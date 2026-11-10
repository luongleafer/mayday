package mayday.dto;

public record MedicalRecord(
        Long medical_record_id,
        Long patient_id,
        String status,
        Long assigned_doctor_id,
        Long created_by
) {}
