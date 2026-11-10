package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "benhanmaydaycap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhAnMayDayCap {

    @Id
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "ma_ho_so", referencedColumnName = "ma_ho_so")
    private HoSoBenhAn hoSoBenhAn;

    // 1. loai_benh_an: GENERATED ALWAYS AS ('CAP'::text) STORED
    @Column(insertable = false, updatable = false)
    private String loaiBenhAn;

    // 2. nhap_vien boolean
    private Boolean nhapVien;

    // 3. cd_soc_phan_ve boolean
    private Boolean cdSocPhanVe;

    // 4. cd_phan_ve boolean
    private Boolean cdPhanVe;

    // 5. cd_may_day_cap_nang boolean
    private Boolean cdMayDayCapNang;

    // 6. cd_may_day_cap_thong_thuong boolean
    private Boolean cdMayDayCapThongThuong;

    // 7. tien_su_benh_nen_da text
    @Column(columnDefinition = "text")
    private String tienSuBenhNenDa;

    // 8. tien_su_benh_nen_ho_hap text
    @Column(columnDefinition = "text")
    private String tienSuBenhNenHoHap;

    // 9. tien_su_benh_nen_tuan_hoan text
    @Column(columnDefinition = "text")
    private String tienSuBenhNenTuanHoan;

    // 10. tien_su_benh_tim_mach_ghi_ro text
    @Column(columnDefinition = "text")
    private String tienSuBenhTimMachGhiRo;

    // 11. tien_su_benh_tieu_hoa_man_tinh_ghi_ro text
    @Column(columnDefinition = "text")
    private String tienSuBenhTieuHoaManTinhGhiRo;

    // 12. tien_su_benh_nen_tieu_hoa text
    @Column(columnDefinition = "text")
    private String tienSuBenhNenTieuHoa;

    // 13. nhom_yeu_to_nghi_ngo text
    @Column(columnDefinition = "text")
    private String nhomYeuToNghiNgo;

    // 14. co_nghi_ngo_dot_nay boolean
    private Boolean coNghiNgoDotNay;

    // 15. ten_yeu_to_nghi_ngo text
    @Column(columnDefinition = "text")
    private String tenYeuToNghiNgo;

    // 16. loai_tien_su text
    @Column(columnDefinition = "text")
    private String loaiTienSu;

    // 17. da_tung_phan_ung_truoc_day boolean
    private Boolean daTungPhanUngTruocDay;

    // 18. thoi_gian_tiep_xuc_den_khoi_phat interval
    @Column(columnDefinition = "interval")
    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    private Duration thoiGianTiepXucDenKhoiPhat;

    // 19. thoi_gian_keo_dai_tb interval
    @Column(columnDefinition = "interval")
    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    private Duration thoiGianKeoDaiTb;

    // 20. dot_gan_nhat_cach interval
    @Column(columnDefinition = "interval")
    @JdbcTypeCode(SqlTypes.INTERVAL_SECOND)
    private Duration dotGanNhatCach;

    // 21. tien_su_phan_ve_ghi_ro text
    @Column(columnDefinition = "text")
    private String tienSuPhanVeGhiRo;

    // 22. tien_su_di_ung_ghi_ro text
    @Column(columnDefinition = "text")
    private String tienSuDiUngGhiRo;

    // 23. da_dieu_tri_benh_dot_nay boolean
    private Boolean daDieuTriBenhDotNay;

    // 24. tuoi_lan_dau_bi_benh integer
    private Integer tuoiLanDauBiBenh;

    // 25. so_lan_may_day_cap_truoc integer
    private Integer soLanMayDayCapTruoc;

    // 26. thoi_diem_cap_nhat timestamp with time zone (quản lý bởi DB trigger fn_cap_nhat_thoi_gian)
    @Column(nullable = false, insertable = false, updatable = false)
    private OffsetDateTime thoiDiemCapNhat;

    // 27. benh_phu text
    @Column(columnDefinition = "text")
    private String benhPhu;

    @OneToOne(mappedBy = "benhAnMayDayCap")
    private TinhTrangNhiemTrung tinhTrangNhiemTrung;
}
