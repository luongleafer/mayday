package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "benhsucacdotbenhtruoc", schema = "public")
public class Benhsucacdotbenhtruoc {

    @Id
    @Column(name = "ma_tien_su", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTienSu;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "tung_co_dot_tuong_tu")
    private Boolean tungCoDotTuongTu;

    @Column(name = "tuoi_khoi_phat")
    private Short tuoiKhoiPhat;

    @Column(name = "so_dot_truoc")
    private Integer soDotTruoc;

    @Column(name = "kieu_dien_bien")
    private String kieuDienBien;

    @Column(name = "bieu_hien_dot_truoc")
    private String bieuHienDotTruoc;

    @Column(name = "da_tung_nhap_vien")
    private Boolean daTungNhapVien;

    @Column(name = "so_lan_nhap_vien")
    private Integer soLanNhapVien;

    @Column(name = "thoi_gian_tu_dot_gan_nhat")
    private BigDecimal thoiGianTuDotGanNhat;

    @Column(name = "dien_bien_cac_dot_truoc")
    private String dienBienCacDotTruoc;

    protected Benhsucacdotbenhtruoc() {
    }

    public Long getMaTienSu() {
        return maTienSu;
    }

    public void setMaTienSu(Long maTienSu) {
        this.maTienSu = maTienSu;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Boolean getTungCoDotTuongTu() {
        return tungCoDotTuongTu;
    }

    public void setTungCoDotTuongTu(Boolean tungCoDotTuongTu) {
        this.tungCoDotTuongTu = tungCoDotTuongTu;
    }

    public Short getTuoiKhoiPhat() {
        return tuoiKhoiPhat;
    }

    public void setTuoiKhoiPhat(Short tuoiKhoiPhat) {
        this.tuoiKhoiPhat = tuoiKhoiPhat;
    }

    public Integer getSoDotTruoc() {
        return soDotTruoc;
    }

    public void setSoDotTruoc(Integer soDotTruoc) {
        this.soDotTruoc = soDotTruoc;
    }

    public String getKieuDienBien() {
        return kieuDienBien;
    }

    public void setKieuDienBien(String kieuDienBien) {
        this.kieuDienBien = kieuDienBien;
    }

    public String getBieuHienDotTruoc() {
        return bieuHienDotTruoc;
    }

    public void setBieuHienDotTruoc(String bieuHienDotTruoc) {
        this.bieuHienDotTruoc = bieuHienDotTruoc;
    }

    public Boolean getDaTungNhapVien() {
        return daTungNhapVien;
    }

    public void setDaTungNhapVien(Boolean daTungNhapVien) {
        this.daTungNhapVien = daTungNhapVien;
    }

    public Integer getSoLanNhapVien() {
        return soLanNhapVien;
    }

    public void setSoLanNhapVien(Integer soLanNhapVien) {
        this.soLanNhapVien = soLanNhapVien;
    }

    public BigDecimal getThoiGianTuDotGanNhat() {
        return thoiGianTuDotGanNhat;
    }

    public void setThoiGianTuDotGanNhat(BigDecimal thoiGianTuDotGanNhat) {
        this.thoiGianTuDotGanNhat = thoiGianTuDotGanNhat;
    }

    public String getDienBienCacDotTruoc() {
        return dienBienCacDotTruoc;
    }

    public void setDienBienCacDotTruoc(String dienBienCacDotTruoc) {
        this.dienBienCacDotTruoc = dienBienCacDotTruoc;
    }

}
