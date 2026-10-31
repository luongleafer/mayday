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
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    PhienKham phienKham;

    @Column(insertable = false, updatable = false)
    private final String loaiBenhAn = "CAP";

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ThongTinKhamCap thongTinKhamCap;


}
