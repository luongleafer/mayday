package mayday.services;

import mayday.dtos.requests.SetDepartmentRequest;
import mayday.dtos.requests.EditDepartmentRequest;
import mayday.dtos.responses.DepartmentResponse;
import mayday.entities.KhoaPhong;
import mayday.entities.ChiNhanh;
import mayday.repositories.KhoaPhongRepository;
import mayday.repositories.ChiNhanhRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhoaPhongService {
    private final KhoaPhongRepository khoaPhongRepository;
    private final ChiNhanhRepository chiNhanhRepository;

    public KhoaPhongService(KhoaPhongRepository khoaPhongRepository, ChiNhanhRepository chiNhanhRepository) {
        this.khoaPhongRepository = khoaPhongRepository;
        this.chiNhanhRepository = chiNhanhRepository;
    }

    public DepartmentResponse toResponse(KhoaPhong entity) {
        return new DepartmentResponse(
                entity.getId(),
                entity.getChiNhanh().getId(),
                entity.getTenKhoaPhong(),
                entity.getMoTa()
        );
    }

    // set_department (dept01)
    @Transactional
    public DepartmentResponse setDepartment(SetDepartmentRequest req) {
        if (req.getBranch_id() == null) {
            throw new IllegalArgumentException("Thiếu tham số branch_id");
        }
        if (req.getName() == null || req.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên khoa/phòng ban không được trống");
        }
        if (req.getDescription() == null || req.getDescription().trim().isEmpty()) {
            throw new IllegalArgumentException("Mô tả khoa/phòng ban không được trống");
        }

        ChiNhanh chiNhanh = chiNhanhRepository.findById(req.getBranch_id())
                .orElseThrow(() -> new RuntimeException("Chi nhánh không tồn tại"));

        KhoaPhong entity = new KhoaPhong();
        entity.setChiNhanh(chiNhanh);
        entity.setTenKhoaPhong(req.getName());
        entity.setMoTa(req.getDescription());

        return toResponse(khoaPhongRepository.save(entity));
    }

    // get_list_departments (dept02)
    public List<DepartmentResponse> getListDepartmentsByBranch(Long branchId) {
        return khoaPhongRepository.findByChiNhanhId(branchId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // get_department_detail (dept03)
    public List<DepartmentResponse> getDepartmentDetail(Long id) {
        KhoaPhong entity = khoaPhongRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khoa/phòng ban không tồn tại"));
        return Collections.singletonList(toResponse(entity));
    }

    // edit_department (dept04)
    @Transactional
    public DepartmentResponse editDepartment(Long id, EditDepartmentRequest req) {
        KhoaPhong entity = khoaPhongRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khoa/phòng ban không tồn tại"));

        if (req.getName() != null && !req.getName().trim().isEmpty()) {
            entity.setTenKhoaPhong(req.getName().trim());
        }
        if (req.getDescription() != null) {
            entity.setMoTa(req.getDescription().trim());
        }

        return toResponse(khoaPhongRepository.save(entity));
    }

    // delete_department (dept05)
    @Transactional
    public void deleteDepartment(Long id) {
        if (!khoaPhongRepository.existsById(id)) {
            throw new IllegalArgumentException("Khoa/phòng ban với id " + id + " không tồn tại");
        }
        khoaPhongRepository.deleteById(id);
    }
}
