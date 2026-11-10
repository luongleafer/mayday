package mayday.entities.hosobenhan;

import jakarta.persistence.*;

@Entity
@Table(name = "tiensubanthan")
public class TienSuBanThan {
    @Id
    private Long maTienSuBanThan;

    @Column(name = "ma_ho_so", insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ho_so", referencedColumnName = "ma_ho_so")
    private BenhAnMayDayMan benhAnMayDayMan;

    private Boolean tienSuCoDia;

    private String tienSuCoDiaGhiRo;

    private Boolean tienSuTuyenGiap;

    private String tienSuTuyenGiapGhiRo;

    private Boolean tienSuTuMien;

    private String tienSuTuMienGhiRo;

    private Boolean tienSuDiUng;

    private String tienSuDiUngGhiRo;

    private Boolean tienSuPhanVe;

    private String tienSuPhanVeGhiRo;

    private Boolean tienSuViemNhiemMan;

    private String tienSuViemNhiemManGhiRo;

    private Boolean tienSuBenhKhac;

    private String tienSuBenhKhacGhiRo;

    public Long getMaTienSuBanThan() {
        return maTienSuBanThan;
    }

    public void setMaTienSuBanThan(Long maTienSuBanThan) {
        this.maTienSuBanThan = maTienSuBanThan;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Boolean getTienSuCoDia() {
        return tienSuCoDia;
    }

    public void setTienSuCoDia(Boolean tienSuCoDia) {
        this.tienSuCoDia = tienSuCoDia;
    }

    public String getTienSuCoDiaGhiRo() {
        return tienSuCoDiaGhiRo;
    }

    public void setTienSuCoDiaGhiRo(String tienSuCoDiaGhiRo) {
        this.tienSuCoDiaGhiRo = tienSuCoDiaGhiRo;
    }

    public Boolean getTienSuTuyenGiap() {
        return tienSuTuyenGiap;
    }

    public void setTienSuTuyenGiap(Boolean tienSuTuyenGiap) {
        this.tienSuTuyenGiap = tienSuTuyenGiap;
    }

    public String getTienSuTuyenGiapGhiRo() {
        return tienSuTuyenGiapGhiRo;
    }

    public void setTienSuTuyenGiapGhiRo(String tienSuTuyenGiapGhiRo) {
        this.tienSuTuyenGiapGhiRo = tienSuTuyenGiapGhiRo;
    }

    public Boolean getTienSuTuMien() {
        return tienSuTuMien;
    }

    public void setTienSuTuMien(Boolean tienSuTuMien) {
        this.tienSuTuMien = tienSuTuMien;
    }

    public String getTienSuTuMienGhiRo() {
        return tienSuTuMienGhiRo;
    }

    public void setTienSuTuMienGhiRo(String tienSuTuMienGhiRo) {
        this.tienSuTuMienGhiRo = tienSuTuMienGhiRo;
    }

    public Boolean getTienSuDiUng() {
        return tienSuDiUng;
    }

    public void setTienSuDiUng(Boolean tienSuDiUng) {
        this.tienSuDiUng = tienSuDiUng;
    }

    public String getTienSuDiUngGhiRo() {
        return tienSuDiUngGhiRo;
    }

    public void setTienSuDiUngGhiRo(String tienSuDiUngGhiRo) {
        this.tienSuDiUngGhiRo = tienSuDiUngGhiRo;
    }

    public Boolean getTienSuPhanVe() {
        return tienSuPhanVe;
    }

    public void setTienSuPhanVe(Boolean tienSuPhanVe) {
        this.tienSuPhanVe = tienSuPhanVe;
    }

    public String getTienSuPhanVeGhiRo() {
        return tienSuPhanVeGhiRo;
    }

    public void setTienSuPhanVeGhiRo(String tienSuPhanVeGhiRo) {
        this.tienSuPhanVeGhiRo = tienSuPhanVeGhiRo;
    }

    public Boolean getTienSuViemNhiemMan() {
        return tienSuViemNhiemMan;
    }

    public void setTienSuViemNhiemMan(Boolean tienSuViemNhiemMan) {
        this.tienSuViemNhiemMan = tienSuViemNhiemMan;
    }

    public String getTienSuViemNhiemManGhiRo() {
        return tienSuViemNhiemManGhiRo;
    }

    public void setTienSuViemNhiemManGhiRo(String tienSuViemNhiemManGhiRo) {
        this.tienSuViemNhiemManGhiRo = tienSuViemNhiemManGhiRo;
    }

    public Boolean getTienSuBenhKhac() {
        return tienSuBenhKhac;
    }

    public void setTienSuBenhKhac(Boolean tienSuBenhKhac) {
        this.tienSuBenhKhac = tienSuBenhKhac;
    }

    public String getTienSuBenhKhacGhiRo() {
        return tienSuBenhKhacGhiRo;
    }

    public void setTienSuBenhKhacGhiRo(String tienSuBenhKhacGhiRo) {
        this.tienSuBenhKhacGhiRo = tienSuBenhKhacGhiRo;
    }

}