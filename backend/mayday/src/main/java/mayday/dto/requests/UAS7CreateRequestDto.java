package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// UAS7CreateRequestDto.java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UAS7CreateRequestDto {
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

    @JsonProperty("note")
    private String note;
}
