package mayday.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedicalRecordResponseDto {

    @JsonProperty("code")
    private String code; // 1001

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private MedicalRecordData data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicalRecordData {
        @JsonProperty("medical_record_id")
        private Long medicalRecordId;

        @JsonProperty("patient_id")
        private Long patientId;

        @JsonProperty("assigned_doctor_id")
        private Long assignedDoctorId;

        @JsonProperty("created_by")
        private Long createdBy;

        @JsonProperty("template_id")
        private String templateId;

        @JsonProperty("status")
        private String status;

        @JsonProperty("instructions")
        private String instructions;

        @JsonProperty("created_at")
        private String createdAt;
    }
}