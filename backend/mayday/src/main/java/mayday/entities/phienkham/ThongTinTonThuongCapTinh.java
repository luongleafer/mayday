package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record ThongTinTonThuongCapTinh(
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
