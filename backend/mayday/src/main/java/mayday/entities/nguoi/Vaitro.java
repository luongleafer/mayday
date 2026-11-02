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
public class Vaitro {

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

    @CreationTimestamp
    @Column(name = "thoi_diem_tao", nullable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @UpdateTimestamp
    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;
}