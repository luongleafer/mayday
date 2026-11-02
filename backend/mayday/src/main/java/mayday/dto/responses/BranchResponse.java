package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/** Du lieu chi nhanh tra ve client; giu ten JSON is_active de tuong thich API hien tai. */
public record BranchResponse(Long id, String name, String address, String phone,
        @JsonProperty("is_active") Boolean active) {
}
