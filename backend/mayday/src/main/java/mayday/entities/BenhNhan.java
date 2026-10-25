package mayday.entities;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "benhnhan")
public class BenhNhan {

    @Id
    @Column(name = "ma_benh_nhan")
    @GeneratedValue
    private Long maBenhNhan;


    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_benh_nhan", referencedColumnName = "ma_nguoi")
    private Nguoi nguoi;

    @Column(name = "ma_his")
    private String maHis;

    @Column(name = "nghe_nghiep")
    private String ngheNghiep;

    @Column(name = "dan_toc")
    private String danToc;

    @Column(name = "bhyt_so_the")
    private String bhytSoThe;

    @Column(name = "bhyt_gia_tri_den")
    private Date bhytGiaTriDen;

    @Column(name = "nguoi_nha_ho_ten_dia_chi")
    private String nguoiNhaHoTenDiaChi;

    @Column(name = "nguoi_nha_so_dien_thoai")
    private String nguoiNhaSoDienThoai;
}
