package mayday.entities.phienkham;

import jakarta.persistence.Embeddable;

@Embeddable
public record ThongTinKhamCap(
        String trieuChungDa,
        String trieuChungHoHap,
        String soVoiThuongNgayHoHap,
        String trieuChungTuanHoan,
        String soVoiThuongNgayTuanHoan,
        String soVoiThuongNgayTieuHoa,
        Boolean coSanPhu,
        Boolean coAnhSanPhu,
        Integer soLanNon
) {
}
