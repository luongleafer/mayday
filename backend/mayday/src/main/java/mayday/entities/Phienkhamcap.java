package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "phienkhamcap", schema = "public")
public class Phienkhamcap {

    @Id
    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    @Column(name = "trieu_chung_da")
    private String trieuChungDa;

    @Column(name = "trieu_chung_ho_hap")
    private String trieuChungHoHap;

    @Column(name = "so_voi_thuong_ngay_ho_hap")
    private String soVoiThuongNgayHoHap;

    @Column(name = "trieu_chung_tuan_hoan")
    private String trieuChungTuanHoan;

    @Column(name = "so_voi_thuong_ngay_tuan_hoan")
    private String soVoiThuongNgayTuanHoan;

    @Column(name = "trieu_chung_tieu_hoa")
    private String trieuChungTieuHoa;

    @Column(name = "so_voi_thuong_ngay_tieu_hoa")
    private String soVoiThuongNgayTieuHoa;

    @Column(name = "co_san_phu")
    private Boolean coSanPhu;

    @Column(name = "co_anh_san_phu")
    private Boolean coAnhSanPhu;

    @Column(name = "so_lan_non")
    private Integer soLanNon;

    protected Phienkhamcap() {
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

    public String getTrieuChungDa() {
        return trieuChungDa;
    }

    public void setTrieuChungDa(String trieuChungDa) {
        this.trieuChungDa = trieuChungDa;
    }

    public String getTrieuChungHoHap() {
        return trieuChungHoHap;
    }

    public void setTrieuChungHoHap(String trieuChungHoHap) {
        this.trieuChungHoHap = trieuChungHoHap;
    }

    public String getSoVoiThuongNgayHoHap() {
        return soVoiThuongNgayHoHap;
    }

    public void setSoVoiThuongNgayHoHap(String soVoiThuongNgayHoHap) {
        this.soVoiThuongNgayHoHap = soVoiThuongNgayHoHap;
    }

    public String getTrieuChungTuanHoan() {
        return trieuChungTuanHoan;
    }

    public void setTrieuChungTuanHoan(String trieuChungTuanHoan) {
        this.trieuChungTuanHoan = trieuChungTuanHoan;
    }

    public String getSoVoiThuongNgayTuanHoan() {
        return soVoiThuongNgayTuanHoan;
    }

    public void setSoVoiThuongNgayTuanHoan(String soVoiThuongNgayTuanHoan) {
        this.soVoiThuongNgayTuanHoan = soVoiThuongNgayTuanHoan;
    }

    public String getTrieuChungTieuHoa() {
        return trieuChungTieuHoa;
    }

    public void setTrieuChungTieuHoa(String trieuChungTieuHoa) {
        this.trieuChungTieuHoa = trieuChungTieuHoa;
    }

    public String getSoVoiThuongNgayTieuHoa() {
        return soVoiThuongNgayTieuHoa;
    }

    public void setSoVoiThuongNgayTieuHoa(String soVoiThuongNgayTieuHoa) {
        this.soVoiThuongNgayTieuHoa = soVoiThuongNgayTieuHoa;
    }

    public Boolean getCoSanPhu() {
        return coSanPhu;
    }

    public void setCoSanPhu(Boolean coSanPhu) {
        this.coSanPhu = coSanPhu;
    }

    public Boolean getCoAnhSanPhu() {
        return coAnhSanPhu;
    }

    public void setCoAnhSanPhu(Boolean coAnhSanPhu) {
        this.coAnhSanPhu = coAnhSanPhu;
    }

    public Integer getSoLanNon() {
        return soLanNon;
    }

    public void setSoLanNon(Integer soLanNon) {
        this.soLanNon = soLanNon;
    }

}
