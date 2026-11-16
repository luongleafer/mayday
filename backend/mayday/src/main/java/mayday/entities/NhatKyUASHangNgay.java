package mayday.entities;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nhatkyuashangngay")
@Entity
@Builder
public class NhatKyUASHangNgay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_nhat_ky_uas")
    private Long maNhatKyUas;

    @Column(name = "ma_chu_ky")
    private Long maChuKy;

    @Column(name = "ma_ho_so_man")
    private Long maHoSoMan;

    @Column(name = "ma_benh_nhan", nullable = false)
    private Long maBenhNhan;

    @Column(name = "ngay_cham_diem", nullable = false)
    private LocalDate ngayChamDiem;

    @Column(name = "ngay_thu_may_trong_chu_ky")
    private Short ngayThuMayTrongChuKy;

    @Column(name = "diem_san_phu_hss", nullable = false)
    private Short diemSanPhuHss;

    @Column(name = "diem_ngua_iss", nullable = false)
    private Short diemNguaIss;

    @Column(name = "diem_uas_ngay", insertable = false, updatable = false)
    private Short diemUasNgay;

    @Column(name = "trang_thai_dung_thuoc")
    private String trangThaiDungThuoc;

    @Column(name = "trang_thai_ngay", nullable = false)
    private String trangThaiNgay;

    @Column(name = "thoi_diem_cap_nhat", insertable = false, updatable = false)
    private OffsetDateTime thoiDiemCapNhat;
}
