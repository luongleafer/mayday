package mayday.entities.nguoi;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Entity
@Table(name = "vaitroquyenhan")
@Getter
public class VaiTroQuyenHan {
    @EmbeddedId
    @JsonUnwrapped
    private VaiTroQuyenHanId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maVaiTro")
    @JoinColumn(name = "maVaiTro", referencedColumnName = "ma_vai_tro")
    @Setter
    VaiTro role;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("maQuyenHan")
    @JoinColumn(name = "maQuyenHan", referencedColumnName = "maQuyenHan")
    QuyenHan permission;

    @CurrentTimestamp
    private OffsetDateTime thoiDiemGan;

    @Setter
    private Long maNguoiGan;


}
