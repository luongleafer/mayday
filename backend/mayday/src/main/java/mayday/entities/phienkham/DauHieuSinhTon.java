package mayday.entities.phienkham;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.EmbeddedColumnNaming;

import java.time.OffsetDateTime;

@Entity
@Table(name = "dauhieusinhton")
public class DauHieuSinhTon {
    @Id
    private Long maDauHieuSinhTon;

    @Column(insertable = false, updatable = false)
    @JsonIgnore
    private Long maPhienKham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maPhienKham", referencedColumnName = "maPhienKham")
    @JsonIgnore
    private PhienKham phienKham;

    private Long maBacSi;

    @JsonUnwrapped
    public VitalSignsInformation getDauHieuSinhTon() {
        return dauHieuSinhTon;
    }

    @Embedded
    @EmbeddedColumnNaming("%s")
    private VitalSignsInformation dauHieuSinhTon;

    @ColumnDefault("CURRENT_TIMESTAMP")
    private OffsetDateTime thoiDiemTao;

    @ColumnDefault("CURRENT_TIMESTAMP")
    private OffsetDateTime thoiDiemCapNhat;

    public Long getMaDauHieuSinhTon() {
        return maDauHieuSinhTon;
    }

    public void setMaDauHieuSinhTon(Long maDauHieuSinhTon) {
        this.maDauHieuSinhTon = maDauHieuSinhTon;
    }

    public Long getMaPhienKham() {
        return maPhienKham;
    }

    public void setMaPhienKham(Long maPhienKham) {
        this.maPhienKham = maPhienKham;
    }

    public Long getMaBacSi() {
        return maBacSi;
    }

    public void setMaBacSi(Long maBacSi) {
        this.maBacSi = maBacSi;
    }


    public OffsetDateTime getThoiDiemTao() {
        return thoiDiemTao;
    }

    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) {
        this.thoiDiemTao = thoiDiemTao;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }


}