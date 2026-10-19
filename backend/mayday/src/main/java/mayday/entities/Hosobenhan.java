package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "hosobenhan", schema = "public")
public class Hosobenhan {

    @Id
    @Column(name = "ma_ho_so", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maHoSo;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "loai_benh_an", nullable = false)
    private String loaiBenhAn;

    @Column(name = "ma_subtype_cap", insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    @Column(name = "thoi_diem_kham_lan_dau")
    private OffsetDateTime thoiDiemKhamLanDau;

    @Column(name = "ngay_bat_dau_dieu_tri_ngoai_tru")
    private LocalDate ngayBatDauDieuTriNgoaiTru;

    @Column(name = "ngay_ket_thuc_dieu_tri_ngoai_tru")
    private LocalDate ngayKetThucDieuTriNgoaiTru;

    @Column(name = "chan_doan_tuyen_truoc")
    private String chanDoanTuyenTruoc;

    @Column(name = "chan_doan_ban_dau_phong_kham")
    private String chanDoanBanDauPhongKham;

    @Column(name = "ket_qua_dieu_tri_bao_hiem")
    private String ketQuaDieuTriBaoHiem;

    @Column(name = "thoi_gian_khoi_phat_tuan", precision = 12, scale = 2)
    private BigDecimal thoiGianKhoiPhatTuan;

    @Column(name = "trang_thai", nullable = false)
    private String trangThai;

    @Column(name = "tien_su_gia_dinh")
    private String tienSuGiaDinh;

    @Column(name = "tien_su_gia_dinh_ghi_ro")
    private String tienSuGiaDinhGhiRo;

    @Column(name = "da_dieu_tri_truoc_kham")
    private Boolean daDieuTriTruocKham;

    protected Hosobenhan() {
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public void setLoaiBenhAn(String loaiBenhAn) {
        this.loaiBenhAn = loaiBenhAn;
    }

    public Long getMaSubtypeCap() {
        return maSubtypeCap;
    }

    public void setMaSubtypeCap(Long maSubtypeCap) {
        this.maSubtypeCap = maSubtypeCap;
    }

    public Long getMaSubtypeMan() {
        return maSubtypeMan;
    }

    public void setMaSubtypeMan(Long maSubtypeMan) {
        this.maSubtypeMan = maSubtypeMan;
    }

    public OffsetDateTime getThoiDiemKhamLanDau() {
        return thoiDiemKhamLanDau;
    }

    public void setThoiDiemKhamLanDau(OffsetDateTime thoiDiemKhamLanDau) {
        this.thoiDiemKhamLanDau = thoiDiemKhamLanDau;
    }

    public LocalDate getNgayBatDauDieuTriNgoaiTru() {
        return ngayBatDauDieuTriNgoaiTru;
    }

    public void setNgayBatDauDieuTriNgoaiTru(LocalDate ngayBatDauDieuTriNgoaiTru) {
        this.ngayBatDauDieuTriNgoaiTru = ngayBatDauDieuTriNgoaiTru;
    }

    public LocalDate getNgayKetThucDieuTriNgoaiTru() {
        return ngayKetThucDieuTriNgoaiTru;
    }

    public void setNgayKetThucDieuTriNgoaiTru(LocalDate ngayKetThucDieuTriNgoaiTru) {
        this.ngayKetThucDieuTriNgoaiTru = ngayKetThucDieuTriNgoaiTru;
    }

    public String getChanDoanTuyenTruoc() {
        return chanDoanTuyenTruoc;
    }

    public void setChanDoanTuyenTruoc(String chanDoanTuyenTruoc) {
        this.chanDoanTuyenTruoc = chanDoanTuyenTruoc;
    }

    public String getChanDoanBanDauPhongKham() {
        return chanDoanBanDauPhongKham;
    }

    public void setChanDoanBanDauPhongKham(String chanDoanBanDauPhongKham) {
        this.chanDoanBanDauPhongKham = chanDoanBanDauPhongKham;
    }

    public String getKetQuaDieuTriBaoHiem() {
        return ketQuaDieuTriBaoHiem;
    }

    public void setKetQuaDieuTriBaoHiem(String ketQuaDieuTriBaoHiem) {
        this.ketQuaDieuTriBaoHiem = ketQuaDieuTriBaoHiem;
    }

    public BigDecimal getThoiGianKhoiPhatTuan() {
        return thoiGianKhoiPhatTuan;
    }

    public void setThoiGianKhoiPhatTuan(BigDecimal thoiGianKhoiPhatTuan) {
        this.thoiGianKhoiPhatTuan = thoiGianKhoiPhatTuan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTienSuGiaDinh() {
        return tienSuGiaDinh;
    }

    public void setTienSuGiaDinh(String tienSuGiaDinh) {
        this.tienSuGiaDinh = tienSuGiaDinh;
    }

    public String getTienSuGiaDinhGhiRo() {
        return tienSuGiaDinhGhiRo;
    }

    public void setTienSuGiaDinhGhiRo(String tienSuGiaDinhGhiRo) {
        this.tienSuGiaDinhGhiRo = tienSuGiaDinhGhiRo;
    }

    public Boolean getDaDieuTriTruocKham() {
        return daDieuTriTruocKham;
    }

    public void setDaDieuTriTruocKham(Boolean daDieuTriTruocKham) {
        this.daDieuTriTruocKham = daDieuTriTruocKham;
    }

}
