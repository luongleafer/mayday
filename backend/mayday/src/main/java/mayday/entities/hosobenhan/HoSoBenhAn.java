package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.phienkham.PhienKham;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "hosobenhan")
public class HoSoBenhAn {

    @Id
    private Long maHoSo;

    @Column(name = "maBenhNhan", insertable = false, updatable = false)
    private Long maBenhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maBenhNhan", referencedColumnName = "maBenhNhan")
    private BenhNhan benhNhan;

    @JsonIgnore
    public List<PhienKham> getDanhSachPhienKham() {
        return danhSachPhienKham;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hoSoBenhAn")
    private List<PhienKham> danhSachPhienKham;

    @OneToOne(mappedBy = "hoSoBenhAn")
    private HoanCanhKhoiphatDiUng hoanCanhKhoiphatDiUng;

    private String loaiBenhAn;


    @Column(insertable = false, updatable = false)
    private Long maSubtypeCap;

    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL)
    private BenhAnMayDayCap benhAnMayDayCap;

    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL)
    private BenhAnMayDayMan benhAnMayDayMan;

    @Column(insertable = false, updatable = false)
    private Long maSubtypeMan;

    private OffsetDateTime thoiDiemKhamLanDau;

    private LocalDate ngayBatDauDieuTriNgoaiTru;

    private LocalDate ngayKetThucDieuTriNgoaiTru;

    private String chanDoanTuyenTruoc;

    private String chanDoanBanDauPhongKham;

    private String ketQuaDieuTriBaoHiem;

    private BigDecimal thoiGianKhoiPhatTuan;

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    private String trangThai;

    private String tienSuGiaDinh;

    private String tienSuGiaDinhGhiRo;

    private Boolean daDieuTriTruocKham;


    public Long getMaHoSo() {
        return maHoSo;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }


    public String getLoaiBenhAn() {
        return loaiBenhAn;
    }

    public Long getMaSubtypeCap() {
        return maSubtypeCap;
    }

    public OffsetDateTime getThoiDiemKhamLanDau() {
        return thoiDiemKhamLanDau;
    }

    public LocalDate getNgayBatDauDieuTriNgoaiTru() {
        return ngayBatDauDieuTriNgoaiTru;
    }

    public LocalDate getNgayKetThucDieuTriNgoaiTru() {
        return ngayKetThucDieuTriNgoaiTru;
    }

    public String getChanDoanTuyenTruoc() {
        return chanDoanTuyenTruoc;
    }

    public String getChanDoanBanDauPhongKham() {
        return chanDoanBanDauPhongKham;
    }

    public String getKetQuaDieuTriBaoHiem() {
        return ketQuaDieuTriBaoHiem;
    }

    public BigDecimal getThoiGianKhoiPhatTuan() {
        return thoiGianKhoiPhatTuan;
    }

    public String getTienSuGiaDinh() {
        return tienSuGiaDinh;
    }

    public String getTienSuGiaDinhGhiRo() {
        return tienSuGiaDinhGhiRo;
    }

    public Boolean getDaDieuTriTruocKham() {
        return daDieuTriTruocKham;
    }

}
