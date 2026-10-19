package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "chitietketquanghiemphap", schema = "public")
public class Chitietketquanghiemphap {

    @Id
    @Column(name = "ma_chi_tiet_ket_qua_nghiem_phap", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietKetQuaNghiemPhap;

    @Column(name = "ma_phien_nghiem_phap", nullable = false)
    private Long maPhienNghiemPhap;

    @Column(name = "loai_nghiem_phap", nullable = false)
    private Long loaiNghiemPhap;

    @Column(name = "ket_qua")
    private String ketQua;

    @Column(name = "nguong_gia_tri_so", precision = 12, scale = 4)
    private BigDecimal nguongGiaTriSo;

    @Column(name = "nguong_gia_tri_chu")
    private String nguongGiaTriChu;

    @Column(name = "don_vi_do")
    private String donViDo;

    @Column(name = "diem_ngua_nrs")
    private Short diemNguaNrs;

    @Column(name = "diem_bong_rat_nrs")
    private Short diemBongRatNrs;

    @Column(name = "diem_dau_nrs")
    private Short diemDauNrs;

    @Column(name = "can_nguyen_khac_ten")
    private String canNguyenKhacTen;

    @Column(name = "la_can_nguyen_khac", nullable = false)
    private Boolean laCanNguyenKhac;

    protected Chitietketquanghiemphap() {
    }

    public Long getMaChiTietKetQuaNghiemPhap() {
        return maChiTietKetQuaNghiemPhap;
    }

    public void setMaChiTietKetQuaNghiemPhap(Long maChiTietKetQuaNghiemPhap) {
        this.maChiTietKetQuaNghiemPhap = maChiTietKetQuaNghiemPhap;
    }

    public Long getMaPhienNghiemPhap() {
        return maPhienNghiemPhap;
    }

    public void setMaPhienNghiemPhap(Long maPhienNghiemPhap) {
        this.maPhienNghiemPhap = maPhienNghiemPhap;
    }

    public Long getLoaiNghiemPhap() {
        return loaiNghiemPhap;
    }

    public void setLoaiNghiemPhap(Long loaiNghiemPhap) {
        this.loaiNghiemPhap = loaiNghiemPhap;
    }

    public String getKetQua() {
        return ketQua;
    }

    public void setKetQua(String ketQua) {
        this.ketQua = ketQua;
    }

    public BigDecimal getNguongGiaTriSo() {
        return nguongGiaTriSo;
    }

    public void setNguongGiaTriSo(BigDecimal nguongGiaTriSo) {
        this.nguongGiaTriSo = nguongGiaTriSo;
    }

    public String getNguongGiaTriChu() {
        return nguongGiaTriChu;
    }

    public void setNguongGiaTriChu(String nguongGiaTriChu) {
        this.nguongGiaTriChu = nguongGiaTriChu;
    }

    public String getDonViDo() {
        return donViDo;
    }

    public void setDonViDo(String donViDo) {
        this.donViDo = donViDo;
    }

    public Short getDiemNguaNrs() {
        return diemNguaNrs;
    }

    public void setDiemNguaNrs(Short diemNguaNrs) {
        this.diemNguaNrs = diemNguaNrs;
    }

    public Short getDiemBongRatNrs() {
        return diemBongRatNrs;
    }

    public void setDiemBongRatNrs(Short diemBongRatNrs) {
        this.diemBongRatNrs = diemBongRatNrs;
    }

    public Short getDiemDauNrs() {
        return diemDauNrs;
    }

    public void setDiemDauNrs(Short diemDauNrs) {
        this.diemDauNrs = diemDauNrs;
    }

    public String getCanNguyenKhacTen() {
        return canNguyenKhacTen;
    }

    public void setCanNguyenKhacTen(String canNguyenKhacTen) {
        this.canNguyenKhacTen = canNguyenKhacTen;
    }

    public Boolean getLaCanNguyenKhac() {
        return laCanNguyenKhac;
    }

    public void setLaCanNguyenKhac(Boolean laCanNguyenKhac) {
        this.laCanNguyenKhac = laCanNguyenKhac;
    }

}
