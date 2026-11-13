package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.EmbeddedColumnNaming;

@Entity
@Table(name = "hoancanhkhoiphatdiung")
public class HoanCanhKhoiphatDiUng {
    @Id
    private Long maHoanCanhKhoiPhat;

    @Column(insertable = false, updatable = false)
    private Long maHoSo;

    @OneToOne
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    @JsonIgnore
    private HoSoBenhAn hoSoBenhAn;

    @Embedded
    @EmbeddedColumnNaming("thuc_an_%s")
    private AllergyTrigger thucAn;

    @Embedded
    @EmbeddedColumnNaming("thuoc_%s")
    private AllergyTrigger thuoc;

    @Embedded
    @EmbeddedColumnNaming("con_trung_%s")
    private AllergyTrigger conTrung;

    @Embedded
    @EmbeddedColumnNaming("khac_%s")
    private AllergyTrigger khac;

    public Long getMaHoanCanhKhoiPhat() {
        return maHoanCanhKhoiPhat;
    }

    public void setMaHoanCanhKhoiPhat(Long maHoanCanhKhoiPhat) {
        this.maHoanCanhKhoiPhat = maHoanCanhKhoiPhat;
    }

    public Long getMaHoSo() {
        return maHoSo;
    }

    public void setMaHoSo(Long maHoSo) {
        this.maHoSo = maHoSo;
    }

}