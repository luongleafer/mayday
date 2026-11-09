package mayday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MedicalRecord(
        @JsonProperty("medical_record_id") Long maHoSo,
        @JsonProperty("patient_id") Long maBenhNhan,
        @JsonProperty("status") String trangThai
) {
}
