package mayday.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UAS7EligibilityResponseDto {

    @JsonProperty("code")
    private String code; // "1000" (Thành công theo slide)

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private EligibilityData data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EligibilityData {

        @JsonProperty("eligible")
        @NotNull
        private String eligible; // "true" hoặc "false"

        // ID của hồ sơ bệnh án đang active
        @JsonProperty("medical_record_id")
        @JsonInclude(JsonInclude.Include.NON_NULL) // Tự động ẩn field khi null (nếu eligible = "false")
        private String medicalRecordId; // Dùng String đúng theo spec Type: string của slide
    }
}