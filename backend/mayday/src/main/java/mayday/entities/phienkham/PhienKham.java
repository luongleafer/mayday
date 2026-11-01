package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.NhanVien;
import mayday.entities.hosobenhan.LoaiBenhAn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "phienkham")
public class PhienKham {

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    @Id
    private Long maPhienKham;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @JsonIgnore
    public HoSoBenhAn getHoSoBenhAn() {
        return hoSoBenhAn;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    public Long getMaBacSi() {
        return maBacSi;
    }

    @OneToOne(mappedBy = "phienKham", cascade = CascadeType.ALL)
    PhienKhamCap phienKhamCap;

    @OneToOne(mappedBy = "phienKham", cascade = CascadeType.ALL)
    PhienKhamMan phienKhamMan;

    @Column(insertable = false, updatable = false)
    private Long maBacSi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maBacSi", referencedColumnName = "maNhanVien")
    private NhanVien bacSi;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phienKham")
    private List<DauHieuSinhTon> bangDauHieuSinhTon;

    private Long maPhongKham;

    private String loaiBenhAn;

    @Column(updatable = false, insertable = false)
    private Long maSubtypeCap;

    @Column(insertable = false, updatable = false)
    private Long maSubtypeMan;

    private LocalDate ngayKham;

    private Integer lanKhamThu;

    public LoaiBenhAn getLoaiPhienKham() {
        return loaiPhienKham;
    }

    @Enumerated(EnumType.STRING)
    private LoaiBenhAn loaiPhienKham;

    private Boolean luuHuyetThanh;

    private Boolean sot;

    @Column(name = "sot_nhiet_do_c")
    private BigDecimal sotNhietDoC;

    private LocalDate ngayHenTaiKham;

    private String tomTatBuoiKham;

    private String huongXuTriLanSau;

    private String danDo;

    public void setDanDo(String danDo) {
        this.danDo = danDo;
    }

    @JsonIgnore
    public PhienKhamCap getPhienKhamCap() {
        return phienKhamCap;
    }

    @JsonIgnore
    public PhienKhamMan getPhienKhamMan() {
        return phienKhamMan;
    }

    @JsonIgnore
    public NhanVien getBacSi() {
        return bacSi;
    }

    @JsonIgnore
    public List<DauHieuSinhTon> getBangDauHieuSinhTon() {
        return bangDauHieuSinhTon;
    }

    public Long getMaPhongKham() {
        return maPhongKham;
    }

    public LocalDate getNgayKham() {
        return ngayKham;
    }

    public Integer getLanKhamThu() {
        return lanKhamThu;
    }

    public Boolean getLuuHuyetThanh() {
        return luuHuyetThanh;
    }

    public Boolean getSot() {
        return sot;
    }

    public BigDecimal getSotNhietDoC() {
        return sotNhietDoC;
    }

    public LocalDate getNgayHenTaiKham() {
        return ngayHenTaiKham;
    }

    public String getTomTatBuoiKham() {
        return tomTatBuoiKham;
    }

    public String getHuongXuTriLanSau() {
        return huongXuTriLanSau;
    }

    public String getDanDo() {
        return danDo;
    }
}
