package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;


/**
 * Gộp các thông tin về tổn thương sẩn phù (wheals) từ bảng `chitiettonthuongsanphu`
 */
@Embeddable
public record WhealsInformation(
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
