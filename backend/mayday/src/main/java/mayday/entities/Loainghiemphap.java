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
@Table(name = "loainghiemphap", schema = "public")
public class Loainghiemphap {

    @Id
    @Column(name = "ma_nghiem_phap", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNghiemPhap;

    @Column(name = "ten_nghiem_phap", nullable = false)
    private String tenNghiemPhap;

    @Column(name = "phuong_phap_thuc_hien")
    private String phuongPhapThucHien;

    @Column(name = "thoi_gian_chuan", precision = 10, scale = 2)
    private BigDecimal thoiGianChuan;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(name = "la_can_nguyen_khac", nullable = false)
    private Boolean laCanNguyenKhac;

    protected Loainghiemphap() {
    }

    public Long getMaNghiemPhap() {
        return maNghiemPhap;
    }

    public void setMaNghiemPhap(Long maNghiemPhap) {
        this.maNghiemPhap = maNghiemPhap;
    }

    public String getTenNghiemPhap() {
        return tenNghiemPhap;
    }

    public void setTenNghiemPhap(String tenNghiemPhap) {
        this.tenNghiemPhap = tenNghiemPhap;
    }

    public String getPhuongPhapThucHien() {
        return phuongPhapThucHien;
    }

    public void setPhuongPhapThucHien(String phuongPhapThucHien) {
        this.phuongPhapThucHien = phuongPhapThucHien;
    }

    public BigDecimal getThoiGianChuan() {
        return thoiGianChuan;
    }

    public void setThoiGianChuan(BigDecimal thoiGianChuan) {
        this.thoiGianChuan = thoiGianChuan;
    }

    public OffsetDateTime getThoiDiemTao() {
        return thoiDiemTao;
    }

    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) {
        this.thoiDiemTao = thoiDiemTao;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

    public Boolean getLaCanNguyenKhac() {
        return laCanNguyenKhac;
    }

    public void setLaCanNguyenKhac(Boolean laCanNguyenKhac) {
        this.laCanNguyenKhac = laCanNguyenKhac;
    }

}
