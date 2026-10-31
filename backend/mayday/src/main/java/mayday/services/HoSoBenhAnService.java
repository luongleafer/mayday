package mayday.services;

import mayday.dtos.ApiResponse;
import mayday.dtos.response.ChotPhienKhamResponse;
import mayday.dtos.response.NhanPhienKhamResponse;
import mayday.entities.hosobenhan.HoSoBenhAn;
import mayday.entities.phienkham.PhienKham;
import mayday.repositories.HoSoBenhAnRepository;
import mayday.repositories.PhienKhamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class HoSoBenhAnService {

    private HoSoBenhAnRepository khoBenhAn;
    private PhienKhamRepository khoPhienKham;

    public HoSoBenhAnService(HoSoBenhAnRepository khoBenhAn, PhienKhamRepository khoPhienKham) {
        this.khoBenhAn = khoBenhAn;
        this.khoPhienKham = khoPhienKham;
    }

    private void capNhatTrangThaiHoSoBenhAn(Long maHoSo, String trangThaiMoi) {
        HoSoBenhAn benhAn = khoBenhAn.findById(maHoSo).orElse(null);
        if (benhAn == null) return;
        benhAn.setTrangThai(trangThaiMoi);
        khoBenhAn.save(benhAn);
    }


    @Transactional
    public ApiResponse<NhanPhienKhamResponse> nhanPhienKham(Long maPhienKham) {
        PhienKham phienKham = khoPhienKham.findById(maPhienKham).orElse(null);
        if (phienKham == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn benhAn = phienKham.getHoSoBenhAn();
        if (benhAn.getTrangThai().equals("in_progress"))
            return new ApiResponse<>("4005", "Already accepted by another doctor", null);
        if (!benhAn.getTrangThai().equals("pending"))
            return new ApiResponse<>("4004", "Medical record not in pending status", null);
        benhAn.setTrangThai("in_progress");
        khoBenhAn.save(benhAn);
        // TODO: cập nhật mã bác sĩ trong phiên khám tùy theo người dùng gọi API
        return ApiResponse.ok(new NhanPhienKhamResponse(benhAn));
    }

    @Transactional
    public ApiResponse<ChotPhienKhamResponse> chotPhienKham(Long maPhienKham, String trangThai, String chiDan) {
        PhienKham phienKham = khoPhienKham.findById(maPhienKham).orElse(null);
        if (phienKham == null) return new ApiResponse<>("4001", "Medical record not found", null);
        HoSoBenhAn benhAn = phienKham.getHoSoBenhAn();
        benhAn.setTrangThai(trangThai);
        khoBenhAn.save(benhAn);
        phienKham.setDanDo(chiDan);
        khoPhienKham.save(phienKham);
        return ApiResponse.ok(new ChotPhienKhamResponse(phienKham));
    }

    public ApiResponse<Void> xoaBenhAn(Long maBenhAn) {
        khoBenhAn.deleteById(maBenhAn);
        return ApiResponse.ok(null);
    }
}
