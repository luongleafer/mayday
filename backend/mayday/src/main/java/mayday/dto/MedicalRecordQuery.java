package mayday.dto;

import mayday.exceptions.MedicalRecordException;

import java.util.Set;

public record MedicalRecordQuery(String status, Long patientId, int page, int limit) {
    private static final Set<String> STATUSES = Set.of("draft", "pending", "in_progress", "completed");

    public MedicalRecordQuery {
        if (status != null && !STATUSES.contains(status)) {
            throw MedicalRecordException.invalid("status must be draft, pending, in_progress or completed");
        }
        if (patientId != null && patientId <= 0) {
            throw MedicalRecordException.invalid("patient_id must be positive");
        }
        if (page < 1 || limit < 1 || limit > 100) {
            throw MedicalRecordException.invalid("page must be >= 1 and limit must be between 1 and 100");
        }
    }

    public long offset() {
        return (long) (page - 1) * limit;
    }
}
