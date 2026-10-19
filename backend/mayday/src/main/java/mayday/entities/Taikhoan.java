package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "taikhoan", schema = "public")
public class Taikhoan {

    @Id
    @Column(name = "ma_tai_khoan", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maTaiKhoan;

    @Column(name = "ma_nguoi", nullable = false)
    private Long maNguoi;

    @Column(name = "ma_vai_tro", nullable = false)
    private Long maVaiTro;

    @Column(name = "ten_dang_nhap", nullable = false)
    private String tenDangNhap;

    @Column(name = "mat_khau_ma_hoa", nullable = false)
    private String matKhauMaHoa;

    @Column(name = "trang_thai_hoat_dong", nullable = false)
    private Boolean trangThaiHoatDong;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Taikhoan() {
    }

    public Long getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(Long maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public Long getMaNguoi() {
        return maNguoi;
    }

    public void setMaNguoi(Long maNguoi) {
        this.maNguoi = maNguoi;
    }

    public Long getMaVaiTro() {
        return maVaiTro;
    }

    public void setMaVaiTro(Long maVaiTro) {
        this.maVaiTro = maVaiTro;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhauMaHoa() {
        return matKhauMaHoa;
    }

    public void setMatKhauMaHoa(String matKhauMaHoa) {
        this.matKhauMaHoa = matKhauMaHoa;
    }

    public Boolean getTrangThaiHoatDong() {
        return trangThaiHoatDong;
    }

    public void setTrangThaiHoatDong(Boolean trangThaiHoatDong) {
        this.trangThaiHoatDong = trangThaiHoatDong;
    }

    public OffsetDateTime getThoiDiemTao() {
        return thoiDiemTao;
    }

    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) {
        this.thoiDiemTao = thoiDiemTao;
    }

    public OffsetDateTime getThoiDiemCapNhat() {
        return thoiDiemCapNhat;
    }

    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) {
        this.thoiDiemCapNhat = thoiDiemCapNhat;
    }

}
