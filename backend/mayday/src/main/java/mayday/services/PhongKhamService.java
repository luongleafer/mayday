package mayday.services;

import java.util.List;
import java.util.Map;
import mayday.dtos.requests.CreateRoomRequest;
import mayday.dtos.requests.UpdateRoomRequest;
import mayday.dtos.responses.RoomResponse;
import mayday.entities.KhoaPhong;
import mayday.entities.PhongKham;
import mayday.exceptions.ConflictException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.KhoaPhongRepository;
import mayday.repositories.PhongKhamRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PhongKhamService {
    private final PhongKhamRepository repository;
    private final KhoaPhongRepository departmentRepository;

    public PhongKhamService(PhongKhamRepository repository, KhoaPhongRepository departmentRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public RoomResponse create(CreateRoomRequest request) {
        KhoaPhong department = getDepartment(request.departmentId());
        String name = ChiNhanhService.required(request.name(), "name");
        if (repository.existsByDepartmentIdAndNameIgnoreCase(department.getId(), name)) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        return toResponse(repository.save(new PhongKham(name, department)));
    }

    public List<RoomResponse> findAll(Long departmentId) {
        List<PhongKham> rooms;
        if (departmentId == null) {
            rooms = repository.findAll();
        } else {
            getDepartment(departmentId);
            rooms = repository.findAllByDepartmentIdOrderByIdAsc(departmentId);
        }
        return rooms.stream().map(this::toResponse).toList();
    }

    public RoomResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Transactional
    public RoomResponse update(Long id, UpdateRoomRequest request) {
        PhongKham room = get(id);
        KhoaPhong department = request.departmentId() == null ? room.getDepartment()
                : getDepartment(request.departmentId());
        String name = request.name() == null ? room.getName()
                : ChiNhanhService.required(request.name(), "name");
        if (repository.existsByDepartmentIdAndNameIgnoreCaseAndIdNot(department.getId(), name, id)) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        room.setName(name);
        room.setDepartment(department);
        return toResponse(repository.save(room));
    }

    @Transactional
    public Map<String, Object> delete(Long id) {
        PhongKham room = get(id);
        try {
            repository.delete(room);
            repository.flush();
            return Map.of();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Room cannot be deleted because it is in use");
        }
    }

    private PhongKham get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Room", id));
    }

    private KhoaPhong getDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department", id));
    }

    private RoomResponse toResponse(PhongKham room) {
        return new RoomResponse(room.getId(), room.getName(), room.getDepartment().getId());
    }
}
