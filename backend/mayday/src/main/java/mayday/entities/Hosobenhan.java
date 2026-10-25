package mayday.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hosobenhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "trang_thai", nullable = false)
    private String trangThai = "draft"; // draft, pending, in_progress, completed, cancelled

    @Column(name = "tien_su_gia_dinh", columnDefinition = "text")
    private String tienSuGiaDinh; // 'Co', 'Khong', 'Khong ro'

    @Column(name = "tien_su_gia_dinh_ghi_ro", columnDefinition = "text")
    private String tienSuGiaDinhGhiRo;

    @Column(name = "da_dieu_tri_truoc_kham")
    private Boolean daDieuTriTruocKham;
}