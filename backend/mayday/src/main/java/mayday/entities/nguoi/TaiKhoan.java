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
    @Column(nullable = false)
    private Long maTaiKhoan;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "ma_nguoi",
            nullable = false,
            unique = true,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_nguoi")
    )
    private Nguoi nguoi;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "ma_vai_tro",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_tai_khoan_vai_tro")
    )
    private VaiTro vaiTro;

    @Column(nullable = false, length = 100)
    private String tenDangNhap;

    @Column(nullable = false, columnDefinition = "text")
    private String matKhauMaHoa;

    @Column(nullable = false)
    private Boolean trangThaiHoatDong = true;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;
}
