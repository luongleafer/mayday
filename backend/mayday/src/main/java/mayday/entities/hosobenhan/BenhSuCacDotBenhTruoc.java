package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "benhsucacdotbenhtruoc")
public class BenhSuCacDotBenhTruoc {
    @Id
    private Long maTienSu;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private BenhAnMayDayMan benhAnMayDayMan;


    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    ClinicHistoryDetail clinicHistoryDetail;

}