package mayday.mappers;

import mayday.dto.responses.BranchResponse;
import mayday.dto.responses.DepartmentResponse;
import mayday.dto.responses.RoomResponse;
import mayday.entities.ChiNhanh;
import mayday.entities.KhoaPhong;
import mayday.entities.PhongKham;
import org.springframework.stereotype.Component;

/** Chuyen entity sang DTO trong transaction, khi cac quan he LAZY van con truy cap duoc. */
@Component
public class TopologyMapper {
    /** Dung cho POST/GET detail/PUT branch: id, name, address, phone, created_at, updated_at. */
    public BranchResponse toResponse(ChiNhanh branch) {
        return new BranchResponse(
                branch.getMaChiNhanh(),
                branch.getTenChiNhanh(),
                branch.getDiaChi(),
                branch.getSoDienThoai(),
                branch.getThoiDiemTao(),
                branch.getThoiDiemCapNhat());
    }

    /** Dung cho GET list branches: id, name, address, phone, is_active. */
    public BranchResponse toListItemResponse(ChiNhanh branch) {
        return new BranchResponse(
                branch.getMaChiNhanh(),
                branch.getTenChiNhanh(),
                branch.getDiaChi(),
                branch.getSoDienThoai(),
                branch.getTrangThai());
    }

    /** Dung cho GET detail / PUT department: id, name, description, branch_id, created_at, updated_at. */
    public DepartmentResponse toResponse(KhoaPhong department) {
        return new DepartmentResponse(
                department.getMaKhoaPhong(),
                department.getTenKhoaPhong(),
                department.getMoTa(),
                department.getChiNhanh() != null ? department.getChiNhanh().getMaChiNhanh() : null,
                department.getThoiDiemTao(),
                department.getThoiDiemCapNhat());
    }

    /** Dung cho POST department: id, name, description, branch_id, created_at. */
    public DepartmentResponse toCreatedResponse(KhoaPhong department) {
        return new DepartmentResponse(
                department.getMaKhoaPhong(),
                department.getTenKhoaPhong(),
                department.getMoTa(),
                department.getChiNhanh() != null ? department.getChiNhanh().getMaChiNhanh() : null,
                department.getThoiDiemTao());
    }

    /** Dung cho GET list departments: id, name, description, branch_id. */
    public DepartmentResponse toListItemResponse(KhoaPhong department) {
        return new DepartmentResponse(
                department.getMaKhoaPhong(),
                department.getTenKhoaPhong(),
                department.getMoTa(),
                department.getChiNhanh() != null ? department.getChiNhanh().getMaChiNhanh() : null);
    }

    /** Dung cho GET detail / PUT room: id, name, description, department_id, created_at, updated_at. */
    public RoomResponse toResponse(PhongKham room) {
        return new RoomResponse(
                room.getMaPhongKham(),
                room.getTenPhongKham(),
                room.getMoTa(),
                room.getKhoaPhong() != null ? room.getKhoaPhong().getMaKhoaPhong() : null,
                room.getThoiDiemTao(),
                room.getThoiDiemCapNhat());
    }

    /** Dung cho POST room va GET list rooms: id, name, description, department_id, created_at. */
    public RoomResponse toCreatedOrListResponse(PhongKham room) {
        return new RoomResponse(
                room.getMaPhongKham(),
                room.getTenPhongKham(),
                room.getMoTa(),
                room.getKhoaPhong() != null ? room.getKhoaPhong().getMaKhoaPhong() : null,
                room.getThoiDiemTao());
    }
}
