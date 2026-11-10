package mayday.entities.nguoi;

import jakarta.persistence.*;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @Column(name = "ma_nhan_vien", insertable = false, updatable = false)
    private Long maNhanVien;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien", referencedColumnName = "ma_nguoi")
    private Nguoi nguoi;

    @Column(name = "ma_chi_nhanh", nullable = false)
    private Long maChiNhanh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_chi_nhanh", insertable = false, updatable = false)
    private ChiNhanh chiNhanh;

    @Column(name = "ma_khoa_phong")
    private Long maKhoaPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khoa_phong", insertable = false, updatable = false)
    private KhoaPhong khoaPhong;

    @Column(name = "ma_phong_kham")
    private Long maPhongKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_phong_kham", insertable = false, updatable = false)
    private PhongKham phongKham;

    public Long getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(Long maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public Nguoi getNguoi() {
        return nguoi;
    }

    public void setNguoi(Nguoi nguoi) {
        this.nguoi = nguoi;
    }

    public Long getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(Long maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public ChiNhanh getChiNhanh() {
        return chiNhanh;
    }

    public Long getMaKhoaPhong() {
        return maKhoaPhong;
    }

    public void setMaKhoaPhong(Long maKhoaPhong) {
        this.maKhoaPhong = maKhoaPhong;
    }

    public KhoaPhong getKhoaPhong() {
        return khoaPhong;
    }

    public Long getMaPhongKham() {
        return maPhongKham;
    }

    public void setMaPhongKham(Long maPhongKham) {
        this.maPhongKham = maPhongKham;
    }

    public PhongKham getPhongKham() {
        return phongKham;
    }
}
