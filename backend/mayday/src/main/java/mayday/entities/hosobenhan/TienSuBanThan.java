package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "tiensubanthan")
public class TienSuBanThan {
    @Id
    private Long maTienSuBanThan;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private BenhAnMayDayMan benhAnMayDayMan;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    PersonalMedicalHistory personalMedicalHistory;
}