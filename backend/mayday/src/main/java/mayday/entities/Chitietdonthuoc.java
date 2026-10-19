package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "chitietdonthuoc", schema = "public")
public class Chitietdonthuoc {

    @Id
    @Column(name = "ma_chi_tiet_don_thuoc", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTietDonThuoc;

    @Column(name = "ma_don_thuoc", nullable = false)
    private Long maDonThuoc;

    @Column(name = "ma_thuoc")
    private String maThuoc;

    @Column(name = "ten_thuoc_bo_sung")
    private String tenThuocBoSung;

    @Column(name = "lieu_dung", nullable = false)
    private String lieuDung;

    @Column(name = "thoi_gian_dung", nullable = false)
    private String thoiGianDung;

    @Column(name = "ma_ke_hoach_dieu_tri")
    private String maKeHoachDieuTri;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    protected Chitietdonthuoc() {
    }

    public Long getMaChiTietDonThuoc() {
        return maChiTietDonThuoc;
    }

    public void setMaChiTietDonThuoc(Long maChiTietDonThuoc) {
        this.maChiTietDonThuoc = maChiTietDonThuoc;
    }

    public Long getMaDonThuoc() {
        return maDonThuoc;
    }

    public void setMaDonThuoc(Long maDonThuoc) {
        this.maDonThuoc = maDonThuoc;
    }

    public String getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(String maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuocBoSung() {
        return tenThuocBoSung;
    }

    public void setTenThuocBoSung(String tenThuocBoSung) {
        this.tenThuocBoSung = tenThuocBoSung;
    }

    public String getLieuDung() {
        return lieuDung;
    }

    public void setLieuDung(String lieuDung) {
        this.lieuDung = lieuDung;
    }

    public String getThoiGianDung() {
        return thoiGianDung;
    }

    public void setThoiGianDung(String thoiGianDung) {
        this.thoiGianDung = thoiGianDung;
    }

    public String getMaKeHoachDieuTri() {
        return maKeHoachDieuTri;
    }

    public void setMaKeHoachDieuTri(String maKeHoachDieuTri) {
        this.maKeHoachDieuTri = maKeHoachDieuTri;
    }

    public OffsetDateTime getThoiDiemTao() {
        return thoiDiemTao;
    }

    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) {
        this.thoiDiemTao = thoiDiemTao;
    }

}
