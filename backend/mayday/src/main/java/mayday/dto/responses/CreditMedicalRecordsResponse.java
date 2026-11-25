package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import mayday.entities.phienkham.PhienKham;

public record CreditMedicalRecordsResponse(
        @JsonProperty("medical_record_id") Long maHoSo,
        @JsonProperty("status") String trangThai,
        @JsonProperty("patient_id") Long maBenhNhan,
        @JsonProperty("assigned_doctor_id") Long maBacSi
) {
    public CreditMedicalRecordsResponse(@Nonnull PhienKham phienKham) {
        this(phienKham.getHoSoBenhAn().getMaHoSo(),
             phienKham.getHoSoBenhAn().getTrangThai(),
             phienKham.getHoSoBenhAn().getMaBenhNhan(),
             phienKham.getMaBacSi());
    }
}