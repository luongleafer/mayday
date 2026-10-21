package mayday.services;

import java.util.List;
import java.util.Map;
import mayday.dtos.requests.CreateBranchRequest;
import mayday.dtos.requests.UpdateBranchRequest;
import mayday.dtos.responses.BranchResponse;
import mayday.entities.ChiNhanh;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.exceptions.NotFoundException;
import mayday.repositories.ChiNhanhRepository;
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
        ChiNhanh branch = new ChiNhanh(required(request.name(), "name"),
                required(request.address(), "address"), trimToNull(request.phone()));
        return toResponse(repository.save(branch));
    }

    public List<BranchResponse> findAll() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    public BranchResponse findById(Long id) {
        return toResponse(get(id));
    }

    @Transactional
    public BranchResponse update(Long id, UpdateBranchRequest request) {
        ChiNhanh branch = get(id);
        if (request.name() != null) branch.setName(required(request.name(), "name"));
        if (request.address() != null) branch.setAddress(required(request.address(), "address"));
        if (request.phone() != null) branch.setPhone(trimToNull(request.phone()));
        if (request.active() != null) branch.setActive(request.active());
        return toResponse(repository.save(branch));
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

    static String required(String value, String field) {
        String result = trimToNull(value);
        if (result == null) throw new InvalidRequestException(field + " must not be blank");
        return result;
    }

    static String trimToNull(String value) {
        if (value == null) return null;
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }
}
