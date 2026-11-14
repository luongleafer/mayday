package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

// UAS7CreateResponseDto.java
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UAS7CreateResponseDto {
    @NotNull
    private String code;
    @NotNull
    private String message;
    @NotNull
    private List<UAS7ScoreItemDto> data;

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UAS7ScoreItemDto {
        @JsonProperty("patient_id")
        @NotNull
        private String patientId;

        @JsonProperty("medical_record_id")
        @NotNull
        private String medicalRecordId;

        @JsonProperty("score_date")
        @NotNull
        private String scoreDate;

        @JsonProperty("iss7")
        @NotNull
        private String iss7;

        @JsonProperty("hss7")
        @NotNull
        private String hss7;

        @JsonProperty("daily_score")
        @NotNull
        private String dailyScore;

        @JsonProperty("note")
        private String note;

        @JsonProperty("created_at")
        private String createdAt;

        @JsonProperty("updated_at")
        private String updatedAt;
    }
}