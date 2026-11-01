package mayday.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "benhanmaydayman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benhanmaydayman {

    @Id
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ma_ho_so")
    private Hosobenhan hoSoBenhAn;

    // 1. loai_benh_an: GENERATED ALWAYS AS ('MAN'::text) STORED
    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    // 2. chan_doan_chinh text (ck: 'CSU', 'CIndU', 'CSU + CIndU', 'Phu mach don thuan')
    @Column(name = "chan_doan_chinh", columnDefinition = "text")
    private String chanDoanChinh;

    // 3. phan_nhom_csu text (ck: 'CSU Type I', 'CSU Type IIb', 'Chong lap Type I + Type IIb', 'Khong xac dinh')
    @Column(name = "phan_nhom_csu", columnDefinition = "text")
    private String phanNhomCsu;

    // 4. chung_da_ve_noi boolean
    @Column(name = "chung_da_ve_noi")
    private Boolean chungDaVeNoi;

    // 5. may_day_cholinergic boolean
    @Column(name = "may_day_cholinergic")
    private Boolean mayDayCholinergic;

    // 6. may_day_do_lanh boolean
    @Column(name = "may_day_do_lanh")
    private Boolean mayDayDoLanh;

    // 7. may_day_adrenergic boolean
    @Column(name = "may_day_adrenergic")
    private Boolean mayDayAdrenergic;

    // 8. may_day_do_nuoc boolean
    @Column(name = "may_day_do_nuoc")
    private Boolean mayDayDoNuoc;

    // 9. may_day_do_anh_sang boolean
    @Column(name = "may_day_do_anh_sang")
    private Boolean mayDayDoAnhSang;

    // 10. may_day_cam_ung_khac boolean
    @Column(name = "may_day_cam_ung_khac")
    private Boolean mayDayCamUngKhac;

    // 11. may_day_cam_ung_khac_ghi_ro text (ck: bắt buộc có chữ khi may_day_cam_ung_khac IS TRUE)
    @Column(name = "may_day_cam_ung_khac_ghi_ro", columnDefinition = "text")
    private String mayDayCamUngKhacGhiRo;

    // 12. bieu_hien_qua_trinh_benh text (ck: 'Chi san phu', 'Chi phu mach', 'San phu + phu mach')
    @Column(name = "bieu_hien_qua_trinh_benh", columnDefinition = "text")
    private String bieuHienQuaTrinhBenh;
}