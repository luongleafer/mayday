package mayday.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import mayday.entities.HoSoBenhAn;

public record NhanPhienKhamResponse(
        @JsonProperty("medical_record_id") Long maHoSo,
        @JsonProperty("status") String trangThai
) {
    public NhanPhienKhamResponse(HoSoBenhAn hoSoBenhAn) {
        this(hoSoBenhAn.getMaHoSo(), hoSoBenhAn.getTrangThai());
    }
}
