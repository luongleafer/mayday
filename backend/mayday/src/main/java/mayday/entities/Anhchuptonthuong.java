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
@Table(name = "anhchuptonthuong", schema = "public")
public class Anhchuptonthuong {

    @Id
    @Column(name = "ma_anh", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maAnh;

    @Column(name = "ma_tep_dinh_kem", nullable = false)
    private Long maTepDinhKem;

    @Column(name = "ma_dot_ton_thuong_da")
    private Long maDotTonThuongDa;

    @Column(name = "ma_vi_tri_ton_thuong")
    private Long maViTriTonThuong;

    @Column(name = "thoi_diem_chup", nullable = false)
    private OffsetDateTime thoiDiemChup;

    @Column(name = "moc_tien_trien")
    private String mocTienTrien;

    @Column(name = "diem_ngua_nrs")
    private Short diemNguaNrs;

    @Column(name = "diem_dau_nrs")
    private Short diemDauNrs;

    @Column(name = "diem_bong_rat_nrs")
    private Short diemBongRatNrs;

    @Column(name = "so_luong_not_san")
    private Integer soLuongNotSan;

    @Column(name = "chi_so_auas_cuc_bo", precision = 5, scale = 2)
    private BigDecimal chiSoAuasCucBo;

    @Column(name = "url_anh")
    private String urlAnh;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Anhchuptonthuong() {
    }

    public Long getMaAnh() {
        return maAnh;
    }

    public void setMaAnh(Long maAnh) {
        this.maAnh = maAnh;
    }

    public Long getMaTepDinhKem() {
        return maTepDinhKem;
    }

    public void setMaTepDinhKem(Long maTepDinhKem) {
        this.maTepDinhKem = maTepDinhKem;
    }

    public Long getMaDotTonThuongDa() {
        return maDotTonThuongDa;
    }

    public void setMaDotTonThuongDa(Long maDotTonThuongDa) {
        this.maDotTonThuongDa = maDotTonThuongDa;
    }

    public Long getMaViTriTonThuong() {
        return maViTriTonThuong;
    }

    public void setMaViTriTonThuong(Long maViTriTonThuong) {
        this.maViTriTonThuong = maViTriTonThuong;
    }

    public OffsetDateTime getThoiDiemChup() {
        return thoiDiemChup;
    }

    public void setThoiDiemChup(OffsetDateTime thoiDiemChup) {
        this.thoiDiemChup = thoiDiemChup;
    }

    public String getMocTienTrien() {
        return mocTienTrien;
    }

    public void setMocTienTrien(String mocTienTrien) {
        this.mocTienTrien = mocTienTrien;
    }

    public Short getDiemNguaNrs() {
        return diemNguaNrs;
    }

    public void setDiemNguaNrs(Short diemNguaNrs) {
        this.diemNguaNrs = diemNguaNrs;
    }

    public Short getDiemDauNrs() {
        return diemDauNrs;
    }

    public void setDiemDauNrs(Short diemDauNrs) {
        this.diemDauNrs = diemDauNrs;
    }

    public Short getDiemBongRatNrs() {
        return diemBongRatNrs;
    }

    public void setDiemBongRatNrs(Short diemBongRatNrs) {
        this.diemBongRatNrs = diemBongRatNrs;
    }

    public Integer getSoLuongNotSan() {
        return soLuongNotSan;
    }

    public void setSoLuongNotSan(Integer soLuongNotSan) {
        this.soLuongNotSan = soLuongNotSan;
    }

    public BigDecimal getChiSoAuasCucBo() {
        return chiSoAuasCucBo;
    }

    public void setChiSoAuasCucBo(BigDecimal chiSoAuasCucBo) {
        this.chiSoAuasCucBo = chiSoAuasCucBo;
    }

    public String getUrlAnh() {
        return urlAnh;
    }

    public void setUrlAnh(String urlAnh) {
        this.urlAnh = urlAnh;
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
