package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "phienkham", schema = "public")
public class Phienkham {

    @Id
    @Column(name = "ma_phien_kham", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhienKham;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "ma_bac_si", nullable = false)
    private Long maBacSi;

    @Column(name = "ma_phong_kham")
    private Long maPhongKham;

    @Column(name = "loai_benh_an", nullable = false)
    private String loaiBenhAn;

    @Column(name = "ma_subtype_cap", insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    @Column(name = "ngay_kham", nullable = false)
    private LocalDate ngayKham;

    @Column(name = "lan_kham_thu", nullable = false)
    private Integer lanKhamThu;

    @Column(name = "loai_phien_kham")
    private String loaiPhienKham;

    @Column(name = "luu_huyet_thanh")
    private Boolean luuHuyetThanh;

    @Column(name = "sot")
    private Boolean sot;

    @Column(name = "sot_nhiet_do_c", precision = 4, scale = 1)
    private BigDecimal sotNhietDoC;

    @Column(name = "ngay_hen_tai_kham")
    private LocalDate ngayHenTaiKham;

    @Column(name = "tom_tat_buoi_kham")
    private String tomTatBuoiKham;

    @Column(name = "huong_xu_tri_lan_sau")
    private String huongXuTriLanSau;

    @Column(name = "dan_do")
    private String danDo;

    protected Phienkham() {
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Long getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(Long maBacSi) {
        this.maBacSi = maBacSi;
    }

    public Long getMaPhongKham() {
        return maPhongKham;
    }

    public void setMaPhongKham(Long maPhongKham) {
        this.maPhongKham = maPhongKham;
    }

    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public void setLoaiBenhAn(String loaiBenhAn) {
        this.loaiBenhAn = loaiBenhAn;
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

    public LocalDate getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(LocalDate ngayKham) {
        this.ngayKham = ngayKham;
    }

    public Integer getLanKhamThu() {
        return lanKhamThu;
    }

    public void setLanKhamThu(Integer lanKhamThu) {
        this.lanKhamThu = lanKhamThu;
    }

    public String getLoaiPhienKham() {
        return loaiPhienKham;
    }

    public void setLoaiPhienKham(String loaiPhienKham) {
        this.loaiPhienKham = loaiPhienKham;
    }

    public Boolean getLuuHuyetThanh() {
        return luuHuyetThanh;
    }

    public void setLuuHuyetThanh(Boolean luuHuyetThanh) {
        this.luuHuyetThanh = luuHuyetThanh;
    }

    public Boolean getSot() {
        return sot;
    }

    public void setSot(Boolean sot) {
        this.sot = sot;
    }

    public BigDecimal getSotNhietDoC() {
        return sotNhietDoC;
    }

    public void setSotNhietDoC(BigDecimal sotNhietDoC) {
        this.sotNhietDoC = sotNhietDoC;
    }

    public LocalDate getNgayHenTaiKham() {
        return ngayHenTaiKham;
    }

    public void setNgayHenTaiKham(LocalDate ngayHenTaiKham) {
        this.ngayHenTaiKham = ngayHenTaiKham;
    }

    public String getTomTatBuoiKham() {
        return tomTatBuoiKham;
    }

    public void setTomTatBuoiKham(String tomTatBuoiKham) {
        this.tomTatBuoiKham = tomTatBuoiKham;
    }

    public String getHuongXuTriLanSau() {
        return huongXuTriLanSau;
    }

    public void setHuongXuTriLanSau(String huongXuTriLanSau) {
        this.huongXuTriLanSau = huongXuTriLanSau;
    }

    public String getDanDo() {
        return danDo;
    }

    public void setDanDo(String danDo) {
        this.danDo = danDo;
    }

}
