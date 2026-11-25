package mayday.entities.nguoi;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "quyenhan")
@NoArgsConstructor
@Getter
public class QuyenHan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maQuyenHan;

    @Column(length = 100, unique = true)
    @Setter
    private String tenQuyenHan;

    @Column(length = 100)
    @Setter
    private String nhomQuyen;

    @Setter
    private String moTa;

    @CurrentTimestamp
    @Column(updatable = false)
    private OffsetDateTime thoiDiemTao;

    @CurrentTimestamp
    private OffsetDateTime thoiDiemCapNhat;

    @OneToMany(mappedBy = "permission", cascade = CascadeType.REMOVE)
    private List<VaiTroQuyenHan> permissionRoles;
}
