package mayday.entities.phienkham;

import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "chitiettonthuongsanphu")
public class ChiTietTonThuongSanPhu {
    @Id
    private Long maChiTietTonThuong;

    @Column(name = "ma_phien_kham", updatable = false, insertable = false)
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_phien_kham", referencedColumnName = "ma_phien_kham")
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