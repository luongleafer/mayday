package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ketquacanlamsangman", schema = "public")
public class Ketquacanlamsangman {

    @Id
    @Column(name = "ma_ket_qua_can_lam_sang", nullable = false)
    private Long maKetQuaCanLamSang;

    @Column(name = "loai_ket_qua_can_lam_sang", insertable = false, updatable = false)
    private String loaiKetQuaCanLamSang;

    @Column(name = "test_huyet_thanh_tu_than")
    private String testHuyetThanhTuThan;

    @Column(name = "ige_khang_tpo_ket_qua")
    private String igeKhangTpoKetQua;

    @Column(name = "bat_ket_qua")
    private String batKetQua;

    @Column(name = "ige_khang_il24_ket_qua")
    private String igeKhangIl24KetQua;

    @Column(name = "asst_duong_kinh_mm", precision = 12, scale = 4)
    private BigDecimal asstDuongKinhMm;

    @Column(name = "eo", precision = 12, scale = 4)
    private BigDecimal eo;

    @Column(name = "ba_bach_cau", precision = 12, scale = 4)
    private BigDecimal baBachCau;

    @Column(name = "anti_tpo", precision = 12, scale = 4)
    private BigDecimal antiTpo;

    @Column(name = "tsh", precision = 12, scale = 4)
    private BigDecimal tsh;

    @Column(name = "ft4", precision = 12, scale = 4)
    private BigDecimal ft4;

    @Column(name = "ft3", precision = 12, scale = 4)
    private BigDecimal ft3;

    @Column(name = "mau_lang_2h", precision = 12, scale = 4)
    private BigDecimal mauLang2h;

    @Column(name = "mau_lang_1h", precision = 12, scale = 4)
    private BigDecimal mauLang1h;

    @Column(name = "ana_hep2")
    private String anaHep2;

    @Column(name = "sieu_am_tuyen_giap")
    private String sieuAmTuyenGiap;

    protected Ketquacanlamsangman() {
    }

    public Long getMaKetQuaCanLamSang() {
        return maKetQuaCanLamSang;
    }

    public void setMaKetQuaCanLamSang(Long maKetQuaCanLamSang) {
        this.maKetQuaCanLamSang = maKetQuaCanLamSang;
    }

    public String getLoaiKetQuaCanLamSang() {
        return loaiKetQuaCanLamSang;
    }

    public void setLoaiKetQuaCanLamSang(String loaiKetQuaCanLamSang) {
        this.loaiKetQuaCanLamSang = loaiKetQuaCanLamSang;
    }

    public String getTestHuyetThanhTuThan() {
        return testHuyetThanhTuThan;
    }

    public void setTestHuyetThanhTuThan(String testHuyetThanhTuThan) {
        this.testHuyetThanhTuThan = testHuyetThanhTuThan;
    }

    public String getIgeKhangTpoKetQua() {
        return igeKhangTpoKetQua;
    }

    public void setIgeKhangTpoKetQua(String igeKhangTpoKetQua) {
        this.igeKhangTpoKetQua = igeKhangTpoKetQua;
    }

    public String getBatKetQua() {
        return batKetQua;
    }

    public void setBatKetQua(String batKetQua) {
        this.batKetQua = batKetQua;
    }

    public String getIgeKhangIl24KetQua() {
        return igeKhangIl24KetQua;
    }

    public void setIgeKhangIl24KetQua(String igeKhangIl24KetQua) {
        this.igeKhangIl24KetQua = igeKhangIl24KetQua;
    }

    public BigDecimal getAsstDuongKinhMm() {
        return asstDuongKinhMm;
    }

    public void setAsstDuongKinhMm(BigDecimal asstDuongKinhMm) {
        this.asstDuongKinhMm = asstDuongKinhMm;
    }

    public BigDecimal getEo() {
        return eo;
    }

    public void setEo(BigDecimal eo) {
        this.eo = eo;
    }

    public BigDecimal getBaBachCau() {
        return baBachCau;
    }

    public void setBaBachCau(BigDecimal baBachCau) {
        this.baBachCau = baBachCau;
    }

    public BigDecimal getAntiTpo() {
        return antiTpo;
    }

    public void setAntiTpo(BigDecimal antiTpo) {
        this.antiTpo = antiTpo;
    }

    public BigDecimal getTsh() {
        return tsh;
    }

    public void setTsh(BigDecimal tsh) {
        this.tsh = tsh;
    }

    public BigDecimal getFt4() {
        return ft4;
    }

    public void setFt4(BigDecimal ft4) {
        this.ft4 = ft4;
    }

    public BigDecimal getFt3() {
        return ft3;
    }

    public void setFt3(BigDecimal ft3) {
        this.ft3 = ft3;
    }

    public BigDecimal getMauLang2h() {
        return mauLang2h;
    }

    public void setMauLang2h(BigDecimal mauLang2h) {
        this.mauLang2h = mauLang2h;
    }

    public BigDecimal getMauLang1h() {
        return mauLang1h;
    }

    public void setMauLang1h(BigDecimal mauLang1h) {
        this.mauLang1h = mauLang1h;
    }

    public String getAnaHep2() {
        return anaHep2;
    }

    public void setAnaHep2(String anaHep2) {
        this.anaHep2 = anaHep2;
    }

    public String getSieuAmTuyenGiap() {
        return sieuAmTuyenGiap;
    }

    public void setSieuAmTuyenGiap(String sieuAmTuyenGiap) {
        this.sieuAmTuyenGiap = sieuAmTuyenGiap;
    }

}
