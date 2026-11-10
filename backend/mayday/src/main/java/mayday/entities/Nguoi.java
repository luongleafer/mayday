package mayday.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "nguoi")
public class Nguoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nguoi", nullable = false)
    private Long maNguoi;

    @Column(nullable = false, length = 150)
    private String hoTen;

    private LocalDate ngaySinh;

    @Enumerated(EnumType.STRING)
    private Gender gioiTinh;

    @Column(nullable = false, length = 10)
    private String soDienThoai;

    private String diaChiTinhTp;

    private String diaChiXaPhuong;

    private String diaChiSoNhaThonPho;

    @Column(insertable = false, updatable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(insertable = false, updatable = false)
    private OffsetDateTime thoiDiemCapNhat;
}
