package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "benhanmaydaycap")
public class BenhAnMayDayCap {
    @Id
    private Long maHoSo;

    @ColumnDefault("'CAP'")
    private String loaiBenhAn;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    private Boolean nhapVien;

    private Boolean cdSocPhanVe;

    private Boolean cdPhanVe;

    private Boolean cdMayDayCapNang;

    private Boolean cdMayDayCapThongThuong;

    private String tienSuBenhNenDa;

    private String tienSuBenhNenHoHap;

    private String tienSuBenhNenTuanHoan;

    private String tienSuBenhTimMachGhiRo;

    private String tienSuBenhTieuHoaManTinhGhiRo;

    private String tienSuBenhNenTieuHoa;

    private String nhomYeuToNghiNgo;

    private Boolean coNghiNgoDotNay;

    private String tenYeuToNghiNgo;

    private String loaiTienSu;

    private Boolean daTungPhanUngTruocDay;

    @Column(columnDefinition = "interval")
    private Duration thoiGianTiepXucDenKhoiPhat;

    @Column(columnDefinition = "interval")
    private Duration thoiGianKeoDaiTb;

    @Column(columnDefinition = "interval")
    private Duration dotGanNhatCach;

    private String tienSuPhanVeGhiRo;

    private String tienSuDiUngGhiRo;

    private Boolean daDieuTriBenhDotNay;

    private Integer tuoiLanDauBiBenh;

    private Integer soLanMayDayCapTruoc;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    private String benhPhu;

    @OneToOne(mappedBy = "benhAnMayDayCap")
    private TinhTrangNhiemTrung tinhTrangNhiemTrung;

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public void setLoaiBenhAn(String loaiBenhAn) {
        this.loaiBenhAn = loaiBenhAn;
    }

    public Boolean getNhapVien() {
        return nhapVien;
    }

    public void setNhapVien(Boolean nhapVien) {
        this.nhapVien = nhapVien;
    }

    public Boolean getCdSocPhanVe() {
        return cdSocPhanVe;
    }

    public void setCdSocPhanVe(Boolean cdSocPhanVe) {
        this.cdSocPhanVe = cdSocPhanVe;
    }

    public Boolean getCdPhanVe() {
        return cdPhanVe;
    }

    public void setCdPhanVe(Boolean cdPhanVe) {
        this.cdPhanVe = cdPhanVe;
    }

    public Boolean getCdMayDayCapNang() {
        return cdMayDayCapNang;
    }

    public void setCdMayDayCapNang(Boolean cdMayDayCapNang) {
        this.cdMayDayCapNang = cdMayDayCapNang;
    }

    public Boolean getCdMayDayCapThongThuong() {
        return cdMayDayCapThongThuong;
    }

    public void setCdMayDayCapThongThuong(Boolean cdMayDayCapThongThuong) {
        this.cdMayDayCapThongThuong = cdMayDayCapThongThuong;
    }

    public String getTienSuBenhNenDa() {
        return tienSuBenhNenDa;
    }

    public void setTienSuBenhNenDa(String tienSuBenhNenDa) {
        this.tienSuBenhNenDa = tienSuBenhNenDa;
    }

    public String getTienSuBenhNenHoHap() {
        return tienSuBenhNenHoHap;
    }

    public void setTienSuBenhNenHoHap(String tienSuBenhNenHoHap) {
        this.tienSuBenhNenHoHap = tienSuBenhNenHoHap;
    }

    public String getTienSuBenhNenTuanHoan() {
        return tienSuBenhNenTuanHoan;
    }

    public void setTienSuBenhNenTuanHoan(String tienSuBenhNenTuanHoan) {
        this.tienSuBenhNenTuanHoan = tienSuBenhNenTuanHoan;
    }

    public String getTienSuBenhTimMachGhiRo() {
        return tienSuBenhTimMachGhiRo;
    }

    public void setTienSuBenhTimMachGhiRo(String tienSuBenhTimMachGhiRo) {
        this.tienSuBenhTimMachGhiRo = tienSuBenhTimMachGhiRo;
    }

    public String getTienSuBenhTieuHoaManTinhGhiRo() {
        return tienSuBenhTieuHoaManTinhGhiRo;
    }

    public void setTienSuBenhTieuHoaManTinhGhiRo(String tienSuBenhTieuHoaManTinhGhiRo) {
        this.tienSuBenhTieuHoaManTinhGhiRo = tienSuBenhTieuHoaManTinhGhiRo;
    }

    public String getTienSuBenhNenTieuHoa() {
        return tienSuBenhNenTieuHoa;
    }

    public void setTienSuBenhNenTieuHoa(String tienSuBenhNenTieuHoa) {
        this.tienSuBenhNenTieuHoa = tienSuBenhNenTieuHoa;
    }

    public String getNhomYeuToNghiNgo() {
        return nhomYeuToNghiNgo;
    }

    public void setNhomYeuToNghiNgo(String nhomYeuToNghiNgo) {
        this.nhomYeuToNghiNgo = nhomYeuToNghiNgo;
    }

    public Boolean getCoNghiNgoDotNay() {
        return coNghiNgoDotNay;
    }

    public void setCoNghiNgoDotNay(Boolean coNghiNgoDotNay) {
        this.coNghiNgoDotNay = coNghiNgoDotNay;
    }

    public String getTenYeuToNghiNgo() {
        return tenYeuToNghiNgo;
    }

    public void setTenYeuToNghiNgo(String tenYeuToNghiNgo) {
        this.tenYeuToNghiNgo = tenYeuToNghiNgo;
    }

    public String getLoaiTienSu() {
        return loaiTienSu;
    }

    public void setLoaiTienSu(String loaiTienSu) {
        this.loaiTienSu = loaiTienSu;
    }

    public Boolean getDaTungPhanUngTruocDay() {
        return daTungPhanUngTruocDay;
    }

    public void setDaTungPhanUngTruocDay(Boolean daTungPhanUngTruocDay) {
        this.daTungPhanUngTruocDay = daTungPhanUngTruocDay;
    }

    public Duration getThoiGianTiepXucDenKhoiPhat() {
        return thoiGianTiepXucDenKhoiPhat;
    }

    public void setThoiGianTiepXucDenKhoiPhat(Duration thoiGianTiepXucDenKhoiPhat) {
        this.thoiGianTiepXucDenKhoiPhat = thoiGianTiepXucDenKhoiPhat;
    }

    public Duration getThoiGianKeoDaiTb() {
        return thoiGianKeoDaiTb;
    }

    public void setThoiGianKeoDaiTb(Duration thoiGianKeoDaiTb) {
        this.thoiGianKeoDaiTb = thoiGianKeoDaiTb;
    }

    public Duration getDotGanNhatCach() {
        return dotGanNhatCach;
    }

    public void setDotGanNhatCach(Duration dotGanNhatCach) {
        this.dotGanNhatCach = dotGanNhatCach;
    }

    public String getTienSuPhanVeGhiRo() {
        return tienSuPhanVeGhiRo;
    }

    public void setTienSuPhanVeGhiRo(String tienSuPhanVeGhiRo) {
        this.tienSuPhanVeGhiRo = tienSuPhanVeGhiRo;
    }

    public String getTienSuDiUngGhiRo() {
        return tienSuDiUngGhiRo;
    }

    public void setTienSuDiUngGhiRo(String tienSuDiUngGhiRo) {
        this.tienSuDiUngGhiRo = tienSuDiUngGhiRo;
    }

    public Boolean getDaDieuTriBenhDotNay() {
        return daDieuTriBenhDotNay;
    }

    public void setDaDieuTriBenhDotNay(Boolean daDieuTriBenhDotNay) {
        this.daDieuTriBenhDotNay = daDieuTriBenhDotNay;
    }

    public Integer getTuoiLanDauBiBenh() {
        return tuoiLanDauBiBenh;
    }

    public void setTuoiLanDauBiBenh(Integer tuoiLanDauBiBenh) {
        this.tuoiLanDauBiBenh = tuoiLanDauBiBenh;
    }

    public Integer getSoLanMayDayCapTruoc() {
        return soLanMayDayCapTruoc;
    }

    public void setSoLanMayDayCapTruoc(Integer soLanMayDayCapTruoc) {
        this.soLanMayDayCapTruoc = soLanMayDayCapTruoc;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

    public String getBenhPhu() {
        return benhPhu;
    }

    public void setBenhPhu(String benhPhu) {
        this.benhPhu = benhPhu;
    }

}