package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemRequestDto {

    @JsonProperty("medicine_name")
    private String medicineName;

    private String dosage;

    private String frequency;

    private String duration;

    private String instruction;
}
