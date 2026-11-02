package mayday.entities.nguoi;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "benhnhan")
public class BenhNhan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maBenhNhan;


    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "maBenhNhan", referencedColumnName = "maNguoi")
    private Nguoi nguoi;

    private String maHis;

    private String ngheNghiep;

    private String danToc;

    private String bhytSoThe;

    private LocalDate bhytGiaTriDen;

    private String nguoiNhaHoTenDiaChi;

    private String nguoiNhaSoDienThoai;
}
