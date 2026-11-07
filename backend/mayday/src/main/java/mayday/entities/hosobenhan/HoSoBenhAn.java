package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.phienkham.PhienKham;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "hosobenhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    private String trangThai;

    private String tienSuGiaDinh;

    private String tienSuGiaDinhGhiRo;

    private Boolean daDieuTriTruocKham;


}
