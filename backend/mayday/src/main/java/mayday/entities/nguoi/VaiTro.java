package mayday.entities.nguoi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
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

    @Column(nullable = false, length = 50, unique = true)
    private String tenVaiTro;

    @Column(nullable = false, length = 100)
    private String tenHienThi;

    @Column(columnDefinition = "text")
    private String moTaVaiTro;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;
}
