package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.util.List;

@Entity
@Table(name = "phienkhamcap")
public class PhienKhamCap {

    @Id
    @Column(name = "ma_phien_kham", insertable = false, updatable = false)
    private Long maPhienKham;

    @OneToOne(optional = false)
    @JoinColumn(name = "ma_phien_kham", referencedColumnName = "ma_phien_kham")
    PhienKham phienKham;

    @Column(insertable = false, updatable = false)
    private final String loaiBenhAn = "CAP";


    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ThongTinKhamCap thongTinKhamCap;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "phienKhamCap")
    private List<ChiTietTonThuongCapTinh> tonThuongCapTinh;

    public ThongTinKhamCap getThongTinKhamCap() {
        return thongTinKhamCap;
    }

    public List<ChiTietTonThuongCapTinh> getTonThuongCapTinh() {
        return tonThuongCapTinh;
    }
}
