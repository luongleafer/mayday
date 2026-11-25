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
public class InfectionStatus {
    Boolean hoHapHo;
    Boolean hoHapDauHong;
    Boolean hoHapHatHoi;
    Boolean hoHapSoMui;
    BigDecimal hoHapThoiGian;
    BigDecimal hoHapKhoangCach;
    Boolean tietNieuTieuRat;
    Boolean tietNieuTieuBuot;
    BigDecimal tietNieuThoiGian;
    BigDecimal tietNieuKhoangCach;
    Boolean daMun;
    Boolean daNhot;
    BigDecimal daThoiGian;
    BigDecimal daKhoangCach;
    String khacGhiRo;
    BigDecimal khacThoiGian;
    BigDecimal khacKhoangCach;
}
