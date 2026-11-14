package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AcuteSessionInformation {
    String trieuChungDa;
    String trieuChungHoHap;
    String soVoiThuongNgayHoHap;
    String trieuChungTuanHoan;
    String soVoiThuongNgayTuanHoan;
    String soVoiThuongNgayTieuHoa;
    Boolean coSanPhu;
    Boolean coAnhSanPhu;
    Integer soLanNon;
}
