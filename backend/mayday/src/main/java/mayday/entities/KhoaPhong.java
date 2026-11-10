package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.OffsetDateTime;

/** Anh xa bang khoaphong theo NAMING_CONVENTION.md; moi khoa bat buoc thuoc mot chi nhanh. */
@Entity
@Table(name = "khoaphong", uniqueConstraints =
        @UniqueConstraint(name = "uq_khoa_phong_chi_nhanh_ten", columnNames = {"ma_chi_nhanh", "ten_khoa_phong"}))
public class KhoaPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maKhoaPhong;

    @Column(nullable = false, length = 150)
    private String tenKhoaPhong;

    @Column(nullable = false, length = 50)
    private String loaiKhoa = "CHUYEN_KHOA";

    @Column(columnDefinition = "text")
    private String moTa;

    // Chi tai entity cha khi can; khong cascade xoa cha khi xoa entity nay.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_chi_nhanh", nullable = false)
    private ChiNhanh chiNhanh;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected KhoaPhong() {
    }

    public KhoaPhong(String tenKhoaPhong, String loaiKhoa, String moTa, ChiNhanh chiNhanh) {
        this.tenKhoaPhong = tenKhoaPhong;
        this.loaiKhoa = loaiKhoa != null ? loaiKhoa : "CHUYEN_KHOA";
        this.moTa = moTa;
        this.chiNhanh = chiNhanh;
    }

    @PrePersist
    void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        if (this.thoiDiemTao == null) {
            this.thoiDiemTao = now;
        }
        if (this.thoiDiemCapNhat == null || this.thoiDiemCapNhat.isBefore(this.thoiDiemTao)) {
            this.thoiDiemCapNhat = this.thoiDiemTao;
        }
        if (this.loaiKhoa == null || this.loaiKhoa.isBlank()) {
            this.loaiKhoa = "CHUYEN_KHOA";
        }
    }

    @PreUpdate
    void onUpdate() {
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }

    public Long getMaKhoaPhong() { return maKhoaPhong; }
    public void updateDetails(String tenKhoaPhong, String loaiKhoa, String moTa, ChiNhanh chiNhanh) {
        this.tenKhoaPhong = tenKhoaPhong;
        this.loaiKhoa = loaiKhoa != null ? loaiKhoa : "CHUYEN_KHOA";
        this.moTa = moTa;
        this.chiNhanh = java.util.Objects.requireNonNull(chiNhanh, "chiNhanh");
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }
    public String getTenKhoaPhong() { return tenKhoaPhong; }
    public void setTenKhoaPhong(String tenKhoaPhong) { this.tenKhoaPhong = tenKhoaPhong; }
    public String getLoaiKhoa() { return loaiKhoa; }
    public void setLoaiKhoa(String loaiKhoa) { this.loaiKhoa = loaiKhoa; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public ChiNhanh getChiNhanh() { return chiNhanh; }
    public void setChiNhanh(ChiNhanh chiNhanh) { this.chiNhanh = chiNhanh; }
    public OffsetDateTime getThoiDiemTao() { return thoiDiemTao; }
    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) { this.thoiDiemTao = thoiDiemTao; }
    public OffsetDateTime getThoiDiemCapNhat() { return thoiDiemCapNhat; }
    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) { this.thoiDiemCapNhat = thoiDiemCapNhat; }
}
