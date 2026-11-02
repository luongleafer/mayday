package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record ThongTinHoanCanhKhoiPhat(
        String nghiNgo,
        BigDecimal thoiGian,
        Boolean tungBi,
        String loaiTienSu,
        String tienSuGhiRo
) {
}
