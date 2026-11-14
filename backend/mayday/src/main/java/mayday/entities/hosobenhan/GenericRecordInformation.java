package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.qa.Answer;
import mayday.entities.qa.AnswerListConvertible;
import mayday.entities.qa.QuestionPrefix;
import mayday.exceptions.InvalidAnswerException;
import tools.jackson.core.io.BigDecimalParser;

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
    public void updateFromListOfAnswers(List<Answer> answers) throws InvalidAnswerException {
        GenericRecordInformation updateRecord = new GenericRecordInformation();
        var matchedAnswers = answers.stream().filter(answer -> answer.getQuestionId().startsWith(QuestionPrefix.HANH_CHINH.toString())).toList();
        for (var answer : matchedAnswers) {
            try {
                var value = answer.getAnswerValue();
                switch (answer.getQuestionId().substring(QuestionPrefix.HANH_CHINH.toString().length())) {
                    case "thoiDiemKhamLanDau":
                        updateRecord.thoiDiemKhamLanDau = OffsetDateTime.parse(value.toString());
                        break;
                    case "ngayBatDauDieuTriNgoaiTru":
                        updateRecord.ngayBatDauDieuTriNgoaiTru = LocalDate.parse(value.toString());
                        break;
                    case "ngayKetThucDieuTriNgoaiTru":
                        updateRecord.ngayKetThucDieuTriNgoaiTru = LocalDate.parse(value.toString());
                        break;
                    case "chanDoanTuyenTruoc":
                        updateRecord.chanDoanTuyenTruoc = value.toString();
                        break;
                    case "chanDoanBanDauPhongKham":
                        updateRecord.chanDoanBanDauPhongKham = value.toString();
                        break;
                    case "ketQuaDieuTriBaoHiem":
                        updateRecord.ketQuaDieuTriBaoHiem = KetQuaDieuTriBaoHiemType.valueOf(value.toString());
                        break;
                    case "thoiGianKhoiPhatTuan":
                        updateRecord.thoiGianKhoiPhatTuan = BigDecimalParser.parse(value.toString());
                        break;
                    case "tienSuGiaDinh":
                        updateRecord.tienSuGiaDinh = value.toString();
                        break;
                    case "tienSuGiaDinhGhiRo":
                        updateRecord.tienSuGiaDinhGhiRo = value.toString();
                        break;
                    case "daDieuTriTruocKham":
                        if (value.toString().equals("true")) {
                            daDieuTriTruocKham = true;
                        } else if (value.toString().equals("false")) {
                            daDieuTriTruocKham = false;
                        } else throw new Exception("Invalid value");
                        break;
                }
            } catch (Exception e) {
                throw new InvalidAnswerException(answer);
            }
        }
        thoiDiemKhamLanDau = updateRecord.thoiDiemKhamLanDau == null ? thoiDiemKhamLanDau : updateRecord.thoiDiemKhamLanDau;
        ngayKetThucDieuTriNgoaiTru = updateRecord.ngayKetThucDieuTriNgoaiTru == null ? ngayKetThucDieuTriNgoaiTru : updateRecord.ngayKetThucDieuTriNgoaiTru;
        ngayBatDauDieuTriNgoaiTru = updateRecord.ngayBatDauDieuTriNgoaiTru == null ? ngayBatDauDieuTriNgoaiTru : updateRecord.ngayBatDauDieuTriNgoaiTru;
        chanDoanTuyenTruoc = updateRecord.chanDoanTuyenTruoc == null ? chanDoanTuyenTruoc : updateRecord.chanDoanTuyenTruoc;
        chanDoanBanDauPhongKham = updateRecord.chanDoanBanDauPhongKham == null ? chanDoanBanDauPhongKham : updateRecord.chanDoanBanDauPhongKham;
        ketQuaDieuTriBaoHiem = updateRecord.ketQuaDieuTriBaoHiem == null ? ketQuaDieuTriBaoHiem : updateRecord.ketQuaDieuTriBaoHiem;
        thoiGianKhoiPhatTuan = updateRecord.thoiGianKhoiPhatTuan == null ? thoiGianKhoiPhatTuan : updateRecord.thoiGianKhoiPhatTuan;
        tienSuGiaDinh = updateRecord.tienSuGiaDinh == null ? tienSuGiaDinh : updateRecord.tienSuGiaDinh;
        tienSuGiaDinhGhiRo = updateRecord.tienSuGiaDinhGhiRo == null ? tienSuGiaDinhGhiRo : updateRecord.tienSuGiaDinhGhiRo;
        daDieuTriTruocKham = updateRecord.daDieuTriTruocKham == null ? daDieuTriTruocKham : updateRecord.daDieuTriTruocKham;
    }
}
