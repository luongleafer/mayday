package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreditMedicalRecordsRequestBody(
        @JsonProperty("status")
        String trangThai,

        @JsonProperty("instructions")
        String danDo
) {
}
