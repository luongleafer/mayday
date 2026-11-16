package mayday.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UAS7ScoreItemDto(
        @JsonProperty("patient_id") @NotNull String patientId,
        @JsonProperty("medical_record_id") @NotNull String medicalRecordId,
        @JsonProperty("score_date") @NotNull String scoreDate,
        @JsonProperty("iss7") @NotNull String iss7,
        @JsonProperty("hss7") @NotNull String hss7,
        @JsonProperty("daily_score") @NotNull String dailyScore,
        @JsonProperty("note") String note,
        @JsonProperty("created_at") String createdAt,
        @JsonProperty("updated_at") String updatedAt
) {}