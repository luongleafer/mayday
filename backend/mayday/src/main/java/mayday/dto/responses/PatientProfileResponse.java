package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import mayday.entities.nguoi.BenhNhan;
import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.TaiKhoan;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PatientProfileResponse {
    Long userId;
    String fullName;
    String phone;
    Long roleId;
    String gender;
    Integer yearOfBirth;
    String career;
    String ethnic;
    String hamletAddress;
    String communeAddress;
    String cityAddress;
    String workPlace;
    String healthInsuranceNumber;
    String healthInsuranceValidate;
    String nextOfKinName;
    String nextOfKinPhone;
    @Setter
    String firstVisit;

    public PatientProfileResponse(BenhNhan patient) {
        userId = patient.getMaBenhNhan();
        Nguoi person = patient.getNguoi();
        TaiKhoan account = person.getTaiKhoan();
        fullName = person.getHoTen();
        phone = person.getSoDienThoai();
        roleId = account.getVaiTro().getMaVaiTro();
        gender = person.getGioiTinh() == null ? null : person.getGioiTinh().toString();
        yearOfBirth = person.getNgaySinh() == null ? null : person.getNgaySinh().getYear();
        career = patient.getNgheNghiep();
        ethnic = patient.getDanToc();
        hamletAddress = person.getDiaChiSoNhaThonPho();
        communeAddress = person.getDiaChiXaPhuong();
        cityAddress = person.getDiaChiTinhTp();
        workPlace = patient.getNgheNghiep();
        healthInsuranceNumber = patient.getBhytSoThe();
        healthInsuranceValidate = patient.getBhytGiaTriDen() == null ? null : patient.getBhytGiaTriDen().toString();
        nextOfKinName = patient.getNguoiNhaHoTenDiaChi();
        nextOfKinPhone = patient.getNguoiNhaSoDienThoai();
    }
}
