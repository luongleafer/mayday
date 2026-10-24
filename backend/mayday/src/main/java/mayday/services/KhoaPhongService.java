package mayday.services;

import java.util.List;
import java.util.Map;
import mayday.dtos.requests.CreateDepartmentRequest;
import mayday.dtos.requests.UpdateDepartmentRequest;
import mayday.dtos.responses.DepartmentResponse;
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

@Service
@Transactional(readOnly = true)
public class KhoaPhongService {
    private final KhoaPhongRepository repository;
    private final ChiNhanhRepository branchRepository;

    public KhoaPhongService(KhoaPhongRepository repository, ChiNhanhRepository branchRepository) {
        this.repository = repository;
        this.branchRepository = branchRepository;
    }

    @Transactional
    public DepartmentResponse create(CreateDepartmentRequest request) {
        ChiNhanh branch = getBranch(request.branchId());
        String name = TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByBranchIdAndNameIgnoreCase(branch.getId(), name)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        KhoaPhong department = new KhoaPhong(name, TextUtils.requireNonBlank(request.type(), "type"),
                TextUtils.trimToNull(request.description()), branch);
        return toResponse(save(department));
    }

    public List<DepartmentResponse> findAll(Long branchId) {
        List<KhoaPhong> departments;
        if (branchId == null) {
            departments = repository.findAllByOrderByIdAsc();
        } else {
            getBranch(branchId);
            departments = repository.findAllByBranchIdOrderByIdAsc(branchId);
        }
        return departments.stream().map(this::toResponse).toList();
    }

    public DepartmentResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Transactional
    public DepartmentResponse update(Long id, UpdateDepartmentRequest request) {
        KhoaPhong department = get(id);
        ChiNhanh branch = request.branchId() == null ? department.getBranch() : getBranch(request.branchId());
        String name = request.name() == null ? department.getName()
                : TextUtils.requireNonBlank(request.name(), "name");
        if (repository.existsByBranchIdAndNameIgnoreCaseAndIdNot(branch.getId(), name, id)) {
            throw ConflictException.duplicate("Department name already exists in this branch");
        }
        department.setName(name);
        department.setBranch(branch);
        if (request.type() != null) department.setType(TextUtils.requireNonBlank(request.type(), "type"));
        if (request.description() != null) department.setDescription(TextUtils.trimToNull(request.description()));
        return toResponse(save(department));
    }

    @Transactional
    public Map<String, Object> delete(Long id) {
        KhoaPhong department = get(id);
        try {
            repository.delete(department);
            repository.flush();
            return Map.of();
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
            return repository.saveAndFlush(department);
        } catch (DataIntegrityViolationException exception) {
            if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
                throw ConflictException.duplicate("Department name already exists in this branch");
            }
            throw new InvalidRequestException("Department data violates a database constraint");
        }
    }

    private DepartmentResponse toResponse(KhoaPhong department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getType(),
                department.getDescription(), department.getBranch().getId());
    }
}
