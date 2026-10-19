package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "khoaphong", schema = "public")
public class Khoaphong {

    @Id
    @Column(name = "ma_khoa_phong", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhoaPhong;

    @Column(name = "ten_khoa_phong", nullable = false)
    private String tenKhoaPhong;

    @Column(name = "loai_khoa", nullable = false)
    private String loaiKhoa;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "ma_chi_nhanh", nullable = false)
    private Long maChiNhanh;

    protected Khoaphong() {
    }

    public Long getMaKhoaPhong() {
        return maKhoaPhong;
    }

    public void setMaKhoaPhong(Long maKhoaPhong) {
        this.maKhoaPhong = maKhoaPhong;
    }

    public String getTenKhoaPhong() {
        return tenKhoaPhong;
    }

    public void setTenKhoaPhong(String tenKhoaPhong) {
        this.tenKhoaPhong = tenKhoaPhong;
    }

    public String getLoaiKhoa() {
        return loaiKhoa;
    }

    public void setLoaiKhoa(String loaiKhoa) {
        this.loaiKhoa = loaiKhoa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Long getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(Long maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

}
