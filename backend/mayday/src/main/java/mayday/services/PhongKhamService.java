package mayday.services;

import java.util.List;
import java.util.Map;
import mayday.dtos.requests.SetRoomRequest;
import mayday.dtos.requests.EditRoomRequest;
import mayday.dtos.responses.RoomResponse;
import mayday.entities.KhoaPhong;
import mayday.entities.PhongKham;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.KhoaPhongRepository;
import mayday.repositories.PhongKhamRepository;
import mayday.utils.PersistenceUtils;
import mayday.utils.TextUtils;
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
    public RoomResponse setRoom(SetRoomRequest request) {
        KhoaPhong department = getDepartment(request.departmentId());
        String name = TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByDepartmentIdAndNameIgnoreCase(department.getId(), name)) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        return toResponse(save(new PhongKham(name, department)));
    }

    public List<RoomResponse> getListRoom(Long departmentId) {
        List<PhongKham> rooms;
        if (departmentId == null) {
            rooms = repository.findAllByOrderByIdAsc();
        } else {
            getDepartment(departmentId);
            rooms = repository.findAllByDepartmentIdOrderByIdAsc(departmentId);
        }
        return rooms.stream().map(this::toResponse).toList();
    }

    public RoomResponse getRoomDetail(Long id) {
        return toResponse(get(id));
    }

    @Transactional
    public RoomResponse editRoom(Long id, EditRoomRequest request) {
        PhongKham room = get(id);
        KhoaPhong department = request.departmentId() == null ? room.getDepartment()
                : getDepartment(request.departmentId());
        String name = request.name() == null ? room.getName()
                : TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByDepartmentIdAndNameIgnoreCaseAndIdNot(department.getId(), name, id)) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        room.setName(name);
        room.setDepartment(department);
        return toResponse(save(room));
    }

    @Transactional
    public Map<String, Object> deleteRoom(Long id) {
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

    private PhongKham save(PhongKham room) {
        try {
            return repository.saveAndFlush(room);
        } catch (DataIntegrityViolationException exception) {
            if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
                throw ConflictException.duplicate("Room name already exists in this department");
            }
            throw new InvalidRequestException("Room data violates a database constraint");
        }
    }

    private RoomResponse toResponse(PhongKham room) {
        return new RoomResponse(room.getId(), room.getName(), room.getDepartment().getId());
    }
}
