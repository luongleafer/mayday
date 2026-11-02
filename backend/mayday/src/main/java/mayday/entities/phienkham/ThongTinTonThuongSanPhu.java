package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Embeddable
public record ThongTinTonThuongSanPhu(
        String dangTonThuong,
        BigDecimal kichThuoc,
        BigDecimal thoiGianTonTai,
        OffsetDateTime thoiDiem,
        String tinhTrangDungThuoc,
        Long maDacDiemSanPhu,
        Integer soLuong,
        String viTri
) {
}
