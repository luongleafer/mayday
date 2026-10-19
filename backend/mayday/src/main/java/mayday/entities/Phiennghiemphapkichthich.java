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
@Table(name = "phiennghiemphapkichthich", schema = "public")
public class Phiennghiemphapkichthich {

    @Id
    @Column(name = "ma_phien_nghiem_phap", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhienNghiemPhap;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "ngung_khang_histamine")
    private Boolean ngungKhangHistamine;

    @Column(name = "thoi_gian_ngung_histamine", precision = 8, scale = 2)
    private BigDecimal thoiGianNgungHistamine;

    @Column(name = "ngung_corticoid")
    private Boolean ngungCorticoid;

    @Column(name = "thoi_gian_ngung_corticoid", precision = 8, scale = 2)
    private BigDecimal thoiGianNgungCorticoid;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Phiennghiemphapkichthich() {
    }

    public Long getMaPhienNghiemPhap() {
        return maPhienNghiemPhap;
    }

    public void setMaPhienNghiemPhap(Long maPhienNghiemPhap) {
        this.maPhienNghiemPhap = maPhienNghiemPhap;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public Boolean getNgungKhangHistamine() {
        return ngungKhangHistamine;
    }

    public void setNgungKhangHistamine(Boolean ngungKhangHistamine) {
        this.ngungKhangHistamine = ngungKhangHistamine;
    }

    public BigDecimal getThoiGianNgungHistamine() {
        return thoiGianNgungHistamine;
    }

    public void setThoiGianNgungHistamine(BigDecimal thoiGianNgungHistamine) {
        this.thoiGianNgungHistamine = thoiGianNgungHistamine;
    }

    public Boolean getNgungCorticoid() {
        return ngungCorticoid;
    }

    public void setNgungCorticoid(Boolean ngungCorticoid) {
        this.ngungCorticoid = ngungCorticoid;
    }

    public BigDecimal getThoiGianNgungCorticoid() {
        return thoiGianNgungCorticoid;
    }

    public void setThoiGianNgungCorticoid(BigDecimal thoiGianNgungCorticoid) {
        this.thoiGianNgungCorticoid = thoiGianNgungCorticoid;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
