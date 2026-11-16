package mayday.services.hosobenhan;

import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.MedicalRecordsAnswersResponse;
import mayday.dto.responses.CreditMedicalRecordsResponse;
import mayday.dto.responses.SetAcceptMedicalRecordsResponse;
import mayday.entities.hosobenhan.*;
import mayday.entities.phienkham.*;
import mayday.entities.qa.Answer;
import mayday.entities.qa.QuestionPrefix;
import mayday.exceptions.InvalidAnswerException;
import mayday.mappers.AnswerListMapper;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.PhienKhamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class MedicalRecordManagementService {

    private final HoSoBenhAnRepository medicalRecordRepository;
    private final PhienKhamRepository clinicSessionRepository;

    public MedicalRecordManagementService(HoSoBenhAnRepository medicalRecordRepository, PhienKhamRepository clinicSessionRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
        this.clinicSessionRepository = clinicSessionRepository;
    }

    private void updateMedicalRecordStatus(Long recordId, String newStatus) {
        HoSoBenhAn record = medicalRecordRepository.findById(recordId).orElse(null);
        if (record == null) return;
        record.setTrangThai(newStatus);
        medicalRecordRepository.save(record);
    }


    @Transactional
    public ApiResponse<SetAcceptMedicalRecordsResponse> setAcceptMedicalRecords(Long sessionId) {
        PhienKham session = clinicSessionRepository.findById(sessionId).orElse(null);
        if (session == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn medicalRecord = session.getHoSoBenhAn();
        if (medicalRecord.getTrangThai().equals("in_progress"))
            return new ApiResponse<>("4005", "Already accepted by another doctor", null);
        if (!medicalRecord.getTrangThai().equals("pending"))
            return new ApiResponse<>("4004", "Medical record not in pending status", null);
        medicalRecord.setTrangThai("in_progress");
        medicalRecordRepository.save(medicalRecord);
        // TODO: cập nhật mã bác sĩ trong phiên khám tùy theo người dùng gọi API
        return ApiResponse.ok(new SetAcceptMedicalRecordsResponse(medicalRecord));
    }

    @Transactional
    public ApiResponse<CreditMedicalRecordsResponse> creditMedicalRecords(Long sessionId, String status, String instruction) {
        PhienKham session = clinicSessionRepository.findById(sessionId).orElse(null);
        if (session == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn medicalRecord = session.getHoSoBenhAn();
        medicalRecord.setTrangThai(status);
        medicalRecordRepository.save(medicalRecord);
        session.setDanDo(instruction);
        clinicSessionRepository.save(session);
        return ApiResponse.ok(new CreditMedicalRecordsResponse(session));
    }

    public ApiResponse<Void> deleteMedicalRecord(Long recordId) {
        medicalRecordRepository.deleteById(recordId);
        return ApiResponse.ok(null);
    }

    @Transactional
    public ApiResponse<MedicalRecordsAnswersResponse> getMedicalRecordsAnswers(Long recordId) {
//        HoSoBenhAn medicalRecord = medicalRecordRepository.findById(recordId).orElse(null);
        PhienKham session = clinicSessionRepository.findById(recordId).orElse(null);
        if (session == null) return new ApiResponse<>("4001", "Medical record not found", null);
        return ApiResponse.ok(new MedicalRecordsAnswersResponse(session));
    }

    @Transactional
    public ApiResponse<Integer> updateMedicalRecordsAnswers(Long recordId, List<Answer> answers) {
        PhienKham session = clinicSessionRepository.findById(recordId).orElse(null);
        if (session == null || session.getHoSoBenhAn() == null)
            return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn medicalRecord = session.getHoSoBenhAn();
//        medicalRecord.getGenericRecordInformation().updateFromListOfAnswers(answers);
        int updated = 0;
        updated += AnswerListMapper.updateFromAnswers(GenericRecordInformation.class, medicalRecord.getGenericRecordInformation(), QuestionPrefix.HANH_CHINH.toString(), answers);
        if (medicalRecord.getLoaiBenhAn().equals("CAP")) {
            BenhAnMayDayCap acuteRecord = medicalRecord.getBenhAnMayDayCap();
            PhienKhamCap acuteSession = session.getPhienKhamCap();
            updated += AnswerListMapper.updateFromAnswers(AcuteRecordInformation.class, acuteRecord.getAcuteRecordInformation(), QuestionPrefix.BENH_AN_CAP.toString(), answers);
            updated += AnswerListMapper.updateFromAnswers(InfectionStatus.class, acuteRecord.getInfectionStatus(), QuestionPrefix.NHIEM_TRUNG.toString(), answers);
            updated += AnswerListMapper.updateFromAnswers(AcuteSessionInformation.class, acuteSession.getThongTinKhamCap(), QuestionPrefix.TRIEU_CHUNG_LAM_SANG.toString(), answers);
        } else {
            BenhAnMayDayMan chronicRecord = medicalRecord.getBenhAnMayDayMan();
            PhienKhamMan chronicSession = session.getPhienKhamMan();
            updated += AnswerListMapper.updateFromAnswers(ChronicRecordInformation.class, chronicRecord.getChronicRecordInformation(), QuestionPrefix.BENH_AN_MAN.toString(), answers);
            updated += AnswerListMapper.updateFromAnswers(ClinicHistoryDetail.class, chronicRecord.getClinicHistoryDetail(), QuestionPrefix.BENH_SU.toString(), answers);
            updated += AnswerListMapper.updateFromAnswers(PersonalMedicalHistory.class, chronicRecord.getPersonalMedicalHistory(), QuestionPrefix.TIEN_SU_BAN_THAN.toString(), answers);
            updated += AnswerListMapper.updateFromAnswers(ChronicSessionInformation.class, chronicSession.getThongTinKhamMan(), QuestionPrefix.BENH_AN_MAN.toString(), answers);
        }
        return ApiResponse.ok(updated);
    }
}
