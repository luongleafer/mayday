package mayday.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nguoi")
@Getter
@Setter
@NoArgsConstructor
public class Nguoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi", nullable = false)
    private Long maNguoi;

    @Column(name = "ho_ten", nullable = false, length = 150)
    private String hoTen;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    @Column(name = "gioi_tinh", length = 3)
    private Gender gioiTinh;

    @Column(name = "so_dien_thoai", nullable = false, length = 10)
    private String soDienThoai;

    @Column(name = "dia_chi_tinh_tp", length = 100)
    private String diaChiTinhTp;

    @Column(name = "dia_chi_xa_phuong", length = 100)
    private String diaChiXaPhuong;

    @Column(name = "dia_chi_so_nha_thon_pho", length = 255)
    private String diaChiSoNhaThonPho;

    @CreationTimestamp
    @Column(name = "thoi_diem_tao", nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @UpdateTimestamp
    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;


}