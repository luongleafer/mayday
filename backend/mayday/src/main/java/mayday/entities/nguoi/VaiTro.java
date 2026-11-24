package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "vaitro")
@Getter
@Setter
@NoArgsConstructor
public class VaiTro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_vai_tro", nullable = false)
    private Long maVaiTro;

    @Column(name = "ten_vai_tro", nullable = false, length = 50, unique = true)
    private String tenVaiTro;

    @Column(name = "ten_hien_thi", nullable = false, length = 100)
    private String tenHienThi;

    @Column(name = "mo_ta_vai_tro", columnDefinition = "text")
    private String moTaVaiTro;

    @CurrentTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "thoi_diem_tao", nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @CurrentTimestamp
    @Setter(AccessLevel.NONE)
    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(updatable = false, insertable = false)
    private Long maVaiTroCha;

    @OneToOne
    @JoinColumn(name = "maVaiTroCha", referencedColumnName = "ma_vai_tro")
    private VaiTro parentRole;
}