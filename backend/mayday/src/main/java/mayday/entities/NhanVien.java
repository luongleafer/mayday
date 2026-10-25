package mayday.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "nhanvien")
public class NhanVien {
    @Id
    @Column(name = "ma_nhan_vien")
    private Long maNhanVien;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_nhan_vien", referencedColumnName = "ma_nguoi")
    private Nguoi nguoi;


    @Column(name = "ma_chi_nhanh")
    private Long maChiNhanh;

    //TODO: bổ sung mối quan hệ với bảng/thực thể ChiNhanh
}
