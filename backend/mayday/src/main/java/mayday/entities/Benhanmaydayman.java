package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "benhanmaydayman", schema = "public")
public class Benhanmaydayman {

    @Id
    @Column(name = "ma_ho_so", nullable = false)
    private Long maHoSo;

    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private String loaiBenhAn;

    @Column(name = "chan_doan_chinh")
    private String chanDoanChinh;

    @Column(name = "phan_nhom_csu")
    private String phanNhomCsu;

    @Column(name = "chung_da_ve_noi")
    private Boolean chungDaVeNoi;

    @Column(name = "may_day_cholinergic")
    private Boolean mayDayCholinergic;

    @Column(name = "may_day_do_lanh")
    private Boolean mayDayDoLanh;

    @Column(name = "may_day_adrenergic")
    private Boolean mayDayAdrenergic;

    @Column(name = "may_day_do_nuoc")
    private Boolean mayDayDoNuoc;

    @Column(name = "may_day_do_anh_sang")
    private Boolean mayDayDoAnhSang;

    @Column(name = "may_day_cam_ung_khac")
    private Boolean mayDayCamUngKhac;

    @Column(name = "may_day_cam_ung_khac_ghi_ro")
    private String mayDayCamUngKhacGhiRo;

    @Column(name = "bieu_hien_qua_trinh_benh")
    private String bieuHienQuaTrinhBenh;

    protected Benhanmaydayman() {
    }

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

    public String getChanDoanChinh() {
        return chanDoanChinh;
    }

    public void setChanDoanChinh(String chanDoanChinh) {
        this.chanDoanChinh = chanDoanChinh;
    }

    public String getPhanNhomCsu() {
        return phanNhomCsu;
    }

    public void setPhanNhomCsu(String phanNhomCsu) {
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

    public String getBieuHienQuaTrinhBenh() {
        return bieuHienQuaTrinhBenh;
    }

    public void setBieuHienQuaTrinhBenh(String bieuHienQuaTrinhBenh) {
        this.bieuHienQuaTrinhBenh = bieuHienQuaTrinhBenh;
    }

}
