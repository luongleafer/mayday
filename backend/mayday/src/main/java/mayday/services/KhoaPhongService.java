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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KhoaPhongService {
    private static final String DEFAULT_LOAI_KHOA = "KHOA";

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
                entity.getMoTa(),
                formatTimestamp(entity.getCreatedAt()),
                formatTimestamp(entity.getUpdatedAt())
        );
    }

    private String formatTimestamp(java.time.OffsetDateTime timestamp) {
        return timestamp == null ? null : timestamp.toString();
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

        ChiNhanh chiNhanh = chiNhanhRepository.findById(req.getBranch_id())
                .orElseThrow(() -> new IllegalArgumentException("Chi nhánh không tồn tại"));

        KhoaPhong entity = new KhoaPhong();
        entity.setChiNhanh(chiNhanh);
        entity.setTenKhoaPhong(req.getName().trim());
        if (req.getDescription() != null && !req.getDescription().trim().isEmpty()) {
            entity.setMoTa(req.getDescription().trim());
        }
        entity.setLoaiKhoa(DEFAULT_LOAI_KHOA);

        return toResponse(khoaPhongRepository.save(entity));
    }

    // get_list_departments (dept02)
    public List<DepartmentResponse> getListDepartmentsByBranch(Long branchId) {
        return khoaPhongRepository.findByChiNhanhId(branchId).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // get_department_detail (dept03)
    public DepartmentResponse getDepartmentDetail(Long id) {
        KhoaPhong entity = khoaPhongRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Khoa/phòng ban không tồn tại"));
        return toResponse(entity);
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
            String description = req.getDescription().trim();
            if (!description.isEmpty()) {
                entity.setMoTa(description);
            }
        }
        if (req.getBranch_id() != null) {
            ChiNhanh chiNhanh = chiNhanhRepository.findById(req.getBranch_id())
                    .orElseThrow(() -> new IllegalArgumentException("Chi nhánh không tồn tại"));
            entity.setChiNhanh(chiNhanh);
        }
        if (entity.getLoaiKhoa() == null || entity.getLoaiKhoa().trim().isEmpty()) {
            entity.setLoaiKhoa(DEFAULT_LOAI_KHOA);
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
