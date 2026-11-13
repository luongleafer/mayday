package mayday.mappers;

import mayday.entities.hosobenhan.BenhAnMayDayCap;
import mayday.entities.hosobenhan.BenhAnMayDayMan;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.phienkham.PhienKham;
import mayday.entities.phienkham.PhienKhamCap;
import mayday.entities.phienkham.PhienKhamMan;
import mayday.entities.qa.Answer;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AnswerListMapper {
    public static <T> List<Answer> toListOfAnswers(T record, String prefix) {
        var answers = new ArrayList<Answer>();
        ObjectMapper objectMapper = new ObjectMapper();
        var values = objectMapper.convertValue(record, new TypeReference<HashMap<String, Object>>() {
        });
        if (values == null || values.isEmpty()) return answers;
        values.forEach((key, value) ->
                       {
                           if (value != null) answers.add(new Answer(prefix + key, value.toString()));
                       }
        );
        return answers;
    }

    public static List<Answer> map(PhienKham session) {
        HoSoBenhAn medicalRecord = session.getHoSoBenhAn();
        var answers = new ArrayList<Answer>();
        answers.addAll(toListOfAnswers(medicalRecord.getGenericRecordInformation(), "1.hanh-chinh_"));
        answers.add(new Answer("2.1.dau-hieu-sinh-ton", session.getBangDauHieuSinhTon()));
        answers.add(new Answer("3.1.hoan-canh-khoi-phat", medicalRecord.getHoanCanhKhoiphatDiUng()));
        if (medicalRecord.getLoaiBenhAn().equals("CAP")) {
            BenhAnMayDayCap acuteRecord = medicalRecord.getBenhAnMayDayCap();
            PhienKhamCap acuteSession = session.getPhienKhamCap();
            answers.addAll(toListOfAnswers(acuteRecord.getAcuteRecordInformation(), "2.0.benh-an-cap_"));
            answers.addAll(toListOfAnswers(acuteRecord.getInfectionStatus(), "3.2.nhiem-trung_"));
            answers.addAll(toListOfAnswers(acuteSession.getThongTinKhamCap(), "2.2.trieu-chung-lam-sang_"));
            answers.add(new Answer("2.2.1.ton-thuong-cap-tinh", acuteSession.getTonThuongCapTinh()));
        } else {
            BenhAnMayDayMan chronicRecord = medicalRecord.getBenhAnMayDayMan();
            PhienKhamMan chronicSession = session.getPhienKhamMan();
            answers.addAll(toListOfAnswers(chronicRecord.getChronicRecordInformation(), "2.0.benh-an-man_"));
            answers.addAll(toListOfAnswers(chronicRecord.getClinicHistoryDetail(), "3.1.benh-su_"));
            answers.addAll(toListOfAnswers(chronicRecord.getPersonalMedicalHistory(), "3.3.tien-su-ban-than_"));
            answers.addAll(toListOfAnswers(chronicSession.getThongTinKhamMan(), "2.0.benh-an-man_"));
            answers.add(new Answer("2.2.2.ton-thuong-san-phu", chronicSession.getTonThuongSanPhu()));
        }
        return answers;
    }
}
