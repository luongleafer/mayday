package mayday.entities.phienkham;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public record ThongTinKhamMan(
        Boolean hienTaiCoSanPhu,
        Boolean uuTheNgauNhien,
        String viTriSanPhu,
        String yeuToLamNangSaPhu,
        String hoanCanhXuatHienSanPhu,
        Boolean trongDotCoPhuMach,
        Boolean hienTaiCoPhuMach,
        Integer soLanPhuMachDotNay,
        String hoanCanhXuatHienPhuMach,
        Integer soLanPhuMachNangPhaiNhapVien,
        String thoiGianTonTaiPhuMachCoDungThuoc,
        String thoiGianTonTaiPhuMachKhongDungThuoc,
        Short diemAas7,
        String benPhuMach,
        BigDecimal thoiGianNgauNhienTuan,
        Boolean uuTheKichThich,
        BigDecimal thoiGianKichThichTuan,
        Boolean uuTheTuongDuong,
        Boolean uuTheKhongRo,
        String thuTuXuatHienSoVoiSanPhu,
        @Column(name = "phu_mach_1_tuan_gan_day") Boolean phuMach1TuanGanDay,
        String chanDoanTaiKham,
        String khoangCachXuatHien,
        Boolean phuMiMat,
        Boolean phuLuoi,
        Boolean phuMoi,
        Boolean phuVungMatKhac,
        Boolean phuThanhQuan,
        Boolean phuBanChan,
        Boolean phuSinhDuc,
        Boolean phuViTriKhac,
        String phuViTriKhacGhiRo,
        Boolean phuBanTay
) {

}
