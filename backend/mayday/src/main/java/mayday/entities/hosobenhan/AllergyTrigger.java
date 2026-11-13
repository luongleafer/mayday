package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record AllergyTrigger(
        String nghiNgo,
        BigDecimal thoiGian,
        Boolean tungBi,
        String loaiTienSu,
        String tienSuGhiRo
) {
}
