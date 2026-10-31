package mayday.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "nguoi")
public class Nguoi {
    @Id
    private Long maNguoi;

    private String hoTen;

    private LocalDate ngaySinh;

    private String gioiTinh;

    private String soDienThoai;

    private String diaChiTinhTp;

    private String diaChiXaPhuong;

    private String diaChiSoNhaThonPho;

    private OffsetDateTime thoiDiemTao;

    private OffsetDateTime thoiDiemCapNhat;

}
