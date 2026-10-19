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
@Table(name = "dauhieusinhton", schema = "public")
public class Dauhieusinhton {

    @Id
    @Column(name = "ma_dau_hieu_sinh_ton", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDauHieuSinhTon;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "ma_bac_si")
    private Long maBacSi;

    @Column(name = "thoi_diem")
    private OffsetDateTime thoiDiem;

    @Column(name = "tri_giac")
    private String triGiac;

    @Column(name = "nhiet_do")
    private BigDecimal nhietDo;

    @Column(name = "spo2")
    private BigDecimal spo2;

    @Column(name = "mach")
    private BigDecimal mach;

    @Column(name = "huyet_ap_tam_thu")
    private BigDecimal huyetApTamThu;

    @Column(name = "huyet_ap_tam_truong")
    private BigDecimal huyetApTamTruong;

    @Column(name = "nhip_tho")
    private BigDecimal nhipTho;

    @Column(name = "bat_thuong_co_quan_khac")
    private Boolean batThuongCoQuanKhac;

    @Column(name = "bat_thuong_co_quan_khac_ghi_ro")
    private String batThuongCoQuanKhacGhiRo;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Dauhieusinhton() {
    }

    public Long getMaDauHieuSinhTon() {
        return maDauHieuSinhTon;
    }

    public void setMaDauHieuSinhTon(Long maDauHieuSinhTon) {
        this.maDauHieuSinhTon = maDauHieuSinhTon;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public Long getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(Long maBacSi) {
        this.maBacSi = maBacSi;
    }

    public OffsetDateTime getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(OffsetDateTime thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    public String getTriGiac() {
        return triGiac;
    }

    public void setTriGiac(String triGiac) {
        this.triGiac = triGiac;
    }

    public BigDecimal getNhietDo() {
        return nhietDo;
    }

    public void setNhietDo(BigDecimal nhietDo) {
        this.nhietDo = nhietDo;
    }

    public BigDecimal getSpo2() {
        return spo2;
    }

    public void setSpo2(BigDecimal spo2) {
        this.spo2 = spo2;
    }

    public BigDecimal getMach() {
        return mach;
    }

    public void setMach(BigDecimal mach) {
        this.mach = mach;
    }

    public BigDecimal getHuyetApTamThu() {
        return huyetApTamThu;
    }

    public void setHuyetApTamThu(BigDecimal huyetApTamThu) {
        this.huyetApTamThu = huyetApTamThu;
    }

    public BigDecimal getHuyetApTamTruong() {
        return huyetApTamTruong;
    }

    public void setHuyetApTamTruong(BigDecimal huyetApTamTruong) {
        this.huyetApTamTruong = huyetApTamTruong;
    }

    public BigDecimal getNhipTho() {
        return nhipTho;
    }

    public void setNhipTho(BigDecimal nhipTho) {
        this.nhipTho = nhipTho;
    }

    public Boolean getBatThuongCoQuanKhac() {
        return batThuongCoQuanKhac;
    }

    public void setBatThuongCoQuanKhac(Boolean batThuongCoQuanKhac) {
        this.batThuongCoQuanKhac = batThuongCoQuanKhac;
    }

    public String getBatThuongCoQuanKhacGhiRo() {
        return batThuongCoQuanKhacGhiRo;
    }

    public void setBatThuongCoQuanKhacGhiRo(String batThuongCoQuanKhacGhiRo) {
        this.batThuongCoQuanKhacGhiRo = batThuongCoQuanKhacGhiRo;
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
