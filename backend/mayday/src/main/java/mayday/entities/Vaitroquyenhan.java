package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@IdClass(VaitroquyenhanId.class)
@Entity
@Table(name = "vaitroquyenhan", schema = "public")
public class Vaitroquyenhan {

    @Id
    @Column(name = "ma_vai_tro", nullable = false)
    private Long maVaiTro;

    @Id
    @Column(name = "ma_quyen_han", nullable = false)
    private Long maQuyenHan;

    @Column(name = "thoi_diem_gan", nullable = false)
    private OffsetDateTime thoiDiemGan;

    @Column(name = "ma_nguoi_gan")
    private Long maNguoiGan;

    protected Vaitroquyenhan() {
    }

    public Long getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Long maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public Long getMaQuyenHan() {
        return maQuyenHan;
    }

    public void setMaQuyenHan(Long maQuyenHan) {
        this.maQuyenHan = maQuyenHan;
    }

    public OffsetDateTime getThoiDiemGan() {
        return thoiDiemGan;
    }

    public void setThoiDiemGan(OffsetDateTime thoiDiemGan) {
        this.thoiDiemGan = thoiDiemGan;
    }

    public Long getMaNguoiGan() {
        return maNguoiGan;
    }

    public void setMaNguoiGan(Long maNguoiGan) {
        this.maNguoiGan = maNguoiGan;
    }

}
