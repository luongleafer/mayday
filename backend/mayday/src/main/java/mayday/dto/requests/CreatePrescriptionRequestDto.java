package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePrescriptionRequestDto {

    @JsonProperty("medical_record_id")
    private String medicalRecordId;

    private String note;

    private List<PrescriptionItemRequestDto> items;
}
