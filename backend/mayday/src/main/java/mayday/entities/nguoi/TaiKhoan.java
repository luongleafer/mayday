package mayday.entities.nguoi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
@NoArgsConstructor
public class TaiKhoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tai_khoan", nullable = false)
    private Long maTaiKhoan;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "ma_nguoi",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_nguoi")
    )
    private Nguoi nguoi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "ma_vai_tro",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_vai_tro")
    )
    private VaiTro vaiTro;

    @Column(name = "ten_dang_nhap", nullable = false, length = 100)
    private String tenDangNhap;

    @Column(name = "mat_khau_ma_hoa", nullable = false, columnDefinition = "text")
    private String matKhauMaHoa;

    @Column(name = "trang_thai_hoat_dong", nullable = false)
    private Boolean trangThaiHoatDong = true;

    @CreationTimestamp
    @Column(name = "thoi_diem_tao", nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @UpdateTimestamp
    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;
}