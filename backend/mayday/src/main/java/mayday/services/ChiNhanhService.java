package mayday.services;

import mayday.dtos.requests.SetBranchRequest;
import mayday.dtos.requests.EditBranchRequest;
import mayday.dtos.responses.BranchResponse;
import mayday.entities.ChiNhanh;
import mayday.repositories.ChiNhanhRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChiNhanhService {

    private final ChiNhanhRepository chiNhanhRepository;

    public ChiNhanhService(ChiNhanhRepository chiNhanhRepository) {
        this.chiNhanhRepository = chiNhanhRepository;
    }

    private BranchResponse toResponse(ChiNhanh entity) {
        return new BranchResponse(
                entity.getId(),
                entity.getTenChiNhanh(),
                entity.getDiaChi(),
                entity.getSoDienThoai(),
                entity.getTrangThai()
        );
    }

    // set_branch (branch01
    @Transactional
    public BranchResponse setBranch(SetBranchRequest req) {
        if (req.getName() == null || req.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên chi nhánh không được để trống");
        }
        if (req.getAddress() == null || req.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Địa chỉ không được để trống");
        }

        ChiNhanh entity = new ChiNhanh();
        entity.setTenChiNhanh(req.getName().trim());
        entity.setDiaChi(req.getAddress().trim());
        entity.setSoDienThoai(req.getPhone() != null ? req.getPhone().trim() : null);
        entity.setTrangThai(true);

        ChiNhanh saved = chiNhanhRepository.save(entity);
        return toResponse(saved);
    }

    // get_list_branches (branch02)
    // Lấy danh sách tất cả các chi nhánh
    public List<BranchResponse> getListBranches() {
        List<ChiNhanh> list = chiNhanhRepository.findAll();
        List<BranchResponse> responseList = new ArrayList<>();
        for (ChiNhanh c : list) {
            responseList.add(toResponse(c));
        }
        return responseList;
    }

    // get_branch_detail (branch03)
    // Lấy thông tin chi tiết của một chi nhánh theo ID
    public BranchResponse getBranchDetail(Long id) {
        ChiNhanh entity = chiNhanhRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Không tồn tại chi nhánh với id: " + id));
        return toResponse(entity);
    }

    // edit_branch (branch04)
    // Cập nhật thông tin (tên, địa chỉ, SĐT) của một chi nhánh
    @Transactional
    public BranchResponse editBranch(Long id, EditBranchRequest req) {
        ChiNhanh entity = chiNhanhRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Chi nhánh với id " + id + " không tồn tại"));

        if (req.getName() != null && !req.getName().trim().isEmpty()) {
            entity.setTenChiNhanh(req.getName().trim());
        }
        if (req.getAddress() != null && !req.getAddress().trim().isEmpty()) {
            entity.setDiaChi(req.getAddress().trim());
        }
        if (req.getPhone() != null && !req.getPhone().trim().isEmpty()) {
            entity.setSoDienThoai(req.getPhone().trim());
        }

        ChiNhanh updated = chiNhanhRepository.save(entity);
        return toResponse(updated);
    }

    // delete_branch (branch05)
    @Transactional
    public void deleteBranch(Long id) {
        if (!chiNhanhRepository.existsById(id)) {
            throw new IllegalArgumentException("Không tồn tại chi nhánh với ID: " + id);
        }
        chiNhanhRepository.deleteById(id);
    }
}