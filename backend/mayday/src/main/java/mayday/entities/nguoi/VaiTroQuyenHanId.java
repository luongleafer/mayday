package mayday.entities.nguoi;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class VaiTroQuyenHanId {
    @Column(updatable = false, insertable = false)
    @Getter
    private Long maVaiTro;

    @Column(updatable = false, insertable = false)
    @Getter
    private Long maQuyenHan;

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof VaiTroQuyenHanId vaiTroQuyenHanId)) return false;
        return Objects.equals(maQuyenHan, vaiTroQuyenHanId.maQuyenHan)
                && Objects.equals(maVaiTro, vaiTroQuyenHanId.maVaiTro);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
