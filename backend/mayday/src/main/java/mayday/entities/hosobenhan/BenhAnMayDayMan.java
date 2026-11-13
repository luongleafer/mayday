package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "benhanmaydayman")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhAnMayDayMan {
    @Id
    private Long maHoSo;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    @ColumnDefault("'MAN'")
    private String loaiBenhAn;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private ChronicRecordInformation chronicRecordInformation;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private TienSuBanThan tienSuBanThan;

    @OneToOne(mappedBy = "benhAnMayDayMan")
    private BenhSuCacDotBenhTruoc benhSuCacDotBenhTruoc;

    public PersonalMedicalHistory getPersonalMedicalHistory() {
        return tienSuBanThan != null ? tienSuBanThan.personalMedicalHistory : null;
    }

    public ClinicHistoryDetail getClinicHistoryDetail() {
        return benhSuCacDotBenhTruoc != null ? benhSuCacDotBenhTruoc.clinicHistoryDetail : null;
    }


}