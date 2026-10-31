package mayday.dtos.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChotCaKhamRequestBody(
        @JsonProperty("status")
        String trangThai,

        @JsonProperty("instructions")
        String danDo
) {
}
