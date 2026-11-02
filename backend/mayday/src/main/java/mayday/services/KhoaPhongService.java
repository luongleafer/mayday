package mayday.services;

import java.util.List;
import mayday.dto.requests.SetDepartmentRequest;
import mayday.dto.requests.EditDepartmentRequest;
import mayday.dto.responses.DepartmentResponse;
import mayday.mappers.TopologyMapper;
import mayday.entities.ChiNhanh;
import mayday.entities.KhoaPhong;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.ChiNhanhRepository;
import mayday.repositories.KhoaPhongRepository;
import mayday.utils.PersistenceUtils;
import mayday.utils.TextUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Quan ly khoa thuoc chi nhanh; kiem tra khoa cha va trung ten truoc khi luu. */
@Service
@Transactional(readOnly = true)
public class KhoaPhongService {
    private final KhoaPhongRepository repository;
    private final ChiNhanhRepository branchRepository;

    private final TopologyMapper mapper;

    public KhoaPhongService(KhoaPhongRepository repository, ChiNhanhRepository branchRepository, TopologyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public DepartmentResponse setDepartment(SetDepartmentRequest request) {
        ChiNhanh branch = getBranch(request.branchId());
        String name = TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByBranchIdAndNameIgnoreCase(branch.getId(), name)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        KhoaPhong department = new KhoaPhong(name, TextUtils.requireNonBlank(request.type(), "type"),
                TextUtils.trimToNull(request.description()), branch);
        return mapper.toResponse(save(department));
    }

    public List<DepartmentResponse> getListDepartment(Long branchId) {
        List<KhoaPhong> departments;
        if (branchId == null) {
            departments = repository.findAllByOrderByIdAsc();
        } else {
            getBranch(branchId);
            departments = repository.findAllByBranchIdOrderByIdAsc(branchId);
        }
        return departments.stream().map(mapper::toResponse).toList();
    }

    public DepartmentResponse getDepartmentDetail(Long id) {
        return mapper.toResponse(get(id));
    }

    // Cap nhat tung phan: field null trong request duoc giu nguyen gia tri hien tai.
    @Transactional
    public DepartmentResponse editDepartment(Long id, EditDepartmentRequest request) {
        KhoaPhong department = get(id);
        ChiNhanh branch = request.branchId() == null ? department.getBranch() : getBranch(request.branchId());
        String name = request.name() == null ? department.getName()
                : TextUtils.requireNonBlank(request.name(), "name");
        // Loai ban ghi dang sua khoi phep kiem tra trung ten.
        if (repository.existsByBranchIdAndNameIgnoreCaseAndIdNot(branch.getId(), name, id)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        department.updateDetails(name,
                request.type() == null ? department.getType() : TextUtils.requireNonBlank(request.type(), "type"),
                request.description() == null ? department.getDescription()
                        : TextUtils.trimToNull(request.description()), branch);
        return mapper.toResponse(save(department));
    }

    @Transactional
    public void deleteDepartment(Long id) {
        KhoaPhong department = get(id);
        try {
            repository.delete(department);
            // Day lenh DELETE xuong DB ngay de bat loi khoa ngoai trong request nay.
            repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Department cannot be deleted because it is in use");
        }
    }

    private KhoaPhong get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Department", id));
    }

    private ChiNhanh getBranch(Long id) {
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
