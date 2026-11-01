package mayday.entities.hosobenhan;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tinhtrangnhiemtrung")
public class TinhTrangNhiemTrung {
    @Id
    private Long maTinhTrangNhiemTrung;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private BenhAnMayDayCap benhAnMayDayCap;

    private Boolean hoHapHo;

    private Boolean hoHapDauHong;

    private Boolean hoHapHatHoi;

    private Boolean hoHapSoMui;

    private BigDecimal hoHapThoiGian;

    private BigDecimal hoHapKhoangCach;

    private Boolean tietNieuTieuRat;

    private Boolean tietNieuTieuBuot;

    private BigDecimal tietNieuThoiGian;

    private BigDecimal tietNieuKhoangCach;

    private Boolean daMun;

    private Boolean daNhot;

    private BigDecimal daThoiGian;

    private BigDecimal daKhoangCach;

    private String khacGhiRo;

    private BigDecimal khacThoiGian;

    private BigDecimal khacKhoangCach;

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