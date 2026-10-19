package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "vitritonthuong", schema = "public")
public class Vitritonthuong {

    @Id
    @Column(name = "ma_vi_tri_ton_thuong", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maViTriTonThuong;

    @Column(name = "ma_dot_ton_thuong_da", nullable = false)
    private Long maDotTonThuongDa;

    @Column(name = "vi_tri_co_the")
    private String viTriCoThe;

    @Column(name = "thoi_diem_xuat_hien")
    private OffsetDateTime thoiDiemXuatHien;

    @Column(name = "ten_thuoc_lien_quan")
    private String tenThuocLienQuan;

    @Column(name = "thoi_gian_dung_thuoc_truoc_khi_xuat_hien")
    private String thoiGianDungThuocTruocKhiXuatHien;

    @Column(name = "hoan_canh_xuat_hien")
    private String hoanCanhXuatHien;

    @Column(name = "hoan_canh_xuat_hien_khac")
    private String hoanCanhXuatHienKhac;

    @Column(name = "don_vi_thoi_gian")
    private String donViThoiGian;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Vitritonthuong() {
    }

    public Long getMaViTriTonThuong() {
        return maViTriTonThuong;
    }

    public void setMaViTriTonThuong(Long maViTriTonThuong) {
        this.maViTriTonThuong = maViTriTonThuong;
    }

    public Long getMaDotTonThuongDa() {
        return maDotTonThuongDa;
    }

    public void setMaDotTonThuongDa(Long maDotTonThuongDa) {
        this.maDotTonThuongDa = maDotTonThuongDa;
    }

    public String getViTriCoThe() {
        return viTriCoThe;
    }

    public void setViTriCoThe(String viTriCoThe) {
        this.viTriCoThe = viTriCoThe;
    }

    public OffsetDateTime getThoiDiemXuatHien() {
        return thoiDiemXuatHien;
    }

    public void setThoiDiemXuatHien(OffsetDateTime thoiDiemXuatHien) {
        this.thoiDiemXuatHien = thoiDiemXuatHien;
    }

    public String getTenThuocLienQuan() {
        return tenThuocLienQuan;
    }

    public void setTenThuocLienQuan(String tenThuocLienQuan) {
        this.tenThuocLienQuan = tenThuocLienQuan;
    }

    public String getThoiGianDungThuocTruocKhiXuatHien() {
        return thoiGianDungThuocTruocKhiXuatHien;
    }

    public void setThoiGianDungThuocTruocKhiXuatHien(String thoiGianDungThuocTruocKhiXuatHien) {
        this.thoiGianDungThuocTruocKhiXuatHien = thoiGianDungThuocTruocKhiXuatHien;
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

    public String getDonViThoiGian() {
        return donViThoiGian;
    }

    public void setDonViThoiGian(String donViThoiGian) {
        this.donViThoiGian = donViThoiGian;
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
