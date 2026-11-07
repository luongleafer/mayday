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
    private Long maBenhNhan;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "maBenhNhan",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_benh_nhan_nguoi")
    )
    private Nguoi nguoi;

    private String maHis;

    private String ngheNghiep;

    private String danToc;

    private String bhytSoThe;

    private LocalDate bhytGiaTriDen;

    private String nguoiNhaHoTenDiaChi;

    private String nguoiNhaSoDienThoai;
}