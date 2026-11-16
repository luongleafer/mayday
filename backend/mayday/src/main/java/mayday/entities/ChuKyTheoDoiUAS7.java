package mayday.entities;

import jakarta.persistence.*;
import lombok.*;
import mayday.entities.nguoi.BenhNhan;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "chukytheodoiuas7")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChuKyTheoDoiUAS7 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chu_ky")
    private Long maChuKy;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_benh_nhan", referencedColumnName = "maBenhNhan", insertable = false, updatable = false)
    private BenhNhan benhNhan;

    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "trang_thai_chu_ky", length = 50)
    @Builder.Default
    private String trangThaiChuKy = "DANG_THEO_DOI";

    @Column(name = "thoi_diem_tao", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime thoiDiemCapNhat;
}