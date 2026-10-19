package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dacdiemsanphu", schema = "public")
public class Dacdiemsanphu {

    @Id
    @Column(name = "ma_dac_diem_san_phu", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maDacDiemSanPhu;

    @Column(name = "ten_dac_diem", nullable = false)
    private String tenDacDiem;

    @Column(name = "mo_ta")
    private String moTa;

    protected Dacdiemsanphu() {
    }

    public Long getMaDacDiemSanPhu() {
        return maDacDiemSanPhu;
    }

    public void setMaDacDiemSanPhu(Long maDacDiemSanPhu) {
        this.maDacDiemSanPhu = maDacDiemSanPhu;
    }

    public String getTenDacDiem() {
        return tenDacDiem;
    }

    public void setTenDacDiem(String tenDacDiem) {
        this.tenDacDiem = tenDacDiem;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
