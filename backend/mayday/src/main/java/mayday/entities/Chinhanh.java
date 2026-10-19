package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chinhanh", schema = "public")
public class Chinhanh {

    @Id
    @Column(name = "ma_chi_nhanh", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiNhanh;

    @Column(name = "ten_chi_nhanh", nullable = false)
    private String tenChiNhanh;

    @Column(name = "dia_chi", nullable = false)
    private String diaChi;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai;

    protected Chinhanh() {
    }

    public Long getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(Long maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

}
