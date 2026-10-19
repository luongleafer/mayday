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
@Table(name = "tepdinhkem", schema = "public")
public class Tepdinhkem {

    @Id
    @Column(name = "ma_tep_dinh_kem", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTepDinhKem;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "ma_tai_khoan")
    private Long maTaiKhoan;

    @Column(name = "ten_tep_goc", nullable = false)
    private String tenTepGoc;

    @Column(name = "duong_dan_luu_tru", nullable = false)
    private String duongDanLuuTru;

    @Column(name = "duong_dan_url")
    private String duongDanUrl;

    @Column(name = "dinh_dang_tep")
    private String dinhDangTep;

    @Column(name = "loai_phuong_tien")
    private String loaiPhuongTien;

    @Column(name = "dung_luong_byte")
    private Long dungLuongByte;

    @Column(name = "phan_loai_muc_dich")
    private String phanLoaiMucDich;

    @Column(name = "mo_ta_tep")
    private String moTaTep;

    @Column(name = "diem_do_mo", precision = 6, scale = 2)
    private BigDecimal diemDoMo;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Tepdinhkem() {
    }

    public Long getMaTepDinhKem() {
        return maTepDinhKem;
    }

    public void setMaTepDinhKem(Long maTepDinhKem) {
        this.maTepDinhKem = maTepDinhKem;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Long getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Long maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTepGoc() {
        return tenTepGoc;
    }

    public void setTenTepGoc(String tenTepGoc) {
        this.tenTepGoc = tenTepGoc;
    }

    public String getDuongDanLuuTru() {
        return duongDanLuuTru;
    }

    public void setDuongDanLuuTru(String duongDanLuuTru) {
        this.duongDanLuuTru = duongDanLuuTru;
    }

    public String getDuongDanUrl() {
        return duongDanUrl;
    }

    public void setDuongDanUrl(String duongDanUrl) {
        this.duongDanUrl = duongDanUrl;
    }

    public String getDinhDangTep() {
        return dinhDangTep;
    }

    public void setDinhDangTep(String dinhDangTep) {
        this.dinhDangTep = dinhDangTep;
    }

    public String getLoaiPhuongTien() {
        return loaiPhuongTien;
    }

    public void setLoaiPhuongTien(String loaiPhuongTien) {
        this.loaiPhuongTien = loaiPhuongTien;
    }

    public Long getDungLuongByte() {
        return dungLuongByte;
    }

    public void setDungLuongByte(Long dungLuongByte) {
        this.dungLuongByte = dungLuongByte;
    }

    public String getPhanLoaiMucDich() {
        return phanLoaiMucDich;
    }

    public void setPhanLoaiMucDich(String phanLoaiMucDich) {
        this.phanLoaiMucDich = phanLoaiMucDich;
    }

    public String getMoTaTep() {
        return moTaTep;
    }

    public void setMoTaTep(String moTaTep) {
        this.moTaTep = moTaTep;
    }

    public BigDecimal getDiemDoMo() {
        return diemDoMo;
    }

    public void setDiemDoMo(BigDecimal diemDoMo) {
        this.diemDoMo = diemDoMo;
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
