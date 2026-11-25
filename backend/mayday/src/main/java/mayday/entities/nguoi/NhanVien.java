package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;

@Entity
@Table(name = "nhanvien")
@NoArgsConstructor
@Setter
@Getter
public class NhanVien {
    @Id
    @Column(insertable = false, updatable = false)
    private Long maNhanVien;

    @OneToOne(optional = false)
    @MapsId
    @JoinColumn(name = "maNhanVien", referencedColumnName = "maNguoi", nullable = false)
    private Nguoi nguoi;


    @Column(insertable = false, updatable = false)
    private Long maChiNhanh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maChiNhanh", referencedColumnName = "maChiNhanh")
    private ChiNhanh chiNhanh;

    @Column(insertable = false, updatable = false)
    private Long maKhoaPhong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maKhoaPhong", referencedColumnName = "maKhoaPhong")
    private KhoaPhong khoaPhong;


    @Column(insertable = false, updatable = false)
    private Long maPhongKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhongKham", referencedColumnName = "maPhongKham")
    private PhongKham phongKham;
}
