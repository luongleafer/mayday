package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "phienkhamman", schema = "public")
public class Phienkhamman {

    @Id
    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    @Column(name = "hien_tai_co_san_phu")
    private Boolean hienTaiCoSanPhu;

    @Column(name = "uu_the_ngau_nhien")
    private Boolean uuTheNgauNhien;

    @Column(name = "vi_tri_san_phu")
    private String viTriSanPhu;

    @Column(name = "yeu_to_lam_nang_sa_phu")
    private String yeuToLamNangSaPhu;

    @Column(name = "hoan_canh_xuat_hien_san_phu")
    private String hoanCanhXuatHienSanPhu;

    @Column(name = "trong_dot_co_phu_mach")
    private Boolean trongDotCoPhuMach;

    @Column(name = "hien_tai_co_phu_mach")
    private Boolean hienTaiCoPhuMach;

    @Column(name = "so_lan_phu_mach_dot_nay")
    private Integer soLanPhuMachDotNay;

    @Column(name = "hoan_canh_xuat_hien_phu_mach")
    private String hoanCanhXuatHienPhuMach;

    @Column(name = "so_lan_phu_mach_nang_phai_nhap_vien")
    private Integer soLanPhuMachNangPhaiNhapVien;

    @Column(name = "thoi_gian_ton_tai_phu_mach_co_dung_thuoc")
    private String thoiGianTonTaiPhuMachCoDungThuoc;

    @Column(name = "thoi_gian_ton_tai_phu_mach_khong_dung_thuoc")
    private String thoiGianTonTaiPhuMachKhongDungThuoc;

    @Column(name = "diem_aas7")
    private Short diemAas7;

    @Column(name = "ben_phu_mach")
    private String benPhuMach;

    @Column(name = "thoi_gian_ngau_nhien_tuan", precision = 12, scale = 2)
    private BigDecimal thoiGianNgauNhienTuan;

    @Column(name = "uu_the_kich_thich")
    private Boolean uuTheKichThich;

    @Column(name = "thoi_gian_kich_thich_tuan", precision = 12, scale = 2)
    private BigDecimal thoiGianKichThichTuan;

    @Column(name = "uu_the_tuong_duong")
    private Boolean uuTheTuongDuong;

    @Column(name = "uu_the_khong_ro")
    private Boolean uuTheKhongRo;

    @Column(name = "thu_tu_xuat_hien_so_voi_san_phu")
    private String thuTuXuatHienSoVoiSanPhu;

    @Column(name = "phu_mach_1_tuan_gan_day")
    private Boolean phuMach1TuanGanDay;

    @Column(name = "chan_doan_tai_kham")
    private String chanDoanTaiKham;

    @Column(name = "khoang_cach_xuat_hien")
    private String khoangCachXuatHien;

    @Column(name = "phu_mi_mat")
    private Boolean phuMiMat;

    @Column(name = "phu_luoi")
    private Boolean phuLuoi;

    @Column(name = "phu_moi")
    private Boolean phuMoi;

    @Column(name = "phu_vung_mat_khac")
    private Boolean phuVungMatKhac;

    @Column(name = "phu_thanh_quan")
    private Boolean phuThanhQuan;

    @Column(name = "phu_ban_chan")
    private Boolean phuBanChan;

    @Column(name = "phu_sinh_duc")
    private Boolean phuSinhDuc;

    @Column(name = "phu_vi_tri_khac")
    private Boolean phuViTriKhac;

    @Column(name = "phu_vi_tri_khac_ghi_ro")
    private String phuViTriKhacGhiRo;

    @Column(name = "phu_ban_tay")
    private Boolean phuBanTay;

    protected Phienkhamman() {
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public void setLoaiBenhAn(String loaiBenhAn) {
        this.loaiBenhAn = loaiBenhAn;
    }

    public Boolean getHienTaiCoSanPhu() {
        return hienTaiCoSanPhu;
    }

    public void setHienTaiCoSanPhu(Boolean hienTaiCoSanPhu) {
        this.hienTaiCoSanPhu = hienTaiCoSanPhu;
    }

    public Boolean getUuTheNgauNhien() {
        return uuTheNgauNhien;
    }

    public void setUuTheNgauNhien(Boolean uuTheNgauNhien) {
        this.uuTheNgauNhien = uuTheNgauNhien;
    }

    public String getViTriSanPhu() {
        return viTriSanPhu;
    }

    public void setViTriSanPhu(String viTriSanPhu) {
        this.viTriSanPhu = viTriSanPhu;
    }

    public String getYeuToLamNangSaPhu() {
        return yeuToLamNangSaPhu;
    }

    public void setYeuToLamNangSaPhu(String yeuToLamNangSaPhu) {
        this.yeuToLamNangSaPhu = yeuToLamNangSaPhu;
    }

    public String getHoanCanhXuatHienSanPhu() {
        return hoanCanhXuatHienSanPhu;
    }

    public void setHoanCanhXuatHienSanPhu(String hoanCanhXuatHienSanPhu) {
        this.hoanCanhXuatHienSanPhu = hoanCanhXuatHienSanPhu;
    }

    public Boolean getTrongDotCoPhuMach() {
        return trongDotCoPhuMach;
    }

    public void setTrongDotCoPhuMach(Boolean trongDotCoPhuMach) {
        this.trongDotCoPhuMach = trongDotCoPhuMach;
    }

    public Boolean getHienTaiCoPhuMach() {
        return hienTaiCoPhuMach;
    }

    public void setHienTaiCoPhuMach(Boolean hienTaiCoPhuMach) {
        this.hienTaiCoPhuMach = hienTaiCoPhuMach;
    }

    public Integer getSoLanPhuMachDotNay() {
        return soLanPhuMachDotNay;
    }

    public void setSoLanPhuMachDotNay(Integer soLanPhuMachDotNay) {
        this.soLanPhuMachDotNay = soLanPhuMachDotNay;
    }

    public String getHoanCanhXuatHienPhuMach() {
        return hoanCanhXuatHienPhuMach;
    }

    public void setHoanCanhXuatHienPhuMach(String hoanCanhXuatHienPhuMach) {
        this.hoanCanhXuatHienPhuMach = hoanCanhXuatHienPhuMach;
    }

    public Integer getSoLanPhuMachNangPhaiNhapVien() {
        return soLanPhuMachNangPhaiNhapVien;
    }

    public void setSoLanPhuMachNangPhaiNhapVien(Integer soLanPhuMachNangPhaiNhapVien) {
        this.soLanPhuMachNangPhaiNhapVien = soLanPhuMachNangPhaiNhapVien;
    }

    public String getThoiGianTonTaiPhuMachCoDungThuoc() {
        return thoiGianTonTaiPhuMachCoDungThuoc;
    }

    public void setThoiGianTonTaiPhuMachCoDungThuoc(String thoiGianTonTaiPhuMachCoDungThuoc) {
        this.thoiGianTonTaiPhuMachCoDungThuoc = thoiGianTonTaiPhuMachCoDungThuoc;
    }

    public String getThoiGianTonTaiPhuMachKhongDungThuoc() {
        return thoiGianTonTaiPhuMachKhongDungThuoc;
    }

    public void setThoiGianTonTaiPhuMachKhongDungThuoc(String thoiGianTonTaiPhuMachKhongDungThuoc) {
        this.thoiGianTonTaiPhuMachKhongDungThuoc = thoiGianTonTaiPhuMachKhongDungThuoc;
    }

    public Short getDiemAas7() {
        return diemAas7;
    }

    public void setDiemAas7(Short diemAas7) {
        this.diemAas7 = diemAas7;
    }

    public String getBenPhuMach() {
        return benPhuMach;
    }

    public void setBenPhuMach(String benPhuMach) {
        this.benPhuMach = benPhuMach;
    }

    public BigDecimal getThoiGianNgauNhienTuan() {
        return thoiGianNgauNhienTuan;
    }

    public void setThoiGianNgauNhienTuan(BigDecimal thoiGianNgauNhienTuan) {
        this.thoiGianNgauNhienTuan = thoiGianNgauNhienTuan;
    }

    public Boolean getUuTheKichThich() {
        return uuTheKichThich;
    }

    public void setUuTheKichThich(Boolean uuTheKichThich) {
        this.uuTheKichThich = uuTheKichThich;
    }

    public BigDecimal getThoiGianKichThichTuan() {
        return thoiGianKichThichTuan;
    }

    public void setThoiGianKichThichTuan(BigDecimal thoiGianKichThichTuan) {
        this.thoiGianKichThichTuan = thoiGianKichThichTuan;
    }

    public Boolean getUuTheTuongDuong() {
        return uuTheTuongDuong;
    }

    public void setUuTheTuongDuong(Boolean uuTheTuongDuong) {
        this.uuTheTuongDuong = uuTheTuongDuong;
    }

    public Boolean getUuTheKhongRo() {
        return uuTheKhongRo;
    }

    public void setUuTheKhongRo(Boolean uuTheKhongRo) {
        this.uuTheKhongRo = uuTheKhongRo;
    }

    public String getThuTuXuatHienSoVoiSanPhu() {
        return thuTuXuatHienSoVoiSanPhu;
    }

    public void setThuTuXuatHienSoVoiSanPhu(String thuTuXuatHienSoVoiSanPhu) {
        this.thuTuXuatHienSoVoiSanPhu = thuTuXuatHienSoVoiSanPhu;
    }

    public Boolean getPhuMach1TuanGanDay() {
        return phuMach1TuanGanDay;
    }

    public void setPhuMach1TuanGanDay(Boolean phuMach1TuanGanDay) {
        this.phuMach1TuanGanDay = phuMach1TuanGanDay;
    }

    public String getChanDoanTaiKham() {
        return chanDoanTaiKham;
    }

    public void setChanDoanTaiKham(String chanDoanTaiKham) {
        this.chanDoanTaiKham = chanDoanTaiKham;
    }

    public String getKhoangCachXuatHien() {
        return khoangCachXuatHien;
    }

    public void setKhoangCachXuatHien(String khoangCachXuatHien) {
        this.khoangCachXuatHien = khoangCachXuatHien;
    }

    public Boolean getPhuMiMat() {
        return phuMiMat;
    }

    public void setPhuMiMat(Boolean phuMiMat) {
        this.phuMiMat = phuMiMat;
    }

    public Boolean getPhuLuoi() {
        return phuLuoi;
    }

    public void setPhuLuoi(Boolean phuLuoi) {
        this.phuLuoi = phuLuoi;
    }

    public Boolean getPhuMoi() {
        return phuMoi;
    }

    public void setPhuMoi(Boolean phuMoi) {
        this.phuMoi = phuMoi;
    }

    public Boolean getPhuVungMatKhac() {
        return phuVungMatKhac;
    }

    public void setPhuVungMatKhac(Boolean phuVungMatKhac) {
        this.phuVungMatKhac = phuVungMatKhac;
    }

    public Boolean getPhuThanhQuan() {
        return phuThanhQuan;
    }

    public void setPhuThanhQuan(Boolean phuThanhQuan) {
        this.phuThanhQuan = phuThanhQuan;
    }

    public Boolean getPhuBanChan() {
        return phuBanChan;
    }

    public void setPhuBanChan(Boolean phuBanChan) {
        this.phuBanChan = phuBanChan;
    }

    public Boolean getPhuSinhDuc() {
        return phuSinhDuc;
    }

    public void setPhuSinhDuc(Boolean phuSinhDuc) {
        this.phuSinhDuc = phuSinhDuc;
    }

    public Boolean getPhuViTriKhac() {
        return phuViTriKhac;
    }

    public void setPhuViTriKhac(Boolean phuViTriKhac) {
        this.phuViTriKhac = phuViTriKhac;
    }

    public String getPhuViTriKhacGhiRo() {
        return phuViTriKhacGhiRo;
    }

    public void setPhuViTriKhacGhiRo(String phuViTriKhacGhiRo) {
        this.phuViTriKhacGhiRo = phuViTriKhacGhiRo;
    }

    public Boolean getPhuBanTay() {
        return phuBanTay;
    }

    public void setPhuBanTay(Boolean phuBanTay) {
        this.phuBanTay = phuBanTay;
    }

}
