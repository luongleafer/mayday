package mayday.dto.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.entities.nguoi.Gender;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientResponseDto {

    @JsonProperty("code")
    private String code; // 1001

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private PatientData data;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PatientData {

        // Khóa chính
        @JsonProperty("user_id")
        private Long userId;

        @JsonProperty("role_id")
        private Long roleId;

        // Ánh xạ bảng 'nguoi' (đồng bộ với CreatePatientRequestDto)
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

        // Ánh xạ bảng 'benhnhan' (đồng bộ với CreatePatientRequestDto)
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

        // Thời gian tạo tài khoản
        @JsonProperty("created_at")
        private OffsetDateTime createdAt;
    }
}
