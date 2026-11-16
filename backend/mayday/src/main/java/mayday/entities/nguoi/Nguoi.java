package mayday.entities.nguoi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nguoi")
@Getter
@Setter
public class Nguoi {
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

    private OffsetDateTime thoiDiemTao;

    private OffsetDateTime thoiDiemCapNhat;

}
