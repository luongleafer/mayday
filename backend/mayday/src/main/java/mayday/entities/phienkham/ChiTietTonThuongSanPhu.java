package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "chitiettonthuongsanphu")
public class ChiTietTonThuongSanPhu {
    @Id
    private Long maChiTietTonThuong;

    @Column(updatable = false, insertable = false)
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    @JsonIgnore
    private PhienKhamMan phienKhamMan;

    public WhealsInformation getThongTinTonThuongSanPhu() {
        return thongTinTonThuongSanPhu;
    }

    @Embedded
    @EmbeddedColumnNaming("%s")
    private WhealsInformation thongTinTonThuongSanPhu;

    public Long getMaChiTietTonThuong() {
        return maChiTietTonThuong;
    }

    public void setMaChiTietTonThuong(Long maChiTietTonThuong) {
        this.maChiTietTonThuong = maChiTietTonThuong;
    }
}