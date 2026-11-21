package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mayday.dto.requests.SignUpRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.CurrentTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nguoi")
@Getter
@Setter
@NoArgsConstructor
public class Nguoi {

    public Nguoi(String fullName, Gender gender, String phoneNumber) {
        hoTen = fullName;
        gioiTinh = gender;
        soDienThoai = phoneNumber;
    }

    public Nguoi(SignUpRequest request) {
        hoTen = request.getFullName();
        gioiTinh = request.getGender();
        soDienThoai = request.getPhone();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maNguoi;

    private String hoTen;

    private LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    private Gender gioiTinh;

    private String soDienThoai;

    private String diaChiTinhTp;

    private String diaChiXaPhuong;

    private String diaChiSoNhaThonPho;

    @CurrentTimestamp(source = SourceType.DB)
    private OffsetDateTime thoiDiemTao;

    @CurrentTimestamp(source = SourceType.DB)
    private OffsetDateTime thoiDiemCapNhat;

}
