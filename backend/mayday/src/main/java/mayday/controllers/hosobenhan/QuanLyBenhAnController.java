package mayday.controllers.hosobenhan;

import mayday.dto.responses.ApiResponse;
import mayday.dto.requests.ChotCaKhamRequestBody;
import mayday.dto.responses.BenhAnResponse;
import mayday.dto.responses.ChotPhienKhamResponse;
import mayday.dto.responses.NhanPhienKhamResponse;
import mayday.services.hosobenhan.QuanLyBenhAnService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/medical_records")
public class QuanLyBenhAnController {

    private QuanLyBenhAnService quanLyBenhAnService;

    public QuanLyBenhAnController(QuanLyBenhAnService service) {
        quanLyBenhAnService = service;
    }

    // medrec07
    @PostMapping("/{id}/accept")
    ApiResponse<NhanPhienKhamResponse> bacSiNhanCaKham(@PathVariable Long id) {
        return quanLyBenhAnService.nhanPhienKham(id);
    }

    //medrec11
    @PutMapping("/{id}")
    ApiResponse<ChotPhienKhamResponse> bacSiChotCaKham(@PathVariable Long id, @RequestBody ChotCaKhamRequestBody body) {
        return quanLyBenhAnService.chotPhienKham(id, body.trangThai(), body.danDo());
    }

    //medrec14
    @DeleteMapping("/{id}")
    ApiResponse<Void> xoaBenhAn(@PathVariable Long id) {
        return quanLyBenhAnService.xoaBenhAn(id);
    }

    //medrec08
    @GetMapping("/{id}/answers")
    ApiResponse<BenhAnResponse> layBenhAn(@PathVariable Long id) {
        return quanLyBenhAnService.xemHoSo(id);
    }
}
