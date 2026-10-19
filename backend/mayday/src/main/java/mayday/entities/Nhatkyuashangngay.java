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
@Table(name = "nhatkyuashangngay", schema = "public")
public class Nhatkyuashangngay {

    @Id
    @Column(name = "ma_nhat_ky_uas", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNhatKyUas;

    @Column(name = "ma_chu_ky")
    private Long maChuKy;

    @Column(name = "ma_ho_so_man")
    private Long maHoSoMan;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ngay_cham_diem", nullable = false)
    private LocalDate ngayChamDiem;

    @Column(name = "ngay_thu_may_trong_chu_ky")
    private Short ngayThuMayTrongChuKy;

    @Column(name = "diem_san_phu_hss", nullable = false)
    private Short diemSanPhuHss;

    @Column(name = "diem_ngua_iss", nullable = false)
    private Short diemNguaIss;

    @Column(name = "diem_uas_ngay", insertable = false, updatable = false)
    private Short diemUasNgay;

    @Column(name = "trang_thai_dung_thuoc")
    private String trangThaiDungThuoc;

    @Column(name = "trang_thai_ngay", nullable = false)
    private String trangThaiNgay;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(name = "ngay_bat_dau_chu_ky", insertable = false, updatable = false)
    private LocalDate ngayBatDauChuKy;

    protected Nhatkyuashangngay() {
    }

    public Long getMaNhatKyUas() {
        return maNhatKyUas;
    }

    public void setMaNhatKyUas(Long maNhatKyUas) {
        this.maNhatKyUas = maNhatKyUas;
    }

    public Long getMaChuKy() {
        return maChuKy;
    }

    public void setMaChuKy(Long maChuKy) {
        this.maChuKy = maChuKy;
    }

    public Long getMaHoSoMan() {
        return maHoSoMan;
    }

    public void setMaHoSoMan(Long maHoSoMan) {
        this.maHoSoMan = maHoSoMan;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public LocalDate getNgayChamDiem() {
        return ngayChamDiem;
    }

    public void setNgayChamDiem(LocalDate ngayChamDiem) {
        this.ngayChamDiem = ngayChamDiem;
    }

    public Short getNgayThuMayTrongChuKy() {
        return ngayThuMayTrongChuKy;
    }

    public void setNgayThuMayTrongChuKy(Short ngayThuMayTrongChuKy) {
        this.ngayThuMayTrongChuKy = ngayThuMayTrongChuKy;
    }

    public Short getDiemSanPhuHss() {
        return diemSanPhuHss;
    }

    public void setDiemSanPhuHss(Short diemSanPhuHss) {
        this.diemSanPhuHss = diemSanPhuHss;
    }

    public Short getDiemNguaIss() {
        return diemNguaIss;
    }

    public void setDiemNguaIss(Short diemNguaIss) {
        this.diemNguaIss = diemNguaIss;
    }

    public Short getDiemUasNgay() {
        return diemUasNgay;
    }

    public void setDiemUasNgay(Short diemUasNgay) {
        this.diemUasNgay = diemUasNgay;
    }

    public String getTrangThaiDungThuoc() {
        return trangThaiDungThuoc;
    }

    public void setTrangThaiDungThuoc(String trangThaiDungThuoc) {
        this.trangThaiDungThuoc = trangThaiDungThuoc;
    }

    public String getTrangThaiNgay() {
        return trangThaiNgay;
    }

    public void setTrangThaiNgay(String trangThaiNgay) {
        this.trangThaiNgay = trangThaiNgay;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

    public LocalDate getNgayBatDauChuKy() {
        return ngayBatDauChuKy;
    }

    public void setNgayBatDauChuKy(LocalDate ngayBatDauChuKy) {
        this.ngayBatDauChuKy = ngayBatDauChuKy;
    }

}
