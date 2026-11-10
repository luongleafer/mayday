package mayday.dto;

public record MedicalRecordSummary(
        Long medical_record_id,
        String status,
        String patient_name,
        java.time.OffsetDateTime created_at
) {}
