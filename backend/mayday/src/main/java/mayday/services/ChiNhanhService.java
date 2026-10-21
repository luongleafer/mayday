package mayday.services;

import java.util.List;
import java.util.Map;
import mayday.dtos.requests.CreateBranchRequest;
import mayday.dtos.requests.UpdateBranchRequest;
import mayday.dtos.responses.BranchResponse;
import mayday.entities.ChiNhanh;
import mayday.exceptions.ConflictException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.ChiNhanhRepository;
import mayday.utils.TextUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ChiNhanhService {
    private final ChiNhanhRepository repository;

    public ChiNhanhService(ChiNhanhRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public BranchResponse create(CreateBranchRequest request) {
        ChiNhanh branch = new ChiNhanh(TextUtils.requireNonBlank(request.name(), "name"),
                TextUtils.requireNonBlank(request.address(), "address"), TextUtils.trimToNull(request.phone()));
        return toResponse(repository.saveAndFlush(branch));
    }

    public List<BranchResponse> findAll() {
        return repository.findAllByOrderByIdAsc().stream().map(this::toResponse).toList();
    }

    public BranchResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Transactional
    public BranchResponse update(Long id, UpdateBranchRequest request) {
        ChiNhanh branch = get(id);
        if (request.name() != null) branch.setName(TextUtils.requireNonBlank(request.name(), "name"));
        if (request.address() != null) branch.setAddress(TextUtils.requireNonBlank(request.address(), "address"));
        if (request.phone() != null) branch.setPhone(TextUtils.trimToNull(request.phone()));
        if (request.active() != null) branch.setActive(request.active());
        return toResponse(repository.saveAndFlush(branch));
    }

    @Transactional
    public Map<String, Object> delete(Long id) {
        ChiNhanh branch = get(id);
        try {
            repository.delete(branch);
            repository.flush();
            return Map.of();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Branch cannot be deleted because it is in use");
        }
    }

    private ChiNhanh get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Branch", id));
    }

    private BranchResponse toResponse(ChiNhanh branch) {
        return new BranchResponse(branch.getId(), branch.getName(), branch.getAddress(),
                branch.getPhone(), branch.isActive());
    }
}
