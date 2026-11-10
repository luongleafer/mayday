package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "benhanmaydayman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhAnMayDayMan {

    @Id
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "ma_ho_so", referencedColumnName = "ma_ho_so")
    private HoSoBenhAn hoSoBenhAn;

    // 1. loai_benh_an: GENERATED ALWAYS AS ('MAN'::text) STORED
    @Column(insertable = false, updatable = false)
    private String loaiBenhAn;

    // 2. chan_doan_chinh text (ck: 'CSU', 'CIndU', 'CSU + CIndU', 'Phu mach don thuan')
    @Enumerated(EnumType.STRING)
    private ChanDoanChinhType chanDoanChinh;

    // 3. phan_nhom_csu text (ck: 'CSU Type I', 'CSU Type IIb', 'Chong lap Type I + Type IIb', 'Khong xac dinh')
    @Enumerated(EnumType.STRING)
    private PhanNhomCsuType phanNhomCsu;

    // 4. chung_da_ve_noi boolean
    private Boolean chungDaVeNoi;

    // 5. may_day_cholinergic boolean
    private Boolean mayDayCholinergic;

    // 6. may_day_do_lanh boolean
    private Boolean mayDayDoLanh;

    // 7. may_day_adrenergic boolean
    private Boolean mayDayAdrenergic;

    // 8. may_day_do_nuoc boolean
    private Boolean mayDayDoNuoc;

    // 9. may_day_do_anh_sang boolean
    private Boolean mayDayDoAnhSang;

    // 10. may_day_cam_ung_khac boolean
    private Boolean mayDayCamUngKhac;

    // 11. may_day_cam_ung_khac_ghi_ro text (ck: bắt buộc có chữ khi may_day_cam_ung_khac IS TRUE)
    @Column(columnDefinition = "text")
    private String mayDayCamUngKhacGhiRo;

    // 12. bieu_hien_qua_trinh_benh text (ck: 'Chi san phu', 'Chi phu mach', 'San phu + phu mach')
    @Enumerated(EnumType.STRING)
    private BieuHienQuaTrinhBenhType bieuHienQuaTrinhBenh;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private TienSuBanThan tienSuBanThan;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private BenhSuCacDotBenhTruoc benhSuCacDotBenhTruoc;
}
