package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

/**
 * Gộp các thành phần liên quan đến dấu hiệu sinh tồn (vital signs) trong bảng `dauhieusinhton`
 */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class VitalSignsInformation {
    OffsetDateTime thoiDiem;
    String triGiac;
    BigDecimal nhietDo;
    BigDecimal spo2;
    BigDecimal mach;
    BigDecimal huyetApTamThu;
    BigDecimal huyetApTamTruong;
    BigDecimal nhipTho;
    Boolean batThuongCoQuanKhac;
    String batThuongCoQuanKhacGhiRo;

    public void update(VitalSignsInformation newInformation) {
        thoiDiem = newInformation.thoiDiem == null ? thoiDiem : newInformation.thoiDiem;
        triGiac = newInformation.triGiac == null ? triGiac : newInformation.triGiac;
        nhietDo = newInformation.nhietDo == null ? nhietDo : newInformation.nhietDo;
        spo2 = newInformation.spo2 == null ? spo2 : newInformation.spo2;
        mach = newInformation.mach == null ? mach : newInformation.mach;
        huyetApTamThu = newInformation.huyetApTamThu == null ? huyetApTamThu : newInformation.huyetApTamThu;
        huyetApTamTruong = newInformation.huyetApTamTruong == null ? huyetApTamTruong : newInformation.huyetApTamTruong;
        nhipTho = newInformation.nhipTho == null ? nhipTho : newInformation.nhipTho;
        batThuongCoQuanKhac = newInformation.batThuongCoQuanKhac == null ? batThuongCoQuanKhac : newInformation.batThuongCoQuanKhac;
        batThuongCoQuanKhacGhiRo = newInformation.batThuongCoQuanKhacGhiRo == null ? batThuongCoQuanKhacGhiRo : newInformation.batThuongCoQuanKhacGhiRo;

    }
}
