package mayday.services;

import java.util.List;
import mayday.dto.requests.SetBranchRequest;
import mayday.dto.requests.EditBranchRequest;
import mayday.dto.responses.BranchResponse;
import mayday.entities.ChiNhanh;
import mayday.exceptions.ConflictException;
import mayday.exceptions.InvalidRequestException;
import mayday.exceptions.NotFoundException;
import mayday.mappers.TopologyMapper;
import mayday.repositories.ChiNhanhRepository;
import mayday.utils.PersistenceUtils;
import mayday.utils.TextUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** Dieu phoi nghiep vu chi nhanh trong transaction; chi cac thao tac ghi moi thay doi du lieu. */
@Service
@Transactional(readOnly = true)
public class ChiNhanhService {
    private final ChiNhanhRepository repository;
    private final TopologyMapper mapper;

    public ChiNhanhService(ChiNhanhRepository repository, TopologyMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public BranchResponse setBranch(SetBranchRequest request) {
        String name = TextUtils.requireNonBlank(request.getName(), "name");
        String address = TextUtils.requireNonBlank(request.getAddress(), "address");
        String phone = TextUtils.normalizePhone(request.getPhone());
        if (repository.existsByTenChiNhanhIgnoreCase(name)) {
            throw ConflictException.duplicate("Branch name already exists");
        }
        if (repository.existsByDiaChiIgnoreCase(address)) {
            throw ConflictException.duplicate("Branch address already exists");
        }
        if (phone != null && repository.existsBySoDienThoai(phone)) {
            throw ConflictException.duplicate("Branch phone already exists");
        }
        ChiNhanh branch = new ChiNhanh();
        branch.updateDetails(name, address, phone, true);
        return mapper.toResponse(save(branch));
    }

    public List<BranchResponse> getListBranches() {
        return repository.findAllByOrderByMaChiNhanhAsc().stream().map(mapper::toListItemResponse).toList();
    }

    public BranchResponse getBranchDetail(Long id) {
        return mapper.toResponse(getBranch(id));
    }

    // Cap nhat tung phan: field null trong request duoc giu nguyen gia tri hien tai.
    @Transactional
    public BranchResponse editBranch(Long id, EditBranchRequest request) {
        ChiNhanh branch = getBranch(id);
        String name = request.getName() == null ? branch.getTenChiNhanh()
                : TextUtils.requireNonBlank(request.getName(), "name");
        String address = request.getAddress() == null ? branch.getDiaChi()
                : TextUtils.requireNonBlank(request.getAddress(), "address");
        String phone = request.getPhone() == null ? branch.getSoDienThoai()
                : TextUtils.normalizePhone(request.getPhone());
        if (repository.existsByTenChiNhanhIgnoreCaseAndMaChiNhanhNot(name, id)) {
            throw ConflictException.duplicate("Branch name already exists");
        }
        if (repository.existsByDiaChiIgnoreCaseAndMaChiNhanhNot(address, id)) {
            throw ConflictException.duplicate("Branch address already exists");
        }
        if (phone != null && repository.existsBySoDienThoaiAndMaChiNhanhNot(phone, id)) {
            throw ConflictException.duplicate("Branch phone already exists");
        }
        branch.updateDetails(name, address, phone);
        return mapper.toResponse(save(branch));
    }

    @Transactional
    public void deleteBranch(Long id) {
        ChiNhanh branch = getBranch(id);
        try {
            repository.delete(branch);
            // Day lenh DELETE xuong DB ngay de bat loi khoa ngoai trong request nay.
            repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw ConflictException.inUse("Branch cannot be deleted because it is in use");
        }
    }

    private ChiNhanh getBranch(Long id) {
        TextUtils.requirePositiveId(id, "id");
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Branch", id));
    }

    private ChiNhanh save(ChiNhanh branch) {
        try {
            return repository.saveAndFlush(branch);
        } catch (DataIntegrityViolationException exception) {
            if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
                throw ConflictException.duplicate("Branch name already exists");
            }
            throw new InvalidRequestException("Branch data violates a database constraint");
        }
    }
}
