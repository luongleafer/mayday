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
public class BenhNhan {

    @Id
    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "ma_benh_nhan",
            referencedColumnName = "ma_nguoi",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_benh_nhan_nguoi")
    )
    private Nguoi nguoi;

    @Column(length = 50)
    private String maHis;

    @Column(length = 40)
    private String ngheNghiep;

    @Column(length = 50)
    private String danToc;

    @Column(length = 30)
    private String bhytSoThe;

    private LocalDate bhytGiaTriDen;

    @Column(columnDefinition = "text")
    private String nguoiNhaHoTenDiaChi;

    @Column(length = 10)
    private String nguoiNhaSoDienThoai;
}
