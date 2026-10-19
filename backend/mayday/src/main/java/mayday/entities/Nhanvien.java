package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "nhanvien", schema = "public")
public class Nhanvien {

    @Id
    @Column(name = "ma_nhan_vien", nullable = false)
    private Long maNhanVien;

    @Column(name = "ma_chi_nhanh", nullable = false)
    private Long maChiNhanh;

    protected Nhanvien() {
    }

    public Long getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Long maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Long getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(Long maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

}
