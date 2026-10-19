package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nhatkyaashangngay", schema = "public")
public class Nhatkyaashangngay {

    @Id
    @Column(name = "ma_nhat_ky_aas", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhatKyAas;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ma_ho_so_man")
    private Long maHoSoMan;

    @Column(name = "ngay_theo_doi", nullable = false)
    private LocalDate ngayTheoDoi;

    @Column(name = "co_dot_sung_phu", nullable = false)
    private Boolean coDotSungPhu;

    @Column(name = "thoi_diem_xuat_hien")
    private String thoiDiemXuatHien;

    @Column(name = "muc_kho_chiu")
    private Short mucKhoChiu;

    @Column(name = "han_che_hoat_dong")
    private Short hanCheHoatDong;

    @Column(name = "anh_huong_ngoai_hinh")
    private Short anhHuongNgoaiHinh;

    @Column(name = "do_nang_tong_the")
    private Short doNangTongThe;

    @Column(name = "diem_aas_ngay", insertable = false, updatable = false)
    private Short diemAasNgay;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Nhatkyaashangngay() {
    }

    public Long getMaNhatKyAas() {
        return maNhatKyAas;
    }

    public void setMaNhatKyAas(Long maNhatKyAas) {
        this.maNhatKyAas = maNhatKyAas;
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

    public LocalDate getNgayTheoDoi() {
        return ngayTheoDoi;
    }

    public void setNgayTheoDoi(LocalDate ngayTheoDoi) {
        this.ngayTheoDoi = ngayTheoDoi;
    }

    public Boolean getCoDotSungPhu() {
        return coDotSungPhu;
    }

    public void setCoDotSungPhu(Boolean coDotSungPhu) {
        this.coDotSungPhu = coDotSungPhu;
    }

    public String getThoiDiemXuatHien() {
        return thoiDiemXuatHien;
    }

    public void setThoiDiemXuatHien(String thoiDiemXuatHien) {
        this.thoiDiemXuatHien = thoiDiemXuatHien;
    }

    public Short getMucKhoChiu() {
        return mucKhoChiu;
    }

    public void setMucKhoChiu(Short mucKhoChiu) {
        this.mucKhoChiu = mucKhoChiu;
    }

    public Short getHanCheHoatDong() {
        return hanCheHoatDong;
    }

    public void setHanCheHoatDong(Short hanCheHoatDong) {
        this.hanCheHoatDong = hanCheHoatDong;
    }

    public Short getAnhHuongNgoaiHinh() {
        return anhHuongNgoaiHinh;
    }

    public void setAnhHuongNgoaiHinh(Short anhHuongNgoaiHinh) {
        this.anhHuongNgoaiHinh = anhHuongNgoaiHinh;
    }

    public Short getDoNangTongThe() {
        return doNangTongThe;
    }

    public void setDoNangTongThe(Short doNangTongThe) {
        this.doNangTongThe = doNangTongThe;
    }

    public Short getDiemAasNgay() {
        return diemAasNgay;
    }

    public void setDiemAasNgay(Short diemAasNgay) {
        this.diemAasNgay = diemAasNgay;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
