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
@Table(name = "ketquaproms", schema = "public")
public class Ketquaproms {

    @Id
    @Column(name = "ma_ket_qua_proms", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKetQuaProms;

    @Column(name = "ma_phien_kham_man", nullable = false)
    private Long maPhienKhamMan;

    @Column(name = "uct_diem")
    private Short uctDiem;

    @Column(name = "aect_diem")
    private Short aectDiem;

    @Column(name = "act_diem_legacy")
    private Short actDiemLegacy;

    @Column(name = "dlqi_diem")
    private Short dlqiDiem;

    @Column(name = "cu_q2ol_diem", precision = 5, scale = 2)
    private BigDecimal cuQ2olDiem;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Ketquaproms() {
    }

    public Long getMaKetQuaProms() {
        return maKetQuaProms;
    }

    public void setMaKetQuaProms(Long maKetQuaProms) {
        this.maKetQuaProms = maKetQuaProms;
    }

    public Long getMaPhienKhamMan() {
        return maPhienKhamMan;
    }

    public void setMaPhienKhamMan(Long maPhienKhamMan) {
        this.maPhienKhamMan = maPhienKhamMan;
    }

    public Short getUctDiem() {
        return uctDiem;
    }

    public void setUctDiem(Short uctDiem) {
        this.uctDiem = uctDiem;
    }

    public Short getAectDiem() {
        return aectDiem;
    }

    public void setAectDiem(Short aectDiem) {
        this.aectDiem = aectDiem;
    }

    public Short getActDiemLegacy() {
        return actDiemLegacy;
    }

    public void setActDiemLegacy(Short actDiemLegacy) {
        this.actDiemLegacy = actDiemLegacy;
    }

    public Short getDlqiDiem() {
        return dlqiDiem;
    }

    public void setDlqiDiem(Short dlqiDiem) {
        this.dlqiDiem = dlqiDiem;
    }

    public BigDecimal getCuQ2olDiem() {
        return cuQ2olDiem;
    }

    public void setCuQ2olDiem(BigDecimal cuQ2olDiem) {
        this.cuQ2olDiem = cuQ2olDiem;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
