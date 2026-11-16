package mayday.mappers;

import com.fasterxml.jackson.annotation.JsonProperty;
import mayday.entities.hosobenhan.BenhAnMayDayCap;
import mayday.entities.hosobenhan.BenhAnMayDayMan;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.phienkham.PhienKham;
import mayday.entities.phienkham.PhienKhamCap;
import mayday.entities.phienkham.PhienKhamMan;
import mayday.entities.qa.Answer;
import mayday.entities.qa.QuestionPrefix;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.lang.reflect.Field;
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

    public static <T> int updateFromAnswers(Class<T> type, T oldRecord, String prefix, List<Answer> answers) {
        HashMap<String, Object> map = new HashMap<>();
        answers.stream().filter(
                answer -> answer.getQuestionId().startsWith(prefix)
        ).forEach(
                answer -> map.put(
                        answer.getQuestionId().substring(prefix.length()),
                        answer.getAnswerValue()
                )
        );
        int updatedCount = 0;
        ObjectMapper objectMapper = new ObjectMapper();
        for (Field field : type.getDeclaredFields()) {
            var fieldJsonPropertyAnnotation = field.getAnnotation(JsonProperty.class);
            var fieldJsonProperty = fieldJsonPropertyAnnotation == null ? field.getName() : fieldJsonPropertyAnnotation.value();
            try {
                field.setAccessible(true);
                if (map.getOrDefault(fieldJsonProperty, null) != null) {
                    Object value = map.get(fieldJsonProperty);
                    field.set(oldRecord, objectMapper.convertValue(value, field.getType()));
                    updatedCount++;
                }
                field.setAccessible(false);
            } catch (IllegalAccessException | IllegalArgumentException e) {
                System.out.println("Invalid conversion " + e.getMessage());
            }
        }
        return updatedCount;
    }

    public static List<Answer> map(PhienKham session) {
        HoSoBenhAn medicalRecord = session.getHoSoBenhAn();
        var answers = new ArrayList<Answer>();
        answers.addAll(toListOfAnswers(medicalRecord.getGenericRecordInformation(), QuestionPrefix.HANH_CHINH.toString()));
        answers.add(new Answer(QuestionPrefix.DAU_HIEU_SINH_TON.toString(), session.getBangDauHieuSinhTon()));
        answers.add(new Answer(QuestionPrefix.HOAN_CANH_KHOI_PHAT.toString(), medicalRecord.getHoanCanhKhoiphatDiUng()));
        if (medicalRecord.getLoaiBenhAn().equals("CAP")) {
            BenhAnMayDayCap acuteRecord = medicalRecord.getBenhAnMayDayCap();
            PhienKhamCap acuteSession = session.getPhienKhamCap();
            answers.addAll(toListOfAnswers(acuteRecord.getAcuteRecordInformation(), QuestionPrefix.BENH_AN_CAP.toString()));
            answers.addAll(toListOfAnswers(acuteRecord.getInfectionStatus(), QuestionPrefix.NHIEM_TRUNG.toString()));
            answers.addAll(toListOfAnswers(acuteSession.getThongTinKhamCap(), QuestionPrefix.TRIEU_CHUNG_LAM_SANG.toString()));
            answers.add(new Answer(QuestionPrefix.TON_THUONG_CAP_TINH.toString(), acuteSession.getTonThuongCapTinh()));
        } else {
            BenhAnMayDayMan chronicRecord = medicalRecord.getBenhAnMayDayMan();
            PhienKhamMan chronicSession = session.getPhienKhamMan();
            answers.addAll(toListOfAnswers(chronicRecord.getChronicRecordInformation(), QuestionPrefix.BENH_AN_MAN.toString()));
            answers.addAll(toListOfAnswers(chronicRecord.getClinicHistoryDetail(), QuestionPrefix.BENH_SU.toString()));
            answers.addAll(toListOfAnswers(chronicRecord.getPersonalMedicalHistory(), QuestionPrefix.TIEN_SU_BAN_THAN.toString()));
            answers.addAll(toListOfAnswers(chronicSession.getThongTinKhamMan(), QuestionPrefix.BENH_AN_MAN.toString()));
            answers.add(new Answer(QuestionPrefix.TON_THUONG_SAN_PHU.toString(), chronicSession.getTonThuongSanPhu()));
        }
        return answers;
    }
}
