package mayday.entities.cosovatchat;

import jakarta.persistence.*;

/**
 * Anh xa bang chi_nhanh; ID duoc database tu sinh khi them ban ghi.
 */
@Entity
@Table(name = "chinhanh")
public class ChiNhanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_nhanh")
    private Long id;

    @Column(name = "ten_chi_nhanh", nullable = false, length = 150)
    private String tenChiNhanh;

    @Column(name = "dia_chi", nullable = false, columnDefinition = "TEXT")
    private String diaChi;

    @Column(name = "so_dien_thoai", length = 20)
    private String soDienThoai;

    @Column(name = "trang_thai", nullable = false)
    private Boolean trangThai = true;

    public ChiNhanh() {
    }

    public void updateDetails(String name, String address, String phone) {
        this.tenChiNhanh = name;
        this.diaChi = address;
        this.soDienThoai = phone;
    }

    public ChiNhanh(Long id, String tenChiNhanh, String diaChi, String soDienThoai, Boolean trangThai) {
        this.id = id;
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
