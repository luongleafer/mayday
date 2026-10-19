package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiensubanthan", schema = "public")
public class Tiensubanthan {

    @Id
    @Column(name = "ma_tien_su_ban_than", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTienSuBanThan;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "tien_su_co_dia")
    private Boolean tienSuCoDia;

    @Column(name = "tien_su_co_dia_ghi_ro")
    private String tienSuCoDiaGhiRo;

    @Column(name = "tien_su_tuyen_giap")
    private Boolean tienSuTuyenGiap;

    @Column(name = "tien_su_tuyen_giap_ghi_ro")
    private String tienSuTuyenGiapGhiRo;

    @Column(name = "tien_su_tu_mien")
    private Boolean tienSuTuMien;

    @Column(name = "tien_su_tu_mien_ghi_ro")
    private String tienSuTuMienGhiRo;

    @Column(name = "tien_su_di_ung")
    private Boolean tienSuDiUng;

    @Column(name = "tien_su_di_ung_ghi_ro")
    private String tienSuDiUngGhiRo;

    @Column(name = "tien_su_phan_ve")
    private Boolean tienSuPhanVe;

    @Column(name = "tien_su_phan_ve_ghi_ro")
    private String tienSuPhanVeGhiRo;

    @Column(name = "tien_su_viem_nhiem_man")
    private Boolean tienSuViemNhiemMan;

    @Column(name = "tien_su_viem_nhiem_man_ghi_ro")
    private String tienSuViemNhiemManGhiRo;

    @Column(name = "tien_su_benh_khac")
    private Boolean tienSuBenhKhac;

    @Column(name = "tien_su_benh_khac_ghi_ro")
    private String tienSuBenhKhacGhiRo;

    protected Tiensubanthan() {
    }

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
