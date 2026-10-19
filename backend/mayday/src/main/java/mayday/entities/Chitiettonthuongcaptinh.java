package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "chitiettonthuongcaptinh", schema = "public")
public class Chitiettonthuongcaptinh {

    @Id
    @Column(name = "ma_chi_tiet_ton_thuong_cap_tinh", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietTonThuongCapTinh;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "dang_ton_thuong")
    private String dangTonThuong;

    @Column(name = "dang_ton_thuong_khac")
    private String dangTonThuongKhac;

    @Column(name = "kich_thuoc_max_mm")
    private BigDecimal kichThuocMaxMm;

    @Column(name = "kich_thuoc_min_mm")
    private BigDecimal kichThuocMinMm;

    @Column(name = "kich_thuoc_dong_deu_mm")
    private BigDecimal kichThuocDongDeuMm;

    @Column(name = "loai_kich_thuoc")
    private String loaiKichThuoc;

    @Column(name = "thoi_gian_ton_tai_tb")
    private BigDecimal thoiGianTonTaiTb;

    @Column(name = "dien_bien")
    private String dienBien;

    @Column(name = "so_luong_san_phu")
    private Integer soLuongSanPhu;

    @Column(name = "phu_mach_ben")
    private Boolean phuMachBen;

    @Column(name = "vi_tri")
    private String viTri;

    @Column(name = "vi_tri_dac_biet_ghi_ro")
    private String viTriDacBietGhiRo;

    protected Chitiettonthuongcaptinh() {
    }

    public Long getMaChiTietTonThuongCapTinh() {
        return maChiTietTonThuongCapTinh;
    }

    public void setMaChiTietTonThuongCapTinh(Long maChiTietTonThuongCapTinh) {
        this.maChiTietTonThuongCapTinh = maChiTietTonThuongCapTinh;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public String getDangTonThuong() {
        return dangTonThuong;
    }

    public void setDangTonThuong(String dangTonThuong) {
        this.dangTonThuong = dangTonThuong;
    }

    public String getDangTonThuongKhac() {
        return dangTonThuongKhac;
    }

    public void setDangTonThuongKhac(String dangTonThuongKhac) {
        this.dangTonThuongKhac = dangTonThuongKhac;
    }

    public BigDecimal getKichThuocMaxMm() {
        return kichThuocMaxMm;
    }

    public void setKichThuocMaxMm(BigDecimal kichThuocMaxMm) {
        this.kichThuocMaxMm = kichThuocMaxMm;
    }

    public BigDecimal getKichThuocMinMm() {
        return kichThuocMinMm;
    }

    public void setKichThuocMinMm(BigDecimal kichThuocMinMm) {
        this.kichThuocMinMm = kichThuocMinMm;
    }

    public BigDecimal getKichThuocDongDeuMm() {
        return kichThuocDongDeuMm;
    }

    public void setKichThuocDongDeuMm(BigDecimal kichThuocDongDeuMm) {
        this.kichThuocDongDeuMm = kichThuocDongDeuMm;
    }

    public String getLoaiKichThuoc() {
        return loaiKichThuoc;
    }

    public void setLoaiKichThuoc(String loaiKichThuoc) {
        this.loaiKichThuoc = loaiKichThuoc;
    }

    public BigDecimal getThoiGianTonTaiTb() {
        return thoiGianTonTaiTb;
    }

    public void setThoiGianTonTaiTb(BigDecimal thoiGianTonTaiTb) {
        this.thoiGianTonTaiTb = thoiGianTonTaiTb;
    }

    public String getDienBien() {
        return dienBien;
    }

    public void setDienBien(String dienBien) {
        this.dienBien = dienBien;
    }

    public Integer getSoLuongSanPhu() {
        return soLuongSanPhu;
    }

    public void setSoLuongSanPhu(Integer soLuongSanPhu) {
        this.soLuongSanPhu = soLuongSanPhu;
    }

    public Boolean getPhuMachBen() {
        return phuMachBen;
    }

    public void setPhuMachBen(Boolean phuMachBen) {
        this.phuMachBen = phuMachBen;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public String getViTriDacBietGhiRo() {
        return viTriDacBietGhiRo;
    }

    public void setViTriDacBietGhiRo(String viTriDacBietGhiRo) {
        this.viTriDacBietGhiRo = viTriDacBietGhiRo;
    }

}
