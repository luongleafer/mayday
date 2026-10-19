package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "dottonthuongda", schema = "public")
public class Dottonthuongda {

    @Id
    @Column(name = "ma_dot_ton_thuong_da", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDotTonThuongDa;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ma_ho_so_man")
    private Long maHoSoMan;

    @Column(name = "ten_dot_ton_thuong_da")
    private String tenDotTonThuongDa;

    @Column(name = "thoi_diem_xuat_hien")
    private OffsetDateTime thoiDiemXuatHien;

    @Column(name = "hoan_canh_xuat_hien")
    private String hoanCanhXuatHien;

    @Column(name = "hoan_canh_xuat_hien_khac")
    private String hoanCanhXuatHienKhac;

    @Column(name = "ten_thuoc_dung_gan_thoi_diem_xuat_hien")
    private String tenThuocDungGanThoiDiemXuatHien;

    @Column(name = "thoi_gian_dung_thuoc_truoc_khi_xuat_hien")
    private String thoiGianDungThuocTruocKhiXuatHien;

    @Column(name = "don_vi_thoi_gian")
    private String donViThoiGian;

    @Column(name = "chi_tiet_dot_ton_thuong")
    private String chiTietDotTonThuong;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Dottonthuongda() {
    }

    public Long getMaDotTonThuongDa() {
        return maDotTonThuongDa;
    }

    public void setMaDotTonThuongDa(Long maDotTonThuongDa) {
        this.maDotTonThuongDa = maDotTonThuongDa;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public Long getMaHoSoMan() {
        return maHoSoMan;
    }

    public void setMaHoSoMan(Long maHoSoMan) {
        this.maHoSoMan = maHoSoMan;
    }

    public String getTenDotTonThuongDa() {
        return tenDotTonThuongDa;
    }

    public void setTenDotTonThuongDa(String tenDotTonThuongDa) {
        this.tenDotTonThuongDa = tenDotTonThuongDa;
    }

    public OffsetDateTime getThoiDiemXuatHien() {
        return thoiDiemXuatHien;
    }

    public void setThoiDiemXuatHien(OffsetDateTime thoiDiemXuatHien) {
        this.thoiDiemXuatHien = thoiDiemXuatHien;
    }

    public String getHoanCanhXuatHien() {
        return hoanCanhXuatHien;
    }

    public void setHoanCanhXuatHien(String hoanCanhXuatHien) {
        this.hoanCanhXuatHien = hoanCanhXuatHien;
    }

    public String getHoanCanhXuatHienKhac() {
        return hoanCanhXuatHienKhac;
    }

    public void setHoanCanhXuatHienKhac(String hoanCanhXuatHienKhac) {
        this.hoanCanhXuatHienKhac = hoanCanhXuatHienKhac;
    }

    public String getTenThuocDungGanThoiDiemXuatHien() {
        return tenThuocDungGanThoiDiemXuatHien;
    }

    public void setTenThuocDungGanThoiDiemXuatHien(String tenThuocDungGanThoiDiemXuatHien) {
        this.tenThuocDungGanThoiDiemXuatHien = tenThuocDungGanThoiDiemXuatHien;
    }

    public String getThoiGianDungThuocTruocKhiXuatHien() {
        return thoiGianDungThuocTruocKhiXuatHien;
    }

    public void setThoiGianDungThuocTruocKhiXuatHien(String thoiGianDungThuocTruocKhiXuatHien) {
        this.thoiGianDungThuocTruocKhiXuatHien = thoiGianDungThuocTruocKhiXuatHien;
    }

    public String getDonViThoiGian() {
        return donViThoiGian;
    }

    public void setDonViThoiGian(String donViThoiGian) {
        this.donViThoiGian = donViThoiGian;
    }

    public String getChiTietDotTonThuong() {
        return chiTietDotTonThuong;
    }

    public void setChiTietDotTonThuong(String chiTietDotTonThuong) {
        this.chiTietDotTonThuong = chiTietDotTonThuong;
    }

    public OffsetDateTime getThoiDiemTao() {
        return thoiDiemTao;
    }

    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) {
        this.thoiDiemTao = thoiDiemTao;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
