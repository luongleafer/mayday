package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabResultsResponseDto {

    private String code;
    private String message;
    private List<LabRequestDetailData> data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LabRequestDetailData {
        private String id;

        @JsonProperty("requested_by")
        private String requestedBy;

        private String status;
        private String note;
        private List<LabResultItemDetailData> items;

        @JsonProperty("created_at")
        private String createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LabResultItemDetailData {
        private String id;

        @JsonProperty("test_name")
        private String testName;

        @JsonProperty("result_id")
        private String resultId;

        @JsonProperty("result_value")
        private String resultValue;

        @JsonProperty("concluded_by")
        private String concludedBy;

        @JsonProperty("result_note")
        private String resultNote;
    }
}
