package mayday.services;

import java.util.List;
import mayday.dto.requests.SetBranchRequest;
import mayday.dto.requests.EditBranchRequest;
import mayday.dto.responses.BranchResponse;
import mayday.entities.ChiNhanh;
import mayday.exceptions.ConflictException;
import mayday.exceptions.NotFoundException;
import mayday.mappers.TopologyMapper;
import mayday.repositories.ChiNhanhRepository;
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
        ChiNhanh branch = new ChiNhanh();
        branch.updateDetails(TextUtils.requireNonBlank(request.getName(), "name"),
                TextUtils.requireNonBlank(request.getAddress(), "address"),
                TextUtils.trimToNull(request.getPhone()));
        return mapper.toResponse(repository.saveAndFlush(branch));
    }

    public List<BranchResponse> getListBranches() {
        return repository.findAllByOrderByIdAsc().stream().map(mapper::toResponse).toList();
    }

    public BranchResponse getBranchDetail(Long id) {
        return mapper.toResponse(getBranch(id));
    }

    // Cap nhat tung phan: field null trong request duoc giu nguyen gia tri hien tai.
    @Transactional
    public BranchResponse editBranch(Long id, EditBranchRequest request) {
        ChiNhanh branch = getBranch(id);
        branch.updateDetails(
                request.getName() == null ? branch.getTenChiNhanh()
                        : TextUtils.requireNonBlank(request.getName(), "name"),
                request.getAddress() == null ? branch.getDiaChi()
                        : TextUtils.requireNonBlank(request.getAddress(), "address"),
                request.getPhone() == null ? branch.getSoDienThoai()
                        : TextUtils.trimToNull(request.getPhone()));
        return mapper.toResponse(repository.saveAndFlush(branch));
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
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Branch", id));
    }
}
