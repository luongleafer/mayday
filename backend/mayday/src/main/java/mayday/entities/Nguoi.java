package mayday.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "nguoi")
public class Nguoi {
    @Id
    @Column(name = "ma_nguoi")
    private Long maNguoi;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "dia_chi_tinh_tp")
    private String diaChiTinhTp;

    @Column(name = "dia_chi_xa_phuong")
    private String diaChiXaPhuong;

    @Column(name = "dia_chi_so_nha_thon_pho")
    private String diaChiSoNhaThonPho;

    @Column(name = "thoi_diem_tao")
    private Timestamp thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat")
    private Timestamp thoiDiemCapNhat;

}
