package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.util.List;

@Entity
@Table(name = "phienkhamman")
public class PhienKhamMan {
    @Id
    @Column(name = "ma_phien_kham", insertable = false, updatable = false)
    private Long maPhienKham;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_phien_kham", referencedColumnName = "ma_phien_kham")
    private PhienKham phienKham;

    @Column(insertable = false, updatable = false)
    private final String loaiBenhAn = "MAN";


    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ThongTinKhamMan thongTinKhamMan;

    public ThongTinKhamMan getThongTinKhamMan() {
        return thongTinKhamMan;
    }

    public List<ChiTietTonThuongSanPhu> getTonThuongSanPhu() {
        return tonThuongSanPhu;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phienKhamMan")
    private List<ChiTietTonThuongSanPhu> tonThuongSanPhu;
}
