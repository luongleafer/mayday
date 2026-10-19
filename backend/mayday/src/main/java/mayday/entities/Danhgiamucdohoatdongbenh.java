package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "danhgiamucdohoatdongbenh", schema = "public")
public class Danhgiamucdohoatdongbenh {

    @Id
    @Column(name = "ma_danh_gia_hoat_dong", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDanhGiaHoatDong;

    @Column(name = "ma_phien_kham_man", nullable = false)
    private Long maPhienKhamMan;

    @Column(name = "du_du_lieu_7_ngay")
    private Boolean duDuLieu7Ngay;

    @Column(name = "ma_nguoi_danh_gia")
    private Long maNguoiDanhGia;

    @Column(name = "thoi_diem_danh_gia", nullable = false)
    private OffsetDateTime thoiDiemDanhGia;

    @Column(name = "hss7_san_phu_khong_dung_thuoc")
    private Short hss7SanPhuKhongDungThuoc;

    @Column(name = "hss7_san_phu_dung_thuoc_deu")
    private Short hss7SanPhuDungThuocDeu;

    @Column(name = "hss7_san_phu_dung_thuoc_khong_deu")
    private Short hss7SanPhuDungThuocKhongDeu;

    @Column(name = "iss7_ngua_khong_dung_thuoc")
    private Short iss7NguaKhongDungThuoc;

    @Column(name = "iss7_ngua_dung_thuoc_deu")
    private Short iss7NguaDungThuocDeu;

    @Column(name = "iss7_ngua_dung_thuoc_khong_deu")
    private Short iss7NguaDungThuocKhongDeu;

    @Column(name = "uas7_tong_khong_dung_thuoc")
    private Short uas7TongKhongDungThuoc;

    @Column(name = "uas7_tong_dung_thuoc_deu")
    private Short uas7TongDungThuocDeu;

    @Column(name = "uas7_tong_dung_thuoc_khong_deu")
    private Short uas7TongDungThuocKhongDeu;

    @Column(name = "ghi_chu")
    private String ghiChu;

    protected Danhgiamucdohoatdongbenh() {
    }

    public Long getMaDanhGiaHoatDong() {
        return maDanhGiaHoatDong;
    }

    public void setMaDanhGiaHoatDong(Long maDanhGiaHoatDong) {
        this.maDanhGiaHoatDong = maDanhGiaHoatDong;
    }

    public Long getMaPhienKhamMan() {
        return maPhienKhamMan;
    }

    public void setMaPhienKhamMan(Long maPhienKhamMan) {
        this.maPhienKhamMan = maPhienKhamMan;
    }

    public Boolean getDuDuLieu7Ngay() {
        return duDuLieu7Ngay;
    }

    public void setDuDuLieu7Ngay(Boolean duDuLieu7Ngay) {
        this.duDuLieu7Ngay = duDuLieu7Ngay;
    }

    public Long getMaNguoiDanhGia() {
        return maNguoiDanhGia;
    }

    public void setMaNguoiDanhGia(Long maNguoiDanhGia) {
        this.maNguoiDanhGia = maNguoiDanhGia;
    }

    public OffsetDateTime getThoiDiemDanhGia() {
        return thoiDiemDanhGia;
    }

    public void setThoiDiemDanhGia(OffsetDateTime thoiDiemDanhGia) {
        this.thoiDiemDanhGia = thoiDiemDanhGia;
    }

    public Short getHss7SanPhuKhongDungThuoc() {
        return hss7SanPhuKhongDungThuoc;
    }

    public void setHss7SanPhuKhongDungThuoc(Short hss7SanPhuKhongDungThuoc) {
        this.hss7SanPhuKhongDungThuoc = hss7SanPhuKhongDungThuoc;
    }

    public Short getHss7SanPhuDungThuocDeu() {
        return hss7SanPhuDungThuocDeu;
    }

    public void setHss7SanPhuDungThuocDeu(Short hss7SanPhuDungThuocDeu) {
        this.hss7SanPhuDungThuocDeu = hss7SanPhuDungThuocDeu;
    }

    public Short getHss7SanPhuDungThuocKhongDeu() {
        return hss7SanPhuDungThuocKhongDeu;
    }

    public void setHss7SanPhuDungThuocKhongDeu(Short hss7SanPhuDungThuocKhongDeu) {
        this.hss7SanPhuDungThuocKhongDeu = hss7SanPhuDungThuocKhongDeu;
    }

    public Short getIss7NguaKhongDungThuoc() {
        return iss7NguaKhongDungThuoc;
    }

    public void setIss7NguaKhongDungThuoc(Short iss7NguaKhongDungThuoc) {
        this.iss7NguaKhongDungThuoc = iss7NguaKhongDungThuoc;
    }

    public Short getIss7NguaDungThuocDeu() {
        return iss7NguaDungThuocDeu;
    }

    public void setIss7NguaDungThuocDeu(Short iss7NguaDungThuocDeu) {
        this.iss7NguaDungThuocDeu = iss7NguaDungThuocDeu;
    }

    public Short getIss7NguaDungThuocKhongDeu() {
        return iss7NguaDungThuocKhongDeu;
    }

    public void setIss7NguaDungThuocKhongDeu(Short iss7NguaDungThuocKhongDeu) {
        this.iss7NguaDungThuocKhongDeu = iss7NguaDungThuocKhongDeu;
    }

    public Short getUas7TongKhongDungThuoc() {
        return uas7TongKhongDungThuoc;
    }

    public void setUas7TongKhongDungThuoc(Short uas7TongKhongDungThuoc) {
        this.uas7TongKhongDungThuoc = uas7TongKhongDungThuoc;
    }

    public Short getUas7TongDungThuocDeu() {
        return uas7TongDungThuocDeu;
    }

    public void setUas7TongDungThuocDeu(Short uas7TongDungThuocDeu) {
        this.uas7TongDungThuocDeu = uas7TongDungThuocDeu;
    }

    public Short getUas7TongDungThuocKhongDeu() {
        return uas7TongDungThuocKhongDeu;
    }

    public void setUas7TongDungThuocKhongDeu(Short uas7TongDungThuocKhongDeu) {
        this.uas7TongDungThuocKhongDeu = uas7TongDungThuocKhongDeu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

}
