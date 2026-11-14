package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UAS7GetResponseDto {

    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private List<UAS7HistoryItemDto> data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UAS7HistoryItemDto {

        @JsonProperty("patient_id")
        private String patientId;

        @JsonProperty("medical_record_id")
        private String medicalRecordId;

        @JsonProperty("score_date")
        private String scoreDate;

        @JsonProperty("iss7")
        private String iss7;

        @JsonProperty("hss7")
        private String hss7;

        @JsonProperty("daily_score")
        private String dailyScore;

        @JsonProperty("note")
        private String note;
    }
}