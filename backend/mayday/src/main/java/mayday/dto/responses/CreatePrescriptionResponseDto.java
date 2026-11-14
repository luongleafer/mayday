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
public class CreatePrescriptionResponseDto {

    private String code;
    private String message;
    private PrescriptionData data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PrescriptionData {
        @JsonProperty("prescription_id")
        private String prescriptionId;

        @JsonProperty("medical_record_id")
        private String medicalRecordId;

        @JsonProperty("created_by")
        private String createdBy;

        private String note;

        @JsonProperty("total_medicines")
        private String totalMedicines;

        private List<PrescriptionItemData> items;

        @JsonProperty("created_at")
        private String createdAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PrescriptionItemData {
        @JsonProperty("prescription_item_id")
        private String prescriptionItemId;

        @JsonProperty("medicine_name")
        private String medicineName;

        private String dosage;
        private String frequency;
        private String duration;
        private String instruction;
    }
}
