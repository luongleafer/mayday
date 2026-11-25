package mayday.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UAS7EligibilityData(
        @JsonProperty("eligible") String eligible,
        @JsonProperty("medical_record_id") @JsonInclude(JsonInclude.Include.NON_NULL) String medicalRecordId
) {}