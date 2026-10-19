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
@Table(name = "donthuoc", schema = "public")
public class Donthuoc {

    @Id
    @Column(name = "ma_don_thuoc", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDonThuoc;

    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "ngay_ke_don", nullable = false)
    private LocalDate ngayKeDon;

    @Column(name = "trang_thai_don")
    private String trangThaiDon;

    @Column(name = "ghi_chu_dieu_tri")
    private String ghiChuDieuTri;

    @Column(name = "ngung_thuoc_lan_sau")
    private Boolean ngungThuocLanSau;

    @Column(name = "thuoc_can_ngung_truoc_tai_kham")
    private String thuocCanNgungTruocTaiKham;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    @Column(name = "ma_phien_kham", nullable = false)
    private Long maPhienKham;

    protected Donthuoc() {
    }

    public Long getMaDonThuoc() {
        return maDonThuoc;
    }

    public void setMaDonThuoc(Long maDonThuoc) {
        this.maDonThuoc = maDonThuoc;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public LocalDate getNgayKeDon() {
        return ngayKeDon;
    }

    public void setNgayKeDon(LocalDate ngayKeDon) {
        this.ngayKeDon = ngayKeDon;
    }

    public String getTrangThaiDon() {
        return trangThaiDon;
    }

    public void setTrangThaiDon(String trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }

    public String getGhiChuDieuTri() {
        return ghiChuDieuTri;
    }

    public void setGhiChuDieuTri(String ghiChuDieuTri) {
        this.ghiChuDieuTri = ghiChuDieuTri;
    }

    public Boolean getNgungThuocLanSau() {
        return ngungThuocLanSau;
    }

    public void setNgungThuocLanSau(Boolean ngungThuocLanSau) {
        this.ngungThuocLanSau = ngungThuocLanSau;
    }

    public String getThuocCanNgungTruocTaiKham() {
        return thuocCanNgungTruocTaiKham;
    }

    public void setThuocCanNgungTruocTaiKham(String thuocCanNgungTruocTaiKham) {
        this.thuocCanNgungTruocTaiKham = thuocCanNgungTruocTaiKham;
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

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

}
