package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import mayday.entities.hosobenhan.HoSoBenhAn;

public record SetAcceptMedicalRecordsResponse(
        @JsonProperty("medical_record_id") Long maHoSo,
        @JsonProperty("status") String trangThai
) {
    public SetAcceptMedicalRecordsResponse(HoSoBenhAn hoSoBenhAn) {
        this(hoSoBenhAn.getMaHoSo(), hoSoBenhAn.getTrangThai());
    }
}
