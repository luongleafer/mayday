package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;
import tools.jackson.databind.annotation.JsonAppend;

@Entity
@Table(name = "phienkhamman")
public class PhienKhamMan {
    @Id
    private Long maPhienKham;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    private PhienKham phienKham;

    @Column(insertable = false, updatable = false)
    private final String loaiBenhAn = "MAN";


    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ThongTinKhamMan thongTinKhamMan;
}
