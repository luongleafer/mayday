package mayday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MedicalRecordSummary(
        @JsonProperty("medical_record_id") Long maHoSo,
        @JsonProperty("status") String trangThai,
        @JsonProperty("patient_name") String tenBenhNhan
) {
}
