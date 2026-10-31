package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "benhanmaydayman")
public class BenhAnMayDayMan {
    @Id
    private Long maHoSo;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    @ColumnDefault("'MAN'")
    private String loaiBenhAn;

    @Enumerated(EnumType.STRING)
    private ChanDoanChinhType chanDoanChinh;

    @Enumerated(EnumType.STRING)
    private PhanNhomCsuType phanNhomCsu;

    private Boolean chungDaVeNoi;

    private Boolean mayDayCholinergic;

    private Boolean mayDayDoLanh;

    private Boolean mayDayAdrenergic;

    private Boolean mayDayDoNuoc;

    private Boolean mayDayDoAnhSang;

    private Boolean mayDayCamUngKhac;

    private String mayDayCamUngKhacGhiRo;

    @Enumerated(EnumType.STRING)
    private BieuHienQuaTrinhBenhType bieuHienQuaTrinhBenh;

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public void setLoaiBenhAn(String loaiBenhAn) {
        this.loaiBenhAn = loaiBenhAn;
    }

    public ChanDoanChinhType getChanDoanChinh() {
        return chanDoanChinh;
    }

    public void setChanDoanChinh(ChanDoanChinhType chanDoanChinh) {
        this.chanDoanChinh = chanDoanChinh;
    }

    public PhanNhomCsuType getPhanNhomCsu() {
        return phanNhomCsu;
    }

    public void setPhanNhomCsu(PhanNhomCsuType phanNhomCsu) {
        this.phanNhomCsu = phanNhomCsu;
    }

    public Boolean getChungDaVeNoi() {
        return chungDaVeNoi;
    }

    public void setChungDaVeNoi(Boolean chungDaVeNoi) {
        this.chungDaVeNoi = chungDaVeNoi;
    }

    public Boolean getMayDayCholinergic() {
        return mayDayCholinergic;
    }

    public void setMayDayCholinergic(Boolean mayDayCholinergic) {
        this.mayDayCholinergic = mayDayCholinergic;
    }

    public Boolean getMayDayDoLanh() {
        return mayDayDoLanh;
    }

    public void setMayDayDoLanh(Boolean mayDayDoLanh) {
        this.mayDayDoLanh = mayDayDoLanh;
    }

    public Boolean getMayDayAdrenergic() {
        return mayDayAdrenergic;
    }

    public void setMayDayAdrenergic(Boolean mayDayAdrenergic) {
        this.mayDayAdrenergic = mayDayAdrenergic;
    }

    public Boolean getMayDayDoNuoc() {
        return mayDayDoNuoc;
    }

    public void setMayDayDoNuoc(Boolean mayDayDoNuoc) {
        this.mayDayDoNuoc = mayDayDoNuoc;
    }

    public Boolean getMayDayDoAnhSang() {
        return mayDayDoAnhSang;
    }

    public void setMayDayDoAnhSang(Boolean mayDayDoAnhSang) {
        this.mayDayDoAnhSang = mayDayDoAnhSang;
    }

    public Boolean getMayDayCamUngKhac() {
        return mayDayCamUngKhac;
    }

    public void setMayDayCamUngKhac(Boolean mayDayCamUngKhac) {
        this.mayDayCamUngKhac = mayDayCamUngKhac;
    }

    public String getMayDayCamUngKhacGhiRo() {
        return mayDayCamUngKhacGhiRo;
    }

    public void setMayDayCamUngKhacGhiRo(String mayDayCamUngKhacGhiRo) {
        this.mayDayCamUngKhacGhiRo = mayDayCamUngKhacGhiRo;
    }

    public BieuHienQuaTrinhBenhType getBieuHienQuaTrinhBenh() {
        return bieuHienQuaTrinhBenh;
    }

    public void setBieuHienQuaTrinhBenh(BieuHienQuaTrinhBenhType bieuHienQuaTrinhBenh) {
        this.bieuHienQuaTrinhBenh = bieuHienQuaTrinhBenh;
    }

}