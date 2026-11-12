package mayday.services.hosobenhan;

import mayday.dto.responses.ApiResponse;
import mayday.dto.responses.MedicalRecordsAnswersResponse;
import mayday.dto.responses.CreditMedicalRecordsResponse;
import mayday.dto.responses.SetAcceptMedicalRecordsResponse;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.phienkham.PhienKham;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.PhienKhamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
        HoSoBenhAn medicalRecord = medicalRecordRepository.findById(recordId).orElse(null);
        if (medicalRecord == null) return new ApiResponse<>("4001", "Medical record not found", null);
        return ApiResponse.ok(new MedicalRecordsAnswersResponse(medicalRecord));
    }
}
