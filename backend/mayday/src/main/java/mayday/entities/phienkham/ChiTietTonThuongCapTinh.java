package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "chitiettonthuongcaptinh")
public class ChiTietTonThuongCapTinh {
    @Id
    private Long maChiTietTonThuongCapTinh;

    @Column(insertable = false, updatable = false)
    @JsonIgnore
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    @JsonIgnore
    private PhienKhamCap phienKhamCap;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private AcuteLesionsInformation thongTinTonThuongCapTinh;

    public Long getMaChiTietTonThuongCapTinh() {
        return maChiTietTonThuongCapTinh;
    }

    public void setMaChiTietTonThuongCapTinh(Long maChiTietTonThuongCapTinh) {
        this.maChiTietTonThuongCapTinh = maChiTietTonThuongCapTinh;
    }


    public AcuteLesionsInformation getThongTinTonThuongCapTinh() {
        return thongTinTonThuongCapTinh;
    }

}