package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "chukytheodoiuas7", schema = "public")
public class Chukytheodoiuas7 {

    @Id
    @Column(name = "ma_chu_ky", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChuKy;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ngay_bat_dau", nullable = false)
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc", nullable = false)
    private LocalDate ngayKetThuc;

    @Column(name = "trang_thai_chu_ky")
    private String trangThaiChuKy;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Chukytheodoiuas7() {
    }

    public Long getMaChuKy() {
        return maChuKy;
    }

    public void setMaChuKy(Long maChuKy) {
        this.maChuKy = maChuKy;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThaiChuKy() {
        return trangThaiChuKy;
    }

    public void setTrangThaiChuKy(String trangThaiChuKy) {
        this.trangThaiChuKy = trangThaiChuKy;
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

}
