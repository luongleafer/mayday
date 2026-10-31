package mayday.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "hosobenhan")
public class HoSoBenhAn {

    @Id
    @Column(name = "ma_ho_so")
    private Long maHoSo;

    @Column(name = "ma_benh_nhan", insertable = false, updatable = false)
    private Long maBenhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_benh_nhan", referencedColumnName = "ma_benh_nhan")
    private BenhNhan benhNhan;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hoSoBenhAn")
    private List<PhienKham> danhSachPhienKham;

    @Column(name = "loai_benh_an")
    private String loaiBenhAn;

    @Column(name = "ma_subtype_cap", insertable = false, updatable = false)
    private Long maSubtypeCap;

    @Column(name = "ma_subtype_man", insertable = false, updatable = false)
    private Long maSubtypeMan;

    @Column(name = "thoi_diem_kham_lan_dau")
    private OffsetDateTime thoiDiemKhamLanDau;

    @Column(name = "ngay_bat_dau_dieu_tri_ngoai_tru")
    private LocalDate ngayBatDauDieuTriNgoaiTru;

    @Column(name = "ngay_ket_thuc_dieu_tri_ngoai_tru")
    private LocalDate ngayKetThucDieuTriNgoaiTru;

    @Column(name = "chan_doan_tuyen_truoc")
    private String chanDoanTuyenTruoc;

    @Column(name = "chan_doan_ban_dau_phong_kham")
    private String chanDoanBanDauPhongKham;

    @Column(name = "ket_qua_dieu_tri_bao_hiem")
    private String ketQuaDieuTriBaoHiem;

    @Column(name = "thoi_gian_khoi_phat_tuan")
    private BigDecimal thoiGianKhoiPhatTuan;

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "tien_su_gia_dinh")
    private String tienSuGiaDinh;

    @Column(name = "tien_su_gia_dinh_ghi_ro")
    private String tienSuGiaDinhGhiRo;

    @Column(name = "da_dieu_tri_truoc_kham")
    private Boolean daDieuTriTruocKham;


    public Long getMaHoSo() {
        return maHoSo;
    }

    public Long getMaBenhNhan() {
        return maBenhNhan;
    }
}
