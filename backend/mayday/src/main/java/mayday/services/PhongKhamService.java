package mayday.services;

import java.util.List;
import mayday.dto.requests.SetRoomRequest;
import mayday.dto.requests.EditRoomRequest;
import mayday.dto.responses.RoomResponse;
import mayday.mappers.TopologyMapper;
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

/** Quan ly phong thuoc khoa; kiem tra khoa ton tai va ten phong trong cung khoa. */
@Service
@Transactional(readOnly = true)
public class PhongKhamService {
    private final PhongKhamRepository repository;
    private final KhoaPhongRepository departmentRepository;

    private final TopologyMapper mapper;

    public PhongKhamService(PhongKhamRepository repository, KhoaPhongRepository departmentRepository, TopologyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public RoomResponse setRoom(SetRoomRequest request) {
        KhoaPhong department = getDepartment(request.departmentId());
        String name = TextUtils.requireNonBlank(request.name(), "name");
        String description = TextUtils.normalizeWhitespace(request.description());
        if (repository.existsByKhoaPhong_MaKhoaPhongAndTenPhongKhamIgnoreCase(department.getMaKhoaPhong(), name)) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        if (department.getChiNhanh() != null && department.getChiNhanh().getMaChiNhanh() != null
                && repository.existsByKhoaPhong_ChiNhanh_MaChiNhanhAndTenPhongKhamIgnoreCase(department.getChiNhanh().getMaChiNhanh(), name)) {
            throw ConflictException.duplicate("Room name already exists in this branch");
        }
        return mapper.toCreatedOrListResponse(save(new PhongKham(name, description, department)));
    }

    public List<RoomResponse> getListRooms(Long departmentId) {
        List<PhongKham> rooms;
        if (departmentId == null) {
            rooms = repository.findAllByOrderByMaPhongKhamAsc();
        } else {
            getDepartment(departmentId);
            rooms = repository.findAllByKhoaPhong_MaKhoaPhongOrderByMaPhongKhamAsc(departmentId);
        }
        return rooms.stream().map(mapper::toCreatedOrListResponse).toList();
    }

    public RoomResponse getRoomDetail(Long id) {
        return mapper.toResponse(get(id));
    }

    // Cap nhat tung phan: field null trong request duoc giu nguyen gia tri hien tai.
    @Transactional
    public RoomResponse editRoom(Long id, EditRoomRequest request) {
        PhongKham room = get(id);
        KhoaPhong department = request.departmentId() == null ? room.getKhoaPhong()
                : getDepartment(request.departmentId());
        String name = request.name() == null ? room.getTenPhongKham()
                : TextUtils.requireNonBlank(request.name(), "name");
        String description = request.description() == null ? room.getMoTa()
                : TextUtils.normalizeWhitespace(request.description());
        // Loai ban ghi dang sua khoi phep kiem tra trung ten.
        boolean duplicate = department != null
                ? repository.existsByKhoaPhong_MaKhoaPhongAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(department.getMaKhoaPhong(), name, id)
                : repository.existsByKhoaPhongIsNullAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(name, id);
        if (duplicate) {
            throw ConflictException.duplicate("Room name already exists in this department");
        }
        if (department != null && department.getChiNhanh() != null && department.getChiNhanh().getMaChiNhanh() != null
                && repository.existsByKhoaPhong_ChiNhanh_MaChiNhanhAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(
                        department.getChiNhanh().getMaChiNhanh(), name, id)) {
            throw ConflictException.duplicate("Room name already exists in this branch");
        }
        room.updateDetails(name, description, department);
        return mapper.toResponse(save(room));
    }

    @Transactional
    public void deleteRoom(Long id) {
        PhongKham room = get(id);
        try {
            repository.delete(room);
            // Day lenh DELETE xuong DB ngay de bat loi khoa ngoai trong request nay.
            repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Room cannot be deleted because it is in use");
        }
    }

    private PhongKham get(Long id) {
        TextUtils.requirePositiveId(id, "id");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Room", id));
    }

    private KhoaPhong getDepartment(Long id) {
        TextUtils.requirePositiveId(id, "department_id");
        return departmentRepository.findById(id).orElseThrow(() -> new NotFoundException("Department", id));
    }

    private PhongKham save(PhongKham room) {
        try {
            // Flush ngay de loi unique/FK phat sinh truoc khi ket thuc transaction.
            return repository.saveAndFlush(room);
        } catch (DataIntegrityViolationException exception) {
            if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
                throw ConflictException.duplicate("Room name already exists in this department");
            }
            throw new InvalidRequestException("Room data violates a database constraint");
        }
    }

}
