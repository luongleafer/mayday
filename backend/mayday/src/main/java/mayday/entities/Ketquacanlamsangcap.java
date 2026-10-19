package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "ketquacanlamsangcap", schema = "public")
public class Ketquacanlamsangcap {

    @Id
    @Column(name = "ma_ket_qua_can_lam_sang", nullable = false)
    private Long maKetQuaCanLamSang;

    @Column(name = "loai_ket_qua_can_lam_sang", insertable = false, updatable = false)
    private String loaiKetQuaCanLamSang;

    @Column(name = "lym", precision = 12, scale = 4)
    private BigDecimal lym;

    @Column(name = "neu", precision = 12, scale = 4)
    private BigDecimal neu;

    @Column(name = "plt", precision = 12, scale = 4)
    private BigDecimal plt;

    @Column(name = "tryptase_cap", precision = 12, scale = 4)
    private BigDecimal tryptaseCap;

    @Column(name = "tryptase_nen", precision = 12, scale = 4)
    private BigDecimal tryptaseNen;

    @Column(name = "nlr", insertable = false, updatable = false)
    private BigDecimal nlr;

    @Column(name = "plr", insertable = false, updatable = false)
    private BigDecimal plr;

    protected Ketquacanlamsangcap() {
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

    public BigDecimal getLym() {
        return lym;
    }

    public void setLym(BigDecimal lym) {
        this.lym = lym;
    }

    public BigDecimal getNeu() {
        return neu;
    }

    public void setNeu(BigDecimal neu) {
        this.neu = neu;
    }

    public BigDecimal getPlt() {
        return plt;
    }

    public void setPlt(BigDecimal plt) {
        this.plt = plt;
    }

    public BigDecimal getTryptaseCap() {
        return tryptaseCap;
    }

    public void setTryptaseCap(BigDecimal tryptaseCap) {
        this.tryptaseCap = tryptaseCap;
    }

    public BigDecimal getTryptaseNen() {
        return tryptaseNen;
    }

    public void setTryptaseNen(BigDecimal tryptaseNen) {
        this.tryptaseNen = tryptaseNen;
    }

    public BigDecimal getNlr() {
        return nlr;
    }

    public void setNlr(BigDecimal nlr) {
        this.nlr = nlr;
    }

    public BigDecimal getPlr() {
        return plr;
    }

    public void setPlr(BigDecimal plr) {
        this.plr = plr;
    }

}
