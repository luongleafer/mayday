package mayday.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "phienkham")
public class PhienKham {

    @Id
    @Column(name = "ma_phien_kham")
    private Long maPhienKham;

    @Column(name = "ma_ho_so", insertable = false, updatable = false)
    private Long maHoSo;

    public HoSoBenhAn getHoSoBenhAn() {
        return hoSoBenhAn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_ho_so", referencedColumnName = "ma_ho_so")
    private HoSoBenhAn hoSoBenhAn;

    @Column(name = "ma_bac_si", insertable = false, updatable = false)
    private Long maBacSi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_bac_si", referencedColumnName = "ma_nhan_vien")
    private NhanVien bacSi;

    @Column(name = "ma_phong_kham")
    private Long maPhongKham;

    @Column(name = "loai_benh_an")
    private String loaiBenhAn;

    @Column(name = "ma_subtype_cap", updatable = false, insertable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    @Column(name = "ngay_kham")
    private Date ngayKham;

    @Column(name = "lan_kham_thu")
    private Integer lanKhamThu;

    @Column(name = "loai_phien_kham")
    private String loaiPhienKham;

    @Column(name = "luu_huyet_thanh")
    private Boolean luuHuyetThanh;

    @Column(name = "sot")
    private Boolean sot;

    @Column(name = "sot_nhiet_do_c")
    private BigDecimal sotNhietDoC;

    @Column(name = "ngay_hen_tai_kham")
    private Date ngayHenTaiKham;

    @Column(name = "tom_tat_buoi_kham")
    private String tomTatBuoiKham;

    @Column(name = "huong_xu_tri_lan_sau")
    private String huongXuTriLanSau;

    @Column(name = "dan_do")
    private String danDo;

    public void setDanDo(String danDo) {
        this.danDo = danDo;
    }


}
