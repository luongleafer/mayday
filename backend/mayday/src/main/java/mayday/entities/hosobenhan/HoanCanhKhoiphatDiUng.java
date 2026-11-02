package mayday.entities.hosobenhan;

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
    private HoSoBenhAn hoSoBenhAn;

    @Embedded
    @EmbeddedColumnNaming("thuc_an_%s")
    private ThongTinHoanCanhKhoiPhat thucAn;

    @Embedded
    @EmbeddedColumnNaming("thuoc_%s")
    private ThongTinHoanCanhKhoiPhat thuoc;

    @Embedded
    @EmbeddedColumnNaming("con_trung_%s")
    private ThongTinHoanCanhKhoiPhat conTrung;

    @Embedded
    @EmbeddedColumnNaming("khac_%s")
    private ThongTinHoanCanhKhoiPhat khac;

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