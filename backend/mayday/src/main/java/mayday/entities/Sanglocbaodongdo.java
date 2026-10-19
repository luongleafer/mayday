package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "sanglocbaodongdo", schema = "public")
public class Sanglocbaodongdo {

    @Id
    @Column(name = "ma_luot_sang_loc", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maLuotSangLoc;

    @Column(name = "ma_phien_ung_dung", nullable = false)
    private UUID maPhienUngDung;

    @Column(name = "ma_benh_nhan")
    private Long maBenhNhan;

    @Column(name = "phien_ban_bo_cau_hoi", nullable = false)
    private String phienBanBoCauHoi;

    @Column(name = "co_kho_tho_kho_khe_nghen_hong", nullable = false)
    private Boolean coKhoThoKhoKheNghenHong;

    @Column(name = "co_chong_mat_tuc_nguc_sap_ngat", nullable = false)
    private Boolean coChongMatTucNgucSapNgat;

    @Column(name = "co_sung_moi_luoi_co_hong", nullable = false)
    private Boolean coSungMoiLuoiCoHong;

    @Column(name = "co_dau_bung_non_lien_tuc", nullable = false)
    private Boolean coDauBungNonLienTuc;

    @Column(name = "co_nguy_co_phan_ve", insertable = false, updatable = false)
    private Boolean coNguyCoPhanVe;

    @Column(name = "ket_qua_dieu_huong", insertable = false, updatable = false)
    private String ketQuaDieuHuong;

    @Column(name = "xac_nhan_muon_goi_cap_cuu")
    private Boolean xacNhanMuonGoiCapCuu;

    @Column(name = "da_nhan_nut_goi_cap_cuu", nullable = false)
    private Boolean daNhanNutGoiCapCuu;

    @Column(name = "so_dien_thoai_cap_cuu")
    private String soDienThoaiCapCuu;

    @Column(name = "thoi_diem_nhan_nut_goi")
    private OffsetDateTime thoiDiemNhanNutGoi;

    @Column(name = "thoi_diem_bat_dau", nullable = false)
    private OffsetDateTime thoiDiemBatDau;

    @Column(name = "thoi_diem_hoan_tat", nullable = false)
    private OffsetDateTime thoiDiemHoanTat;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Sanglocbaodongdo() {
    }

    public Long getMaLuotSangLoc() {
        return maLuotSangLoc;
    }

    public void setMaLuotSangLoc(Long maLuotSangLoc) {
        this.maLuotSangLoc = maLuotSangLoc;
    }

    public UUID getMaPhienUngDung() {
        return maPhienUngDung;
    }

    public void setMaPhienUngDung(UUID maPhienUngDung) {
        this.maPhienUngDung = maPhienUngDung;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }

    public void setMaBenhNhan(Long maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public String getPhienBanBoCauHoi() {
        return phienBanBoCauHoi;
    }

    public void setPhienBanBoCauHoi(String phienBanBoCauHoi) {
        this.phienBanBoCauHoi = phienBanBoCauHoi;
    }

    public Boolean getCoKhoThoKhoKheNghenHong() {
        return coKhoThoKhoKheNghenHong;
    }

    public void setCoKhoThoKhoKheNghenHong(Boolean coKhoThoKhoKheNghenHong) {
        this.coKhoThoKhoKheNghenHong = coKhoThoKhoKheNghenHong;
    }

    public Boolean getCoChongMatTucNgucSapNgat() {
        return coChongMatTucNgucSapNgat;
    }

    public void setCoChongMatTucNgucSapNgat(Boolean coChongMatTucNgucSapNgat) {
        this.coChongMatTucNgucSapNgat = coChongMatTucNgucSapNgat;
    }

    public Boolean getCoSungMoiLuoiCoHong() {
        return coSungMoiLuoiCoHong;
    }

    public void setCoSungMoiLuoiCoHong(Boolean coSungMoiLuoiCoHong) {
        this.coSungMoiLuoiCoHong = coSungMoiLuoiCoHong;
    }

    public Boolean getCoDauBungNonLienTuc() {
        return coDauBungNonLienTuc;
    }

    public void setCoDauBungNonLienTuc(Boolean coDauBungNonLienTuc) {
        this.coDauBungNonLienTuc = coDauBungNonLienTuc;
    }

    public Boolean getCoNguyCoPhanVe() {
        return coNguyCoPhanVe;
    }

    public void setCoNguyCoPhanVe(Boolean coNguyCoPhanVe) {
        this.coNguyCoPhanVe = coNguyCoPhanVe;
    }

    public String getKetQuaDieuHuong() {
        return ketQuaDieuHuong;
    }

    public void setKetQuaDieuHuong(String ketQuaDieuHuong) {
        this.ketQuaDieuHuong = ketQuaDieuHuong;
    }

    public Boolean getXacNhanMuonGoiCapCuu() {
        return xacNhanMuonGoiCapCuu;
    }

    public void setXacNhanMuonGoiCapCuu(Boolean xacNhanMuonGoiCapCuu) {
        this.xacNhanMuonGoiCapCuu = xacNhanMuonGoiCapCuu;
    }

    public Boolean getDaNhanNutGoiCapCuu() {
        return daNhanNutGoiCapCuu;
    }

    public void setDaNhanNutGoiCapCuu(Boolean daNhanNutGoiCapCuu) {
        this.daNhanNutGoiCapCuu = daNhanNutGoiCapCuu;
    }

    public String getSoDienThoaiCapCuu() {
        return soDienThoaiCapCuu;
    }

    public void setSoDienThoaiCapCuu(String soDienThoaiCapCuu) {
        this.soDienThoaiCapCuu = soDienThoaiCapCuu;
    }

    public OffsetDateTime getThoiDiemNhanNutGoi() {
        return thoiDiemNhanNutGoi;
    }

    public void setThoiDiemNhanNutGoi(OffsetDateTime thoiDiemNhanNutGoi) {
        this.thoiDiemNhanNutGoi = thoiDiemNhanNutGoi;
    }

    public OffsetDateTime getThoiDiemBatDau() {
        return thoiDiemBatDau;
    }

    public void setThoiDiemBatDau(OffsetDateTime thoiDiemBatDau) {
        this.thoiDiemBatDau = thoiDiemBatDau;
    }

    public OffsetDateTime getThoiDiemHoanTat() {
        return thoiDiemHoanTat;
    }

    public void setThoiDiemHoanTat(OffsetDateTime thoiDiemHoanTat) {
        this.thoiDiemHoanTat = thoiDiemHoanTat;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
