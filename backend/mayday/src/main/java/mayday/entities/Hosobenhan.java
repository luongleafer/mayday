package mayday.entities;

import jakarta.persistence.*;
import lombok.*;
import mayday.entities.nguoi.Benhnhan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hosobenhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hosobenhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_benh_nhan", nullable = false)
    private Benhnhan benhnhan;

    @Column(name = "loai_benh_an", nullable = false)
    private String loaiBenhAn; // 'CAP' hoặc 'MAN'

    // Cột GENERATED ALWAYS STORED trong Postgres -> Cấm JPA insert/update
    @Column(name = "ma_subtype_cap", insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    @Column(name = "thoi_diem_kham_lan_dau")
    private OffsetDateTime thoiDiemKhamLanDau;

    @Column(name = "ngay_bat_dau_dieu_tri_ngoai_tru")
    private LocalDate ngayBatDauDieuTriNgoaiTru;

    @Column(name = "ngay_ket_thuc_dieu_tri_ngoai_tru")
    private LocalDate ngayKetThucDieuTriNgoaiTru;

    @Column(name = "chan_doan_tuyen_truoc", columnDefinition = "text")
    private String chanDoanTuyenTruoc;

    @Column(name = "chan_doan_ban_dau_phong_kham", columnDefinition = "text")
    private String chanDoanBanDauPhongKham;

    @Column(name = "ket_qua_dieu_tri_bao_hiem", columnDefinition = "text")
    private String ketQuaDieuTriBaoHiem;

    @Column(name = "thoi_gian_khoi_phat_tuan", precision = 12, scale = 2)
    private BigDecimal thoiGianKhoiPhatTuan;

    @Builder.Default
    @Column(name = "trang_thai", nullable = false)
    private String trangThai = "draft"; // draft, pending, in_progress, completed, cancelled

    @Column(name = "tien_su_gia_dinh", columnDefinition = "text")
    private String tienSuGiaDinh; // 'Co', 'Khong', 'Khong ro'

    @Column(name = "tien_su_gia_dinh_ghi_ro", columnDefinition = "text")
    private String tienSuGiaDinhGhiRo;

    @Column(name = "da_dieu_tri_truoc_kham")
    private Boolean daDieuTriTruocKham;

    // Quan hệ 1-1 Subtype để Cascade tự động lưu mà không dính lỗi FK PostgreSQL
    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Benhanmaydaycap benhanmaydaycap;

    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Benhanmaydayman benhanmaydayman;
}