package mayday.entities;

import java.io.Serializable;
import java.util.Objects;

public class VaitroquyenhanId implements Serializable {

    private static final long serialVersionUID = 1L;

    public Long maVaiTro;
    public Long maQuyenHan;

    public VaitroquyenhanId() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof VaitroquyenhanId that)) return false;
        return Objects.equals(maVaiTro, that.maVaiTro) && Objects.equals(maQuyenHan, that.maQuyenHan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maVaiTro, maQuyenHan);
    }
}
