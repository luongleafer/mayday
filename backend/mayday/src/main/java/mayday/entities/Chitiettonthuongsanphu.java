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
@Table(name = "chitiettonthuongsanphu", schema = "public")
public class Chitiettonthuongsanphu {

    @Id
    @Column(name = "ma_chi_tiet_ton_thuong", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietTonThuong;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    @Column(name = "dang_ton_thuong")
    private String dangTonThuong;

    @Column(name = "kich_thuoc")
    private BigDecimal kichThuoc;

    @Column(name = "thoi_gian_ton_tai")
    private BigDecimal thoiGianTonTai;

    @Column(name = "thoi_diem")
    private OffsetDateTime thoiDiem;

    @Column(name = "tinh_trang_dung_thuoc")
    private String tinhTrangDungThuoc;

    @Column(name = "ma_dac_diem_san_phu")
    private Long maDacDiemSanPhu;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "vi_tri")
    private String viTri;

    protected Chitiettonthuongsanphu() {
    }

    public Long getMaChiTietTonThuong() {
        return maChiTietTonThuong;
    }

    public void setMaChiTietTonThuong(Long maChiTietTonThuong) {
        this.maChiTietTonThuong = maChiTietTonThuong;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public String getDangTonThuong() {
        return dangTonThuong;
    }

    public void setDangTonThuong(String dangTonThuong) {
        this.dangTonThuong = dangTonThuong;
    }

    public BigDecimal getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(BigDecimal kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public BigDecimal getThoiGianTonTai() {
        return thoiGianTonTai;
    }

    public void setThoiGianTonTai(BigDecimal thoiGianTonTai) {
        this.thoiGianTonTai = thoiGianTonTai;
    }

    public OffsetDateTime getThoiDiem() {
        return thoiDiem;
    }

    public void setThoiDiem(OffsetDateTime thoiDiem) {
        this.thoiDiem = thoiDiem;
    }

    public String getTinhTrangDungThuoc() {
        return tinhTrangDungThuoc;
    }

    public void setTinhTrangDungThuoc(String tinhTrangDungThuoc) {
        this.tinhTrangDungThuoc = tinhTrangDungThuoc;
    }

    public Long getMaDacDiemSanPhu() {
        return maDacDiemSanPhu;
    }

    public void setMaDacDiemSanPhu(Long maDacDiemSanPhu) {
        this.maDacDiemSanPhu = maDacDiemSanPhu;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

}
