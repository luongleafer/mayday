package mayday.entities.hosobenhan;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record ClinicHistoryDetail(
        Boolean tungCoDotTuongTu,

        Short tuoiKhoiPhat,

        Integer soDotTruoc,

        String kieuDienBien,

        String bieuHienDotTruoc,

        Boolean daTungNhapVien,

        Integer soLanNhapVien,

        BigDecimal thoiGianTuDotGanNhat,

        String dienBienCacDotTruoc
) {
}
