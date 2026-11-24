package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLabRequestDto {

    @JsonProperty("medical_record_id")
    private String medicalRecordId;

    private String note;

    private List<LabRequestItemDto> items;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class LabRequestItemDto {
        @JsonProperty("test_name")
        private String testName;

        private String description;
    }
}
