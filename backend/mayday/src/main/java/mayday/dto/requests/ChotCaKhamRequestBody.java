package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChotCaKhamRequestBody(
        @JsonProperty("status")
        String trangThai,

        @JsonProperty("instructions")
        String danDo
) {
}
