package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Gộp các thành phần liên quan đến dấu hiệu sinh tồn (vital signs) trong bảng `dauhieusinhton`
 */

@Embeddable
public record VitalSignsInformation(
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
