package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "phienkhamcap")
public class PhienKhamCap {

    @Id
    private Long maPhienKham;

    @OneToOne(optional = false)
    @JoinColumn(name = "ma_phien_kham", referencedColumnName = "ma_phien_kham")
    PhienKham phienKham;

    @Column(name = "loai_benh_an", insertable = false, updatable = false)
    private final String loaiBenhAn = "CAP";

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ThongTinKhamCap thongTinKhamCap;


}
