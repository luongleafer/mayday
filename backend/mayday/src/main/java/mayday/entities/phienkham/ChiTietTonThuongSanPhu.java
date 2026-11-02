package mayday.entities.phienkham;

import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "chitiettonthuongsanphu")
public class ChiTietTonThuongSanPhu {
    @Id
    private Long maChiTietTonThuong;

    @Column(updatable = false, insertable = false)
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    private PhienKhamMan phienKhamMan;

    public ThongTinTonThuongSanPhu getThongTinTonThuongSanPhu() {
        return thongTinTonThuongSanPhu;
    }

    @Embedded
    @EmbeddedColumnNaming("%s")
    private ThongTinTonThuongSanPhu thongTinTonThuongSanPhu;

    public Long getMaChiTietTonThuong() {
        return maChiTietTonThuong;
    }

    public void setMaChiTietTonThuong(Long maChiTietTonThuong) {
        this.maChiTietTonThuong = maChiTietTonThuong;
    }
}