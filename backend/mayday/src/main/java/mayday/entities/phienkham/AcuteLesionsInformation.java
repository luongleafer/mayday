package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

/**
 * Gộp các cột liên quan đến tổn thương cấp tính trong bảng `chitiettonthuongcaptinh`
 */
@Embeddable
public record AcuteLesionsInformation(
        String dangTonThuong,
        String dangTonThuongKhac,
        BigDecimal kichThuocMaxMm,
        BigDecimal kichThuocMinMm,
        BigDecimal kichThuocDongDeuMm,
        String loaiKichThuoc,
        BigDecimal thoiGianTonTaiTb,
        String dienBien,
        Integer soLuongSanPhu,
        Boolean phuMachBen,
        String viTri,
        String viTriDacBietGhiRo
) {
}
