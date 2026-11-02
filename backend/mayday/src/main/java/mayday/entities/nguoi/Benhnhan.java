package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "benhnhan")
@Getter
@Setter
@NoArgsConstructor
public class Benhnhan {

    @Id
    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "ma_benh_nhan",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_benh_nhan_nguoi")
    )
    private Nguoi nguoi;

    @Column(name = "ma_his", length = 50)
    private String maHis;

    @Column(name = "nghe_nghiep", length = 40)
    private String ngheNghiep;

    @Column(name = "dan_toc", length = 50)
    private String danToc;

    @Column(name = "bhyt_so_the", length = 30)
    private String bhytSoThe;

    @Column(name = "bhyt_gia_tri_den")
    private LocalDate bhytGiaTriDen;

    @Column(name = "nguoi_nha_ho_ten_dia_chi", columnDefinition = "text")
    private String nguoiNhaHoTenDiaChi;

    @Column(name = "nguoi_nha_so_dien_thoai", length = 10)
    private String nguoiNhaSoDienThoai;
}