package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "ketquacanlamsang", schema = "public")
public class Ketquacanlamsang {

    @Id
    @Column(name = "ma_ket_qua_can_lam_sang", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKetQuaCanLamSang;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "loai_ket_qua_can_lam_sang", nullable = false)
    private String loaiKetQuaCanLamSang;

    @Column(name = "wbc", precision = 12, scale = 4)
    private BigDecimal wbc;

    @Column(name = "crp", precision = 12, scale = 4)
    private BigDecimal crp;

    @Column(name = "ige_toan_phan", precision = 12, scale = 4)
    private BigDecimal igeToanPhan;

    @Column(name = "can_lam_sang_khac_ghi_chu")
    private String canLamSangKhacGhiChu;

    @Column(name = "xet_nghiem_khac")
    private String xetNghiemKhac;

    @Column(name = "thoi_diem_thuc_hien")
    private OffsetDateTime thoiDiemThucHien;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(name = "ma_subtype_cap", insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    protected Ketquacanlamsang() {
    }

    public Long getMaKetQuaCanLamSang() {
        return maKetQuaCanLamSang;
    }

    public void setMaKetQuaCanLamSang(Long maKetQuaCanLamSang) {
        this.maKetQuaCanLamSang = maKetQuaCanLamSang;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public String getLoaiKetQuaCanLamSang() {
        return loaiKetQuaCanLamSang;
    }

    public void setLoaiKetQuaCanLamSang(String loaiKetQuaCanLamSang) {
        this.loaiKetQuaCanLamSang = loaiKetQuaCanLamSang;
    }

    public BigDecimal getWbc() {
        return wbc;
    }

    public void setWbc(BigDecimal wbc) {
        this.wbc = wbc;
    }

    public BigDecimal getCrp() {
        return crp;
    }

    public void setCrp(BigDecimal crp) {
        this.crp = crp;
    }

    public BigDecimal getIgeToanPhan() {
        return igeToanPhan;
    }

    public void setIgeToanPhan(BigDecimal igeToanPhan) {
        this.igeToanPhan = igeToanPhan;
    }

    public String getCanLamSangKhacGhiChu() {
        return canLamSangKhacGhiChu;
    }

    public void setCanLamSangKhacGhiChu(String canLamSangKhacGhiChu) {
        this.canLamSangKhacGhiChu = canLamSangKhacGhiChu;
    }

    public String getXetNghiemKhac() {
        return xetNghiemKhac;
    }

    public void setXetNghiemKhac(String xetNghiemKhac) {
        this.xetNghiemKhac = xetNghiemKhac;
    }

    public OffsetDateTime getThoiDiemThucHien() {
        return thoiDiemThucHien;
    }

    public void setThoiDiemThucHien(OffsetDateTime thoiDiemThucHien) {
        this.thoiDiemThucHien = thoiDiemThucHien;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

    public Long getMaSubtypeCap() {
        return maSubtypeCap;
    }

    public void setMaSubtypeCap(Long maSubtypeCap) {
        this.maSubtypeCap = maSubtypeCap;
    }

    public Long getMaSubtypeMan() {
        return maSubtypeMan;
    }

    public void setMaSubtypeMan(Long maSubtypeMan) {
        this.maSubtypeMan = maSubtypeMan;
    }

}
