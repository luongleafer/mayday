package mayday.medicalrecords;

public record MedicalRecord(
        Long medical_record_id,
        Long patient_id,
        String status
) {}
