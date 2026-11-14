package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class ChronicRecordInformation {
    @Enumerated(EnumType.STRING)
    ChanDoanChinhType chanDoanChinh;

    @Enumerated(EnumType.STRING)
    PhanNhomCsuType phanNhomCsu;

    Boolean chungDaVeNoi;

    Boolean mayDayCholinergic;

    Boolean mayDayDoLanh;

    Boolean mayDayAdrenergic;

    Boolean mayDayDoNuoc;

    Boolean mayDayDoAnhSang;

    Boolean mayDayCamUngKhac;

    String mayDayCamUngKhacGhiRo;

    @Enumerated(EnumType.STRING)
    BieuHienQuaTrinhBenhType bieuHienQuaTrinhBenh;
}
