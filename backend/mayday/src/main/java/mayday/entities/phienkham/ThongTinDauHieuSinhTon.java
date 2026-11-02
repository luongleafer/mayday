package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Embeddable
public record ThongTinDauHieuSinhTon(
        OffsetDateTime thoiDiem,
        String triGiac,
        BigDecimal nhietDo,
        BigDecimal spo2,
        BigDecimal mach,
        BigDecimal huyetApTamThu,
        BigDecimal huyetApTamTruong,
        BigDecimal nhipTho,
        Boolean batThuongCoQuanKhac,
        String batThuongCoQuanKhacGhiRo
) {
}
