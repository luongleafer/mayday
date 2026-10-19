package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "benhanmaydaycap", schema = "public")
public class Benhanmaydaycap {

    @Id
    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    @Column(name = "nhap_vien")
    private Boolean nhapVien;

    @Column(name = "cd_soc_phan_ve")
    private Boolean cdSocPhanVe;

    @Column(name = "cd_phan_ve")
    private Boolean cdPhanVe;

    @Column(name = "cd_may_day_cap_nang")
    private Boolean cdMayDayCapNang;

    @Column(name = "cd_may_day_cap_thong_thuong")
    private Boolean cdMayDayCapThongThuong;

    @Column(name = "tien_su_benh_nen_da")
    private String tienSuBenhNenDa;

    @Column(name = "tien_su_benh_nen_ho_hap")
    private String tienSuBenhNenHoHap;

    @Column(name = "tien_su_benh_nen_tuan_hoan")
    private String tienSuBenhNenTuanHoan;

    @Column(name = "tien_su_benh_tim_mach_ghi_ro")
    private String tienSuBenhTimMachGhiRo;

    @Column(name = "tien_su_benh_tieu_hoa_man_tinh_ghi_ro")
    private String tienSuBenhTieuHoaManTinhGhiRo;

    @Column(name = "tien_su_benh_nen_tieu_hoa")
    private String tienSuBenhNenTieuHoa;

    @Column(name = "nhom_yeu_to_nghi_ngo")
    private String nhomYeuToNghiNgo;

    @Column(name = "co_nghi_ngo_dot_nay")
    private Boolean coNghiNgoDotNay;

    @Column(name = "ten_yeu_to_nghi_ngo")
    private String tenYeuToNghiNgo;

    @Column(name = "loai_tien_su")
    private String loaiTienSu;

    @Column(name = "da_tung_phan_ung_truoc_day")
    private Boolean daTungPhanUngTruocDay;

    @Column(name = "thoi_gian_tiep_xuc_den_khoi_phat")
    private Duration thoiGianTiepXucDenKhoiPhat;

    @Column(name = "thoi_gian_keo_dai_tb")
    private Duration thoiGianKeoDaiTb;

    @Column(name = "dot_gan_nhat_cach")
    private Duration dotGanNhatCach;

    @Column(name = "tien_su_phan_ve_ghi_ro")
    private String tienSuPhanVeGhiRo;

    @Column(name = "tien_su_di_ung_ghi_ro")
    private String tienSuDiUngGhiRo;

    @Column(name = "da_dieu_tri_benh_dot_nay")
    private Boolean daDieuTriBenhDotNay;

    @Column(name = "tuoi_lan_dau_bi_benh")
    private Integer tuoiLanDauBiBenh;

    @Column(name = "so_lan_may_day_cap_truoc")
    private Integer soLanMayDayCapTruoc;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(name = "benh_phu")
    private String benhPhu;

    protected Benhanmaydaycap() {
    }

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
