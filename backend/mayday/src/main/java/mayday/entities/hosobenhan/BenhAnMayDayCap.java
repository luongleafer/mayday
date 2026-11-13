package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Table(name = "benhanmaydaycap")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BenhAnMayDayCap {
    @Id
    private Long maHoSo;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private AcuteRecordInformation acuteRecordInformation;

    @ColumnDefault("'CAP'")
    private String loaiBenhAn;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;


    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;


    @OneToOne(mappedBy = "benhAnMayDayCap")
    private TinhTrangNhiemTrung tinhTrangNhiemTrung;

    public InfectionStatus getInfectionStatus() {
        return tinhTrangNhiemTrung != null ? tinhTrangNhiemTrung.getInfectionStatus() : null;
    }


}