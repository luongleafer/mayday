package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "chidanngungthuoc", schema = "public")
public class Chidanngungthuoc {

    @Id
    @Column(name = "ma_chi_dan_ngung", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiDanNgung;

    @Column(name = "ma_don_thuoc", nullable = false)
    private Long maDonThuoc;

    @Column(name = "thuoc_can_ngung", nullable = false)
    private String thuocCanNgung;

    @Column(name = "ngung_tu_ngay")
    private LocalDate ngungTuNgay;

    @Column(name = "thoi_gian_ngung_ngay")
    private Integer thoiGianNgungNgay;

    @Column(name = "muc_dich")
    private String mucDich;

    @Column(name = "muc_dich_khac_ghi_ro")
    private String mucDichKhacGhiRo;

    protected Chidanngungthuoc() {
    }

    public Long getMaChiDanNgung() {
        return maChiDanNgung;
    }

    public void setMaChiDanNgung(Long maChiDanNgung) {
        this.maChiDanNgung = maChiDanNgung;
    }

    public Long getMaDonThuoc() {
        return maDonThuoc;
    }

    public void setMaDonThuoc(Long maDonThuoc) {
        this.maDonThuoc = maDonThuoc;
    }

    public String getThuocCanNgung() {
        return thuocCanNgung;
    }

    public void setThuocCanNgung(String thuocCanNgung) {
        this.thuocCanNgung = thuocCanNgung;
    }

    public LocalDate getNgungTuNgay() {
        return ngungTuNgay;
    }

    public void setNgungTuNgay(LocalDate ngungTuNgay) {
        this.ngungTuNgay = ngungTuNgay;
    }

    public Integer getThoiGianNgungNgay() {
        return thoiGianNgungNgay;
    }

    public void setThoiGianNgungNgay(Integer thoiGianNgungNgay) {
        this.thoiGianNgungNgay = thoiGianNgungNgay;
    }

    public String getMucDich() {
        return mucDich;
    }

    public void setMucDich(String mucDich) {
        this.mucDich = mucDich;
    }

    public String getMucDichKhacGhiRo() {
        return mucDichKhacGhiRo;
    }

    public void setMucDichKhacGhiRo(String mucDichKhacGhiRo) {
        this.mucDichKhacGhiRo = mucDichKhacGhiRo;
    }

}
