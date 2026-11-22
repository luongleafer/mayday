package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "taikhoan")
@Getter
@Setter
@NoArgsConstructor
public class TaiKhoan {


    public TaiKhoan(Nguoi owner, String username, String passwordDigest, Boolean isActive, VaiTro role) {
        tenDangNhap = username;
        matKhauMaHoa = passwordDigest;
        trangThaiHoatDong = isActive;
        nguoi = owner;
        vaiTro = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tai_khoan", nullable = false)
    private Long maTaiKhoan;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "ma_nguoi",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_nguoi")
    )
    private Nguoi nguoi;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(
            name = "maVaiTro",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_vai_tro")
    )
    private VaiTro vaiTro;

    @Column(insertable = false, updatable = false)
    private Long maVaiTro;

    @Column(name = "ten_dang_nhap", nullable = false, length = 100)
    private String tenDangNhap;

    @Column(name = "mat_khau_ma_hoa", nullable = false, columnDefinition = "text")
    private String matKhauMaHoa;

    @Column(name = "trang_thai_hoat_dong", nullable = false)
    private Boolean trangThaiHoatDong = true;

    @CurrentTimestamp
    @Column(name = "thoi_diem_tao", nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @CurrentTimestamp
    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;
}