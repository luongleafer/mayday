package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import lombok.*;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.phienkham.PhienKham;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "hosobenhan")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoSoBenhAn {

    @Id
    private Long maHoSo;

    @Column(name = "maBenhNhan", insertable = false, updatable = false)
    private Long maBenhNhan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maBenhNhan", referencedColumnName = "maBenhNhan")
    @JsonIgnore
    private BenhNhan benhNhan;

    @JsonIgnore
    public List<PhienKham> getDanhSachPhienKham() {
        return danhSachPhienKham;
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hoSoBenhAn")
    private List<PhienKham> danhSachPhienKham;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "hoSoBenhAn")
    private HoanCanhKhoiphatDiUng hoanCanhKhoiphatDiUng;

    private String loaiBenhAn;


    @Column(insertable = false, updatable = false)
    private Long maSubtypeCap;

    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL)
    @JsonIgnore
    private BenhAnMayDayCap benhAnMayDayCap;

    @OneToOne(mappedBy = "hoSoBenhAn", cascade = CascadeType.ALL)
    @JsonIgnore
    private BenhAnMayDayMan benhAnMayDayMan;

    @Column(insertable = false, updatable = false)
    private Long maSubtypeMan;

    private String trangThai;

    @Embedded
    @EmbeddedColumnNaming("%s")
    @JsonUnwrapped
    private GenericRecordInformation genericRecordInformation;

}
