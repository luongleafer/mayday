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
public class CreateLabResponseDto {

    private String code;
    private String message;
    private LabRequestData data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LabRequestData {
        private String id;

        @JsonProperty("lab_request_id")
        private String labRequestId;

        @JsonProperty("medical_record_id")
        private String medicalRecordId;

        @JsonProperty("requested_by")
        private String requestedBy;

        private String status;
        private String note;
        private List<LabRequestItemData> items;

        @JsonProperty("created_at")
        private String createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class LabRequestItemData {
        private String id;

        @JsonProperty("test_name")
        private String testName;

        private String description;
    }
}
