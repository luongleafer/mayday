package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ClinicHistoryDetail {
    Boolean tungCoDotTuongTu;

    Short tuoiKhoiPhat;

    Integer soDotTruoc;

    String kieuDienBien;

    String bieuHienDotTruoc;

    Boolean daTungNhapVien;

    Integer soLanNhapVien;

    BigDecimal thoiGianTuDotGanNhat;

    String dienBienCacDotTruoc;
}
