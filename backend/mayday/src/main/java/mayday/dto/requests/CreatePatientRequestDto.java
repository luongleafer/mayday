package mayday.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.nguoi.Gender;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientRequestDto {

    // Ánh xạ bảng 'nguoi'
    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("gender")
    private Gender gender;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("province")
    private String province;

    @JsonProperty("ward")
    private String ward;

    @JsonProperty("address_detail")
    private String addressDetail;

    // Ánh xạ bảng 'benhnhan'
    @JsonProperty("his_code")
    private String hisCode;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("ethnic")
    private String ethnic;

    @JsonProperty("insurance_card_number")
    private String insuranceCardNumber;

    @JsonProperty("insurance_expire_date")
    private LocalDate insuranceExpireDate;

    @JsonProperty("guardian_info")
    private String guardianInfo;

    @JsonProperty("guardian_phone")
    private String guardianPhone;

    // Ánh xạ bảng 'taikhoan'
    @JsonProperty("password")
    private String password;
}