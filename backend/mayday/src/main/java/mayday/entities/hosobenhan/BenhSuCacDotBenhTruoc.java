package mayday.entities.hosobenhan;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "benhsucacdotbenhtruoc")
public class BenhSuCacDotBenhTruoc {
    @Id
    private Long maTienSu;

    @Column(name = "ma_ho_so", insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ho_so", referencedColumnName = "ma_ho_so")
    private BenhAnMayDayMan benhAnMayDayMan;

    private Boolean tungCoDotTuongTu;

    private Short tuoiKhoiPhat;

    private Integer soDotTruoc;

    private String kieuDienBien;

    private String bieuHienDotTruoc;

    private Boolean daTungNhapVien;

    private Integer soLanNhapVien;

    private BigDecimal thoiGianTuDotGanNhat;

    private String dienBienCacDotTruoc;

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