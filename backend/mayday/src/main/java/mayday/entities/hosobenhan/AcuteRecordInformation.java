package mayday.entities.hosobenhan;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AcuteRecordInformation {
    Boolean nhapVien;

    Boolean cdSocPhanVe;

    Boolean cdPhanVe;

    Boolean cdMayDayCapNang;

    Boolean cdMayDayCapThongThuong;

    String tienSuBenhNenDa;

    String tienSuBenhNenHoHap;

    String tienSuBenhNenTuanHoan;

    String tienSuBenhTimMachGhiRo;

    String tienSuBenhTieuHoaManTinhGhiRo;

    String tienSuBenhNenTieuHoa;

    String nhomYeuToNghiNgo;

    Boolean coNghiNgoDotNay;

    String tenYeuToNghiNgo;

    String loaiTienSu;

    Boolean daTungPhanUngTruocDay;

    @Column(columnDefinition = "interval")
    Duration thoiGianTiepXucDenKhoiPhat;

    @Column(columnDefinition = "interval")
    Duration thoiGianKeoDaiTb;

    @Column(columnDefinition = "interval")
    Duration dotGanNhatCach;

    String tienSuPhanVeGhiRo;

    String tienSuDiUngGhiRo;

    Boolean daDieuTriBenhDotNay;

    Integer tuoiLanDauBiBenh;

    Integer soLanMayDayCapTruoc;

    String benhPhu;
}
