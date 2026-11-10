package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.phienkham.PhienKham;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "hosobenhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoSoBenhAn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @Column(name = "ma_benh_nhan", insertable = false, updatable = false)
    private Long maBenhNhan;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_benh_nhan", referencedColumnName = "ma_benh_nhan", nullable = false)
    private BenhNhan benhNhan;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hoSoBenhAn")
    private List<PhienKham> danhSachPhienKham;

    @JsonIgnore
    @OneToOne(mappedBy = "hoSoBenhAn")
    private HoanCanhKhoiPhatDiUng hoanCanhKhoiPhatDiUng;

    @Column(nullable = false)
    private String loaiBenhAn; // 'CAP' hoặc 'MAN'

    // Cột GENERATED ALWAYS STORED trong Postgres -> Cấm JPA insert/update
    @Column(insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(insertable = false, updatable = false)
    private Long maSubtypeMan;

    // Quan hệ 1-1 Subtype để Cascade tự động lưu mà không dính lỗi FK PostgreSQL
    @JsonIgnore
    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BenhAnMayDayCap benhAnMayDayCap;

    @JsonIgnore
    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BenhAnMayDayMan benhAnMayDayMan;

    private OffsetDateTime thoiDiemKhamLanDau;

    private LocalDate ngayBatDauDieuTriNgoaiTru;

    private LocalDate ngayKetThucDieuTriNgoaiTru;

    @Column(columnDefinition = "text")
    private String chanDoanTuyenTruoc;

    @Column(columnDefinition = "text")
    private String chanDoanBanDauPhongKham;

    @Column(columnDefinition = "text")
    private String ketQuaDieuTriBaoHiem;

    @Column(precision = 12, scale = 2)
    private BigDecimal thoiGianKhoiPhatTuan;

    @Builder.Default
    @Column(nullable = false)
    private String trangThai = "draft"; // draft, pending, in_progress, completed, cancelled

    @Column(columnDefinition = "text")
    private String tienSuGiaDinh; // 'Co', 'Khong', 'Khong ro'

    @Column(columnDefinition = "text")
    private String tienSuGiaDinhGhiRo;

    private Boolean daDieuTriTruocKham;

    public Long getMaBenhNhan() {
        return maBenhNhan != null ? maBenhNhan : (benhNhan != null ? benhNhan.getMaBenhNhan() : null);
    }
}
