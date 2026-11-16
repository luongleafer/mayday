package mayday.services.cosovatchat;

import java.util.List;
import mayday.dto.requests.SetDepartmentRequest;
import mayday.dto.requests.EditDepartmentRequest;
import mayday.dto.responses.DepartmentResponse;
import mayday.mappers.TopologyMapper;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.ChiNhanhRepository;
import mayday.repositories.KhoaPhongRepository;
import mayday.repositories.PhongKhamRepository;
import mayday.utils.PersistenceUtils;
import mayday.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Quan ly khoa thuoc chi nhanh; kiem tra khoa cha va trung ten truoc khi luu. */
@Service
@Transactional(readOnly = true)
public class KhoaPhongService {
    private static final String DEFAULT_DEPARTMENT_TYPE = "CHUYEN_KHOA";

    private final KhoaPhongRepository repository;
    private final ChiNhanhRepository branchRepository;
    private final PhongKhamRepository roomRepository;
    private final TopologyMapper mapper;

    public KhoaPhongService(KhoaPhongRepository repository, ChiNhanhRepository branchRepository, TopologyMapper mapper) {
        this(repository, branchRepository, null, mapper);
    }

    @Autowired
    public KhoaPhongService(
            KhoaPhongRepository repository,
            ChiNhanhRepository branchRepository,
            PhongKhamRepository roomRepository,
            TopologyMapper mapper) {
        this.repository = repository;
        this.branchRepository = branchRepository;
        this.roomRepository = roomRepository;
        this.mapper = mapper;
    }

    @Transactional
    public DepartmentResponse setDepartment(SetDepartmentRequest request) {
        ChiNhanh branch = getBranch(request.branchId());
        String name = TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByChiNhanh_MaChiNhanhAndTenKhoaPhongIgnoreCase(branch.getMaChiNhanh(), name)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        KhoaPhong department = new KhoaPhong(name, DEFAULT_DEPARTMENT_TYPE, TextUtils.normalizeWhitespace(request.description()), branch);
        return mapper.toCreatedResponse(save(department));
    }

    public List<DepartmentResponse> getListDepartments(Long branchId) {
        List<KhoaPhong> departments;
        if (branchId == null) {
            departments = repository.findAllByOrderByMaKhoaPhongAsc();
        } else {
            getBranch(branchId);
            departments = repository.findAllByChiNhanh_MaChiNhanhOrderByMaKhoaPhongAsc(branchId);
        }
        return departments.stream().map(mapper::toListItemResponse).toList();
    }

    public DepartmentResponse getDepartmentDetail(Long id) {
        return mapper.toResponse(get(id));
    }

    // Cap nhat tung phan: field null trong request duoc giu nguyen gia tri hien tai.
    @Transactional
    public DepartmentResponse editDepartment(Long id, EditDepartmentRequest request) {
        KhoaPhong department = get(id);
        ChiNhanh branch = request.branchId() == null ? department.getChiNhanh() : getBranch(request.branchId());
        String name = request.name() == null ? department.getTenKhoaPhong()
                : TextUtils.requireNonBlank(request.name(), "name");
        // Loai ban ghi dang sua khoi phep kiem tra trung ten.
        if (repository.existsByChiNhanh_MaChiNhanhAndTenKhoaPhongIgnoreCaseAndMaKhoaPhongNot(branch.getMaChiNhanh(), name, id)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        department.updateDetails(name,
                department.getLoaiKhoa(),
                request.description() == null ? department.getMoTa()
                        : TextUtils.normalizeWhitespace(request.description()), branch);
        return mapper.toResponse(save(department));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        KhoaPhong department = get(id);
        try {
            if (roomRepository != null) {
                roomRepository.detachDepartment(id);
            }
            repository.delete(department);
            // Day lenh DELETE xuong DB ngay de bat loi khoa ngoai trong request nay.
            repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Department cannot be deleted because it is in use");
        }
    }

    private KhoaPhong get(Long id) {
        TextUtils.requirePositiveId(id, "id");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Department", id));
    }

    private ChiNhanh getBranch(Long id) {
        TextUtils.requirePositiveId(id, "branch_id");
        return branchRepository.findById(id).orElseThrow(() -> new NotFoundException("Branch", id));
    }

    private KhoaPhong save(KhoaPhong department) {
        try {
            // Flush ngay de loi unique/FK phat sinh truoc khi ket thuc transaction.
            return repository.saveAndFlush(department);
        } catch (DataIntegrityViolationException exception) {
            if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
                throw ConflictException.duplicate("Department name already exists in this branch");
            }
            throw new InvalidRequestException("Department data violates a database constraint");
        }
    }

}
