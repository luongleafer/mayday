package mayday.entities.phienkham;

import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.math.BigDecimal;

@Entity
@Table(name = "chitiettonthuongcaptinh")
public class ChiTietTonThuongCapTinh {
    @Id
    private Long maChiTietTonThuongCapTinh;

    @Column(insertable = false, updatable = false)
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    private PhienKhamCap phienKhamCap;

    @Embedded
    @EmbeddedColumnNaming("%s")
    private ThongTinTonThuongCapTinh thongTinTonThuongCapTinh;

    public Long getMaChiTietTonThuongCapTinh() {
        return maChiTietTonThuongCapTinh;
    }

    public void setMaChiTietTonThuongCapTinh(Long maChiTietTonThuongCapTinh) {
        this.maChiTietTonThuongCapTinh = maChiTietTonThuongCapTinh;
    }


}