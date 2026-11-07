package mayday.entities.hosobenhan;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("'CAP'")
    private String loaiBenhAn;

    @OneToOne(optional = false)
    @JoinColumn(name = "maHoSo", referencedColumnName = "maHoSo")
    private HoSoBenhAn hoSoBenhAn;

    private Boolean nhapVien;

    private Boolean cdSocPhanVe;

    private Boolean cdPhanVe;

    private Boolean cdMayDayCapNang;

    private Boolean cdMayDayCapThongThuong;

    private String tienSuBenhNenDa;

    private String tienSuBenhNenHoHap;

    private String tienSuBenhNenTuanHoan;

    private String tienSuBenhTimMachGhiRo;

    private String tienSuBenhTieuHoaManTinhGhiRo;

    private String tienSuBenhNenTieuHoa;

    private String nhomYeuToNghiNgo;

    private Boolean coNghiNgoDotNay;

    private String tenYeuToNghiNgo;

    private String loaiTienSu;

    private Boolean daTungPhanUngTruocDay;

    @Column(columnDefinition = "interval")
    private Duration thoiGianTiepXucDenKhoiPhat;

    @Column(columnDefinition = "interval")
    private Duration thoiGianKeoDaiTb;

    @Column(columnDefinition = "interval")
    private Duration dotGanNhatCach;

    private String tienSuPhanVeGhiRo;

    private String tienSuDiUngGhiRo;

    private Boolean daDieuTriBenhDotNay;

    private Integer tuoiLanDauBiBenh;

    private Integer soLanMayDayCapTruoc;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    private String benhPhu;

    @OneToOne(mappedBy = "benhAnMayDayCap")
    private TinhTrangNhiemTrung tinhTrangNhiemTrung;

}