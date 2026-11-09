package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "benhanmaydayman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhAnMayDayMan {
    @Id
    private Long maHoSo;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    @ColumnDefault("'MAN'")
    private String loaiBenhAn;

    @Enumerated(EnumType.STRING)
    private ChanDoanChinhType chanDoanChinh;

    @Enumerated(EnumType.STRING)
    private PhanNhomCsuType phanNhomCsu;

    private Boolean chungDaVeNoi;

    private Boolean mayDayCholinergic;

    private Boolean mayDayDoLanh;

    private Boolean mayDayAdrenergic;

    private Boolean mayDayDoNuoc;

    private Boolean mayDayDoAnhSang;

    private Boolean mayDayCamUngKhac;

    private String mayDayCamUngKhacGhiRo;

    @Enumerated(EnumType.STRING)
    private BieuHienQuaTrinhBenhType bieuHienQuaTrinhBenh;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private TienSuBanThan tienSuBanThan;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private BenhSuCacDotBenhTruoc benhSuCacDotBenhTruoc;


}