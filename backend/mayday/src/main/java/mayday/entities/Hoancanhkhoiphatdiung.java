package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "hoancanhkhoiphatdiung", schema = "public")
public class Hoancanhkhoiphatdiung {

    @Id
    @Column(name = "ma_hoan_canh_khoi_phat", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoanCanhKhoiPhat;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "thuc_an_nghi_ngo")
    private String thucAnNghiNgo;

    @Column(name = "thuc_an_thoi_gian")
    private BigDecimal thucAnThoiGian;

    @Column(name = "thuc_an_tung_bi")
    private Boolean thucAnTungBi;

    @Column(name = "thuc_an_loai_tien_su")
    private String thucAnLoaiTienSu;

    @Column(name = "thuc_an_tien_su_ghi_ro")
    private String thucAnTienSuGhiRo;

    @Column(name = "thuoc_nghi_ngo")
    private String thuocNghiNgo;

    @Column(name = "thuoc_thoi_gian")
    private BigDecimal thuocThoiGian;

    @Column(name = "thuoc_tung_bi")
    private Boolean thuocTungBi;

    @Column(name = "thuoc_loai_tien_su")
    private String thuocLoaiTienSu;

    @Column(name = "thuoc_tien_su_ghi_ro")
    private String thuocTienSuGhiRo;

    @Column(name = "con_trung_nghi_ngo")
    private String conTrungNghiNgo;

    @Column(name = "con_trung_thoi_gian")
    private BigDecimal conTrungThoiGian;

    @Column(name = "con_trung_tung_bi")
    private Boolean conTrungTungBi;

    @Column(name = "con_trung_loai_tien_su")
    private String conTrungLoaiTienSu;

    @Column(name = "con_trung_tien_su_ghi_ro")
    private String conTrungTienSuGhiRo;

    @Column(name = "khac_nghi_ngo")
    private String khacNghiNgo;

    @Column(name = "khac_thoi_gian")
    private BigDecimal khacThoiGian;

    @Column(name = "khac_tung_bi")
    private Boolean khacTungBi;

    @Column(name = "khac_loai_tien_su")
    private String khacLoaiTienSu;

    @Column(name = "khac_tien_su_ghi_ro")
    private String khacTienSuGhiRo;

    protected Hoancanhkhoiphatdiung() {
    }

    public Long getMaHoanCanhKhoiPhat() {
        return maHoanCanhKhoiPhat;
    }

    public void setMaHoanCanhKhoiPhat(Long maHoanCanhKhoiPhat) {
        this.maHoanCanhKhoiPhat = maHoanCanhKhoiPhat;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public String getThucAnNghiNgo() {
        return thucAnNghiNgo;
    }

    public void setThucAnNghiNgo(String thucAnNghiNgo) {
        this.thucAnNghiNgo = thucAnNghiNgo;
    }

    public BigDecimal getThucAnThoiGian() {
        return thucAnThoiGian;
    }

    public void setThucAnThoiGian(BigDecimal thucAnThoiGian) {
        this.thucAnThoiGian = thucAnThoiGian;
    }

    public Boolean getThucAnTungBi() {
        return thucAnTungBi;
    }

    public void setThucAnTungBi(Boolean thucAnTungBi) {
        this.thucAnTungBi = thucAnTungBi;
    }

    public String getThucAnLoaiTienSu() {
        return thucAnLoaiTienSu;
    }

    public void setThucAnLoaiTienSu(String thucAnLoaiTienSu) {
        this.thucAnLoaiTienSu = thucAnLoaiTienSu;
    }

    public String getThucAnTienSuGhiRo() {
        return thucAnTienSuGhiRo;
    }

    public void setThucAnTienSuGhiRo(String thucAnTienSuGhiRo) {
        this.thucAnTienSuGhiRo = thucAnTienSuGhiRo;
    }

    public String getThuocNghiNgo() {
        return thuocNghiNgo;
    }

    public void setThuocNghiNgo(String thuocNghiNgo) {
        this.thuocNghiNgo = thuocNghiNgo;
    }

    public BigDecimal getThuocThoiGian() {
        return thuocThoiGian;
    }

    public void setThuocThoiGian(BigDecimal thuocThoiGian) {
        this.thuocThoiGian = thuocThoiGian;
    }

    public Boolean getThuocTungBi() {
        return thuocTungBi;
    }

    public void setThuocTungBi(Boolean thuocTungBi) {
        this.thuocTungBi = thuocTungBi;
    }

    public String getThuocLoaiTienSu() {
        return thuocLoaiTienSu;
    }

    public void setThuocLoaiTienSu(String thuocLoaiTienSu) {
        this.thuocLoaiTienSu = thuocLoaiTienSu;
    }

    public String getThuocTienSuGhiRo() {
        return thuocTienSuGhiRo;
    }

    public void setThuocTienSuGhiRo(String thuocTienSuGhiRo) {
        this.thuocTienSuGhiRo = thuocTienSuGhiRo;
    }

    public String getConTrungNghiNgo() {
        return conTrungNghiNgo;
    }

    public void setConTrungNghiNgo(String conTrungNghiNgo) {
        this.conTrungNghiNgo = conTrungNghiNgo;
    }

    public BigDecimal getConTrungThoiGian() {
        return conTrungThoiGian;
    }

    public void setConTrungThoiGian(BigDecimal conTrungThoiGian) {
        this.conTrungThoiGian = conTrungThoiGian;
    }

    public Boolean getConTrungTungBi() {
        return conTrungTungBi;
    }

    public void setConTrungTungBi(Boolean conTrungTungBi) {
        this.conTrungTungBi = conTrungTungBi;
    }

    public String getConTrungLoaiTienSu() {
        return conTrungLoaiTienSu;
    }

    public void setConTrungLoaiTienSu(String conTrungLoaiTienSu) {
        this.conTrungLoaiTienSu = conTrungLoaiTienSu;
    }

    public String getConTrungTienSuGhiRo() {
        return conTrungTienSuGhiRo;
    }

    public void setConTrungTienSuGhiRo(String conTrungTienSuGhiRo) {
        this.conTrungTienSuGhiRo = conTrungTienSuGhiRo;
    }

    public String getKhacNghiNgo() {
        return khacNghiNgo;
    }

    public void setKhacNghiNgo(String khacNghiNgo) {
        this.khacNghiNgo = khacNghiNgo;
    }

    public BigDecimal getKhacThoiGian() {
        return khacThoiGian;
    }

    public void setKhacThoiGian(BigDecimal khacThoiGian) {
        this.khacThoiGian = khacThoiGian;
    }

    public Boolean getKhacTungBi() {
        return khacTungBi;
    }

    public void setKhacTungBi(Boolean khacTungBi) {
        this.khacTungBi = khacTungBi;
    }

    public String getKhacLoaiTienSu() {
        return khacLoaiTienSu;
    }

    public void setKhacLoaiTienSu(String khacLoaiTienSu) {
        this.khacLoaiTienSu = khacLoaiTienSu;
    }

    public String getKhacTienSuGhiRo() {
        return khacTienSuGhiRo;
    }

    public void setKhacTienSuGhiRo(String khacTienSuGhiRo) {
        this.khacTienSuGhiRo = khacTienSuGhiRo;
    }

}
