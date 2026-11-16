package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.math.BigDecimal;

@Entity
@Table(name = "tinhtrangnhiemtrung")
public class TinhTrangNhiemTrung {
    @Id
    private Long maTinhTrangNhiemTrung;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private BenhAnMayDayCap benhAnMayDayCap;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @Getter
    InfectionStatus infectionStatus;
}