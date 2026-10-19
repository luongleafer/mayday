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
@Table(name = "nguoi", schema = "public")
public class Nguoi {

    @Id
    @Column(name = "ma_nguoi", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNguoi;

    @Column(name = "ho_ten", nullable = false)
    private String hoTen;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "so_dien_thoai", nullable = false)
    private String soDienThoai;

    @Column(name = "dia_chi_tinh_tp")
    private String diaChiTinhTp;

    @Column(name = "dia_chi_xa_phuong")
    private String diaChiXaPhuong;

    @Column(name = "dia_chi_so_nha_thon_pho")
    private String diaChiSoNhaThonPho;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Nguoi() {
    }

    public Long getMaNguoi() {
        return maNguoi;
    }

    public void setMaNguoi(Long maNguoi) {
        this.maNguoi = maNguoi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChiTinhTp() {
        return diaChiTinhTp;
    }

    public void setDiaChiTinhTp(String diaChiTinhTp) {
        this.diaChiTinhTp = diaChiTinhTp;
    }

    public String getDiaChiXaPhuong() {
        return diaChiXaPhuong;
    }

    public void setDiaChiXaPhuong(String diaChiXaPhuong) {
        this.diaChiXaPhuong = diaChiXaPhuong;
    }

    public String getDiaChiSoNhaThonPho() {
        return diaChiSoNhaThonPho;
    }

    public void setDiaChiSoNhaThonPho(String diaChiSoNhaThonPho) {
        this.diaChiSoNhaThonPho = diaChiSoNhaThonPho;
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
