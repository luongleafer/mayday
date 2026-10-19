package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tinhtrangnhiemtrung", schema = "public")
public class Tinhtrangnhiemtrung {

    @Id
    @Column(name = "ma_tinh_trang_nhiem_trung", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTinhTrangNhiemTrung;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "ho_hap_ho")
    private Boolean hoHapHo;

    @Column(name = "ho_hap_dau_hong")
    private Boolean hoHapDauHong;

    @Column(name = "ho_hap_hat_hoi")
    private Boolean hoHapHatHoi;

    @Column(name = "ho_hap_so_mui")
    private Boolean hoHapSoMui;

    @Column(name = "ho_hap_thoi_gian")
    private BigDecimal hoHapThoiGian;

    @Column(name = "ho_hap_khoang_cach")
    private BigDecimal hoHapKhoangCach;

    @Column(name = "tiet_nieu_tieu_rat")
    private Boolean tietNieuTieuRat;

    @Column(name = "tiet_nieu_tieu_buot")
    private Boolean tietNieuTieuBuot;

    @Column(name = "tiet_nieu_thoi_gian")
    private BigDecimal tietNieuThoiGian;

    @Column(name = "tiet_nieu_khoang_cach")
    private BigDecimal tietNieuKhoangCach;

    @Column(name = "da_mun")
    private Boolean daMun;

    @Column(name = "da_nhot")
    private Boolean daNhot;

    @Column(name = "da_thoi_gian")
    private BigDecimal daThoiGian;

    @Column(name = "da_khoang_cach")
    private BigDecimal daKhoangCach;

    @Column(name = "khac_ghi_ro")
    private String khacGhiRo;

    @Column(name = "khac_thoi_gian")
    private BigDecimal khacThoiGian;

    @Column(name = "khac_khoang_cach")
    private BigDecimal khacKhoangCach;

    protected Tinhtrangnhiemtrung() {
    }

    public Long getMaTinhTrangNhiemTrung() {
        return maTinhTrangNhiemTrung;
    }

    public void setMaTinhTrangNhiemTrung(Long maTinhTrangNhiemTrung) {
        this.maTinhTrangNhiemTrung = maTinhTrangNhiemTrung;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Boolean getHoHapHo() {
        return hoHapHo;
    }

    public void setHoHapHo(Boolean hoHapHo) {
        this.hoHapHo = hoHapHo;
    }

    public Boolean getHoHapDauHong() {
        return hoHapDauHong;
    }

    public void setHoHapDauHong(Boolean hoHapDauHong) {
        this.hoHapDauHong = hoHapDauHong;
    }

    public Boolean getHoHapHatHoi() {
        return hoHapHatHoi;
    }

    public void setHoHapHatHoi(Boolean hoHapHatHoi) {
        this.hoHapHatHoi = hoHapHatHoi;
    }

    public Boolean getHoHapSoMui() {
        return hoHapSoMui;
    }

    public void setHoHapSoMui(Boolean hoHapSoMui) {
        this.hoHapSoMui = hoHapSoMui;
    }

    public BigDecimal getHoHapThoiGian() {
        return hoHapThoiGian;
    }

    public void setHoHapThoiGian(BigDecimal hoHapThoiGian) {
        this.hoHapThoiGian = hoHapThoiGian;
    }

    public BigDecimal getHoHapKhoangCach() {
        return hoHapKhoangCach;
    }

    public void setHoHapKhoangCach(BigDecimal hoHapKhoangCach) {
        this.hoHapKhoangCach = hoHapKhoangCach;
    }

    public Boolean getTietNieuTieuRat() {
        return tietNieuTieuRat;
    }

    public void setTietNieuTieuRat(Boolean tietNieuTieuRat) {
        this.tietNieuTieuRat = tietNieuTieuRat;
    }

    public Boolean getTietNieuTieuBuot() {
        return tietNieuTieuBuot;
    }

    public void setTietNieuTieuBuot(Boolean tietNieuTieuBuot) {
        this.tietNieuTieuBuot = tietNieuTieuBuot;
    }

    public BigDecimal getTietNieuThoiGian() {
        return tietNieuThoiGian;
    }

    public void setTietNieuThoiGian(BigDecimal tietNieuThoiGian) {
        this.tietNieuThoiGian = tietNieuThoiGian;
    }

    public BigDecimal getTietNieuKhoangCach() {
        return tietNieuKhoangCach;
    }

    public void setTietNieuKhoangCach(BigDecimal tietNieuKhoangCach) {
        this.tietNieuKhoangCach = tietNieuKhoangCach;
    }

    public Boolean getDaMun() {
        return daMun;
    }

    public void setDaMun(Boolean daMun) {
        this.daMun = daMun;
    }

    public Boolean getDaNhot() {
        return daNhot;
    }

    public void setDaNhot(Boolean daNhot) {
        this.daNhot = daNhot;
    }

    public BigDecimal getDaThoiGian() {
        return daThoiGian;
    }

    public void setDaThoiGian(BigDecimal daThoiGian) {
        this.daThoiGian = daThoiGian;
    }

    public BigDecimal getDaKhoangCach() {
        return daKhoangCach;
    }

    public void setDaKhoangCach(BigDecimal daKhoangCach) {
        this.daKhoangCach = daKhoangCach;
    }

    public String getKhacGhiRo() {
        return khacGhiRo;
    }

    public void setKhacGhiRo(String khacGhiRo) {
        this.khacGhiRo = khacGhiRo;
    }

    public BigDecimal getKhacThoiGian() {
        return khacThoiGian;
    }

    public void setKhacThoiGian(BigDecimal khacThoiGian) {
        this.khacThoiGian = khacThoiGian;
    }

    public BigDecimal getKhacKhoangCach() {
        return khacKhoangCach;
    }

    public void setKhacKhoangCach(BigDecimal khacKhoangCach) {
        this.khacKhoangCach = khacKhoangCach;
    }

}
