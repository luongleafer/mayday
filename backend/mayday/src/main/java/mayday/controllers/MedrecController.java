package mayday.controllers;

import mayday.dtos.ApiResponse;
import mayday.services.HoSoBenhAnService;
import org.springframework.web.bind.annotation.*;

record ChotCaKhamRequestBody(
        String trangThai,
        String danDo
) {
}


@RestController
@RequestMapping("/api/v1/medical_records")
public class MedrecController {

    private HoSoBenhAnService hoSoBenhAnService;

    public MedrecController(HoSoBenhAnService service) {
        hoSoBenhAnService = service;
    }


    @PostMapping("/{id}/accept")
    ApiResponse<HoSoBenhAnService.HoSoBenhAnServiceResponse> bacSiNhanCaKham(@PathVariable Long id) {
        return hoSoBenhAnService.nhanPhienKham(id);
    }


    @PutMapping("/{id}")
    ApiResponse<HoSoBenhAnService.HoSoBenhAnServiceResponse> bacSiChotCaKham(@PathVariable Long id, @RequestBody ChotCaKhamRequestBody body) {
        return hoSoBenhAnService.chotPhienKham(id, body.trangThai(), body.danDo());

    }
}
