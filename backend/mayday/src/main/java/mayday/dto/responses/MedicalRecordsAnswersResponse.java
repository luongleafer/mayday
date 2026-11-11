package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.hosobenhan.BenhAnType;
import mayday.entities.phienkham.*;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class ClinicSessionInformation {
    PhienKham session;
    AcuteSessionInformation accuteRecordInformation;
    ChronicSessionInformation chronicRecordInformation;
    List<VitalSignsInformation> dauHieuSinhTon;
    List<AcuteLesionsInformation> tonThuongCapTinh;
    List<WhealsInformation> tonThuongSanPhu;

    public ClinicSessionInformation(PhienKham session) {
        this.session = session;
        this.dauHieuSinhTon = session.getBangDauHieuSinhTon()
                .stream().map(DauHieuSinhTon::getDauHieuSinhTon).toList();
        if (session.getLoaiPhienKham() == BenhAnType.CAP) {
            PhienKhamCap phienKhamCap = session.getPhienKhamCap();
            accuteRecordInformation = phienKhamCap.getThongTinKhamCap();
            chronicRecordInformation = null;
            tonThuongCapTinh = phienKhamCap.getTonThuongCapTinh()
                    .stream().map(ChiTietTonThuongCapTinh::getThongTinTonThuongCapTinh).toList();

        } else {
            PhienKhamMan phienKhamMan = session.getPhienKhamMan();
            chronicRecordInformation = phienKhamMan.getThongTinKhamMan();
            accuteRecordInformation = null;
            tonThuongSanPhu = phienKhamMan.getTonThuongSanPhu()
                    .stream().map(ChiTietTonThuongSanPhu::getThongTinTonThuongSanPhu).toList();
        }

    }


    @JsonUnwrapped
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PhienKham getSession() {
        return session;
    }

    @JsonUnwrapped
    public AcuteSessionInformation getAccuteRecordInformation() {
        return accuteRecordInformation;
    }

    @JsonUnwrapped
    public ChronicSessionInformation getChronicRecordInformation() {
        return chronicRecordInformation;
    }

    public List<VitalSignsInformation> getDauHieuSinhTon() {
        return dauHieuSinhTon;
    }

    public List<AcuteLesionsInformation> getTonThuongCapTinh() {
        return tonThuongCapTinh;
    }

    public List<WhealsInformation> getTonThuongSanPhu() {
        return tonThuongSanPhu;
    }
}

public record MedicalRecordsAnswersResponse(
        @JsonUnwrapped HoSoBenhAn hoSoBenhAn,
        List<ClinicSessionInformation> lichSuPhienKham
) {
    public MedicalRecordsAnswersResponse(HoSoBenhAn medicalRecord) {
        this(medicalRecord, new ArrayList<>());
        medicalRecord.getDanhSachPhienKham().forEach(phienKham -> lichSuPhienKham.add(new ClinicSessionInformation(phienKham)));
    }
}
