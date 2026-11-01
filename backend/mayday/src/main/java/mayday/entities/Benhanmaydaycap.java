package mayday.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "benhanmaydaycap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Benhanmaydaycap {

    @Id
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ma_ho_so")
    private Hosobenhan hoSoBenhAn;

    // 1. loai_benh_an: GENERATED ALWAYS AS ('CAP'::text) STORED
    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    // 2. nhap_vien boolean
    @Column(name = "nhap_vien")
    private Boolean nhapVien;

    // 3. cd_soc_phan_ve boolean
    @Column(name = "cd_soc_phan_ve")
    private Boolean cdSocPhanVe;

    // 4. cd_phan_ve boolean
    @Column(name = "cd_phan_ve")
    private Boolean cdPhanVe;

    // 5. cd_may_day_cap_nang boolean
    @Column(name = "cd_may_day_cap_nang")
    private Boolean cdMayDayCapNang;

    // 6. cd_may_day_cap_thong_thuong boolean
    @Column(name = "cd_may_day_cap_thong_thuong")
    private Boolean cdMayDayCapThongThuong;

    // 7. tien_su_benh_nen_da text
    @Column(name = "tien_su_benh_nen_da", columnDefinition = "text")
    private String tienSuBenhNenDa;

    // 8. tien_su_benh_nen_ho_hap text
    @Column(name = "tien_su_benh_nen_ho_hap", columnDefinition = "text")
    private String tienSuBenhNenHoHap;

    // 9. tien_su_benh_nen_tuan_hoan text
    @Column(name = "tien_su_benh_nen_tuan_hoan", columnDefinition = "text")
    private String tienSuBenhNenTuanHoan;

    // 10. tien_su_benh_tim_mach_ghi_ro text
    @Column(name = "tien_su_benh_tim_mach_ghi_ro", columnDefinition = "text")
    private String tienSuBenhTimMachGhiRo;

    // 11. tien_su_benh_tieu_hoa_man_tinh_ghi_ro text
    @Column(name = "tien_su_benh_tieu_hoa_man_tinh_ghi_ro", columnDefinition = "text")
    private String tienSuBenhTieuHoaManTinhGhiRo;

    // 12. tien_su_benh_nen_tieu_hoa text
    @Column(name = "tien_su_benh_nen_tieu_hoa", columnDefinition = "text")
    private String tienSuBenhNenTieuHoa;

    // 13. nhom_yeu_to_nghi_ngo text
    @Column(name = "nhom_yeu_to_nghi_ngo", columnDefinition = "text")
    private String nhomYeuToNghiNgo;

    // 14. co_nghi_ngo_dot_nay boolean
    @Column(name = "co_nghi_ngo_dot_nay")
    private Boolean coNghiNgoDotNay;

    // 15. ten_yeu_to_nghi_ngo text
    @Column(name = "ten_yeu_to_nghi_ngo", columnDefinition = "text")
    private String tenYeuToNghiNgo;

    // 16. loai_tien_su text
    @Column(name = "loai_tien_su", columnDefinition = "text")
    private String loaiTienSu;

    // 17. da_tung_phan_ung_truoc_day boolean
    @Column(name = "da_tung_phan_ung_truoc_day")
    private Boolean daTungPhanUngTruocDay;

    // 18. thoi_gian_tiep_xuc_den_khoi_phat interval
    @Column(name = "thoi_gian_tiep_xuc_den_khoi_phat")
    private Duration thoiGianTiepXucDenKhoiPhat;

    // 19. thoi_gian_keo_dai_tb interval
    @Column(name = "thoi_gian_keo_dai_tb")
    private Duration thoiGianKeoDaiTb;

    // 20. dot_gan_nhat_cach interval
    @Column(name = "dot_gan_nhat_cach")
    private Duration dotGanNhatCach;

    // 21. tien_su_phan_ve_ghi_ro text
    @Column(name = "tien_su_phan_ve_ghi_ro", columnDefinition = "text")
    private String tienSuPhanVeGhiRo;

    // 22. tien_su_di_ung_ghi_ro text
    @Column(name = "tien_su_di_ung_ghi_ro", columnDefinition = "text")
    private String tienSuDiUngGhiRo;

    // 23. da_dieu_tri_benh_dot_nay boolean
    @Column(name = "da_dieu_tri_benh_dot_nay")
    private Boolean daDieuTriBenhDotNay;

    // 24. tuoi_lan_dau_bi_benh integer
    @Column(name = "tuoi_lan_dau_bi_benh")
    private Integer tuoiLanDauBiBenh;

    // 25. so_lan_may_day_cap_truoc integer
    @Column(name = "so_lan_may_day_cap_truoc")
    private Integer soLanMayDayCapTruoc;

    // 26. thoi_diem_cap_nhat timestamp with time zone (quản lý bởi DB trigger fn_cap_nhat_thoi_gian)
    @Column(name = "thoi_diem_cap_nhat", insertable = false, updatable = false)
    private OffsetDateTime thoiDiemCapNhat;

    // 27. benh_phu text
    @Column(name = "benh_phu", columnDefinition = "text")
    private String benhPhu;
}