package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.hosobenhan.BenhAnType;
import mayday.entities.phienkham.*;
import mayday.entities.qa.Answer;
import mayday.mappers.AnswerListMapper;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class ClinicSessionInformation {
    PhienKham session;
    AcuteSessionInformation accuteRecordInformation;
    ChronicSessionInformation chronicRecordInformation;
    @Getter
    List<VitalSignsInformation> vitalSigns;
    @Getter
    List<AcuteLesionsInformation> acuteLesions;
    @Getter
    List<WhealsInformation> wheals;

    public ClinicSessionInformation(PhienKham session) {
        this.session = session;
        this.vitalSigns = session.getBangDauHieuSinhTon()
                .stream().map(DauHieuSinhTon::getDauHieuSinhTon).toList();
        if (session.getLoaiPhienKham() == BenhAnType.CAP) {
            PhienKhamCap phienKhamCap = session.getPhienKhamCap();
            accuteRecordInformation = phienKhamCap.getThongTinKhamCap();
            chronicRecordInformation = null;
            acuteLesions = phienKhamCap.getTonThuongCapTinh()
                    .stream().map(ChiTietTonThuongCapTinh::getThongTinTonThuongCapTinh).toList();

        } else {
            PhienKhamMan phienKhamMan = session.getPhienKhamMan();
            chronicRecordInformation = phienKhamMan.getThongTinKhamMan();
            accuteRecordInformation = null;
            wheals = phienKhamMan.getTonThuongSanPhu()
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

}

public record MedicalRecordsAnswersResponse(
        @JsonIgnore HoSoBenhAn hoSoBenhAn,
        List<Answer> answers
) {
    public MedicalRecordsAnswersResponse(PhienKham session) {
        AnswerListMapper answerListMapper = new AnswerListMapper();
        this(session.getHoSoBenhAn(), answerListMapper.map(session));
//        medicalRecord.getDanhSachPhienKham().forEach(phienKham -> lichSuPhienKham.add(new ClinicSessionInformation(phienKham)));
    }
}
