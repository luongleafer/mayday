package mayday.entities.phienkham;

import jakarta.persistence.*;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.NhanVien;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "phienkham")
public class PhienKham {

    @Id
    private Long maPhienKham;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

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

    private Long maPhongKham;

    private String loaiBenhAn;

    @Column(updatable = false, insertable = false)
    private Long maSubtypeCap;

    @Column(insertable = false, updatable = false)
    private Long maSubtypeMan;

    private LocalDate ngayKham;

    private Integer lanKhamThu;

    private String loaiPhienKham;

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


}
