package mayday.entities.nguoi;

import jakarta.persistence.*;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @Column(insertable = false, updatable = false)
    private Long maNhanVien;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "maNhanVien", referencedColumnName = "maNguoi")
    private Nguoi nguoi;


    private Long maChiNhanh;

    //TODO: bổ sung mối quan hệ với bảng/thực thể ChiNhanh
}
