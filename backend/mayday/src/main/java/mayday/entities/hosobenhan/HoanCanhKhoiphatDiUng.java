package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "hoancanhkhoiphatdiung")
@Getter
public class HoanCanhKhoiphatDiUng {
    @Id
    private Long maHoanCanhKhoiPhat;

    @Column(insertable = false, updatable = false)
    @JsonIgnore
    private Long maHoSo;

    @OneToOne
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    @JsonIgnore
    private HoSoBenhAn hoSoBenhAn;

    @Embedded
    @EmbeddedColumnNaming("thuc_an_%s")
    @JsonUnwrapped(prefix = "thucAn")
    private AllergyTrigger thucAn;

    @Embedded
    @EmbeddedColumnNaming("thuoc_%s")
    @JsonUnwrapped(prefix = "thuoc")
    private AllergyTrigger thuoc;

    @Embedded
    @EmbeddedColumnNaming("con_trung_%s")
    @JsonUnwrapped(prefix = "conTrung")
    private AllergyTrigger conTrung;

    @Embedded
    @EmbeddedColumnNaming("khac_%s")
    @JsonUnwrapped(prefix = "khac")
    private AllergyTrigger khac;

}