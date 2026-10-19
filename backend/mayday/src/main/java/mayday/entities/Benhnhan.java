package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "benhnhan", schema = "public")
public class Benhnhan {

    @Id
    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ma_his")
    private String maHis;

    @Column(name = "nghe_nghiep")
    private String ngheNghiep;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "bhyt_so_the")
    private String bhytSoThe;

    @Column(name = "bhyt_gia_tri_den")
    private LocalDate bhytGiaTriDen;

    @Column(name = "nguoi_nha_ho_ten_dia_chi")
    private String nguoiNhaHoTenDiaChi;

    @Column(name = "nguoi_nha_so_dien_thoai")
    private String nguoiNhaSoDienThoai;

    protected Benhnhan() {
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getMaHis() {
        return maHis;
    }

    public void setMaHis(String maHis) {
        this.maHis = maHis;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getBhytSoThe() {
        return bhytSoThe;
    }

    public void setBhytSoThe(String bhytSoThe) {
        this.bhytSoThe = bhytSoThe;
    }

    public LocalDate getBhytGiaTriDen() {
        return bhytGiaTriDen;
    }

    public void setBhytGiaTriDen(LocalDate bhytGiaTriDen) {
        this.bhytGiaTriDen = bhytGiaTriDen;
    }

    public String getNguoiNhaHoTenDiaChi() {
        return nguoiNhaHoTenDiaChi;
    }

    public void setNguoiNhaHoTenDiaChi(String nguoiNhaHoTenDiaChi) {
        this.nguoiNhaHoTenDiaChi = nguoiNhaHoTenDiaChi;
    }

    public String getNguoiNhaSoDienThoai() {
        return nguoiNhaSoDienThoai;
    }

    public void setNguoiNhaSoDienThoai(String nguoiNhaSoDienThoai) {
        this.nguoiNhaSoDienThoai = nguoiNhaSoDienThoai;
    }

}
