package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Gộp các cột liên quan đến tổn thương cấp tính trong bảng `chitiettonthuongcaptinh`
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AcuteLesionsInformation {
    String dangTonThuong;
    String dangTonThuongKhac;
    BigDecimal kichThuocMaxMm;
    BigDecimal kichThuocMinMm;
    BigDecimal kichThuocDongDeuMm;
    String loaiKichThuoc;
    BigDecimal thoiGianTonTaiTb;
    String dienBien;
    Integer soLuongSanPhu;
    Boolean phuMachBen;
    String viTri;
    String viTriDacBietGhiRo;
}
