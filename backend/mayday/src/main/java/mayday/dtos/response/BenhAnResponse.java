package mayday.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.hosobenhan.LoaiBenhAn;
import mayday.entities.phienkham.*;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
class ThongTinPhienKham {
    PhienKham phienKham;
    ThongTinKhamCap thongTinKhamCap;
    ThongTinKhamMan thongTinKhamMan;
    List<ThongTinDauHieuSinhTon> dauHieuSinhTon;
    List<ThongTinTonThuongCapTinh> tonThuongCapTinh;
    List<ThongTinTonThuongSanPhu> tonThuongSanPhu;

    public ThongTinPhienKham(PhienKham phienKham) {
        this.phienKham = phienKham;
        this.dauHieuSinhTon = phienKham.getBangDauHieuSinhTon()
                .stream().map(DauHieuSinhTon::getDauHieuSinhTon).toList();
        if (phienKham.getLoaiPhienKham() == LoaiBenhAn.CAP) {
            PhienKhamCap phienKhamCap = phienKham.getPhienKhamCap();
            thongTinKhamCap = phienKhamCap.getThongTinKhamCap();
            thongTinKhamMan = null;
            tonThuongCapTinh = phienKhamCap.getTonThuongCapTinh()
                    .stream().map(ChiTietTonThuongCapTinh::getThongTinTonThuongCapTinh).toList();

        } else {
            PhienKhamMan phienKhamMan = phienKham.getPhienKhamMan();
            thongTinKhamMan = phienKhamMan.getThongTinKhamMan();
            thongTinKhamCap = null;
            tonThuongSanPhu = phienKhamMan.getTonThuongSanPhu()
                    .stream().map(ChiTietTonThuongSanPhu::getThongTinTonThuongSanPhu).toList();
        }

    }


    @JsonUnwrapped
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public PhienKham getPhienKham() {
        return phienKham;
    }

    @JsonUnwrapped
    public ThongTinKhamCap getThongTinKhamCap() {
        return thongTinKhamCap;
    }

    @JsonUnwrapped
    public ThongTinKhamMan getThongTinKhamMan() {
        return thongTinKhamMan;
    }

    public List<ThongTinDauHieuSinhTon> getDauHieuSinhTon() {
        return dauHieuSinhTon;
    }

    public List<ThongTinTonThuongCapTinh> getTonThuongCapTinh() {
        return tonThuongCapTinh;
    }

    public List<ThongTinTonThuongSanPhu> getTonThuongSanPhu() {
        return tonThuongSanPhu;
    }
}

public record BenhAnResponse(
        @JsonUnwrapped HoSoBenhAn hoSoBenhAn,
        List<ThongTinPhienKham> lichSuPhienKham
) {
    public BenhAnResponse(HoSoBenhAn benhAn) {
        this(benhAn, new ArrayList<>());
        benhAn.getDanhSachPhienKham().forEach(phienKham -> lichSuPhienKham.add(new ThongTinPhienKham(phienKham)));
    }
}
