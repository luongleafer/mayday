package mayday.entities.hosobenhan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class AllergyTrigger {
    @JsonProperty("NghiNgo")
    String nghiNgo;
    @JsonProperty("ThoiGian")
    BigDecimal thoiGian;
    @JsonProperty("TungBi")
    Boolean tungBi;
    @JsonProperty("LoaiTienSu")
    String loaiTienSu;
    @JsonProperty("TienSuGhiRo")
    String tienSuGhiRo;
}
