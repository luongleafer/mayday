package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.qa.Answer;
import mayday.entities.qa.AnswerListConvertible;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class GenericRecordInformation implements AnswerListConvertible {
    OffsetDateTime thoiDiemKhamLanDau;

    LocalDate ngayBatDauDieuTriNgoaiTru;

    LocalDate ngayKetThucDieuTriNgoaiTru;

    String chanDoanTuyenTruoc;

    String chanDoanBanDauPhongKham;

    @Enumerated(EnumType.STRING)
    KetQuaDieuTriBaoHiemType ketQuaDieuTriBaoHiem;

    BigDecimal thoiGianKhoiPhatTuan;

    String tienSuGiaDinh;

    String tienSuGiaDinhGhiRo;

    Boolean daDieuTriTruocKham;

    @Override
    public void updateFromListOfAnswers(List<Answer> answers) {

    }
}
