package mayday.entities.cosovatchat;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

/** Anh xa bang chinhanh theo NAMING_CONVENTION.md; Hibernate tu dong map camelCase sang snake_case. */
@Entity
@Table(name = "chinhanh", uniqueConstraints = {
        @UniqueConstraint(name = "uq_chi_nhanh_ten", columnNames = {"ten_chi_nhanh"}),
        @UniqueConstraint(name = "uq_chi_nhanh_dia_chi", columnNames = {"dia_chi"}),
        @UniqueConstraint(name = "uq_chi_nhanh_sdt", columnNames = {"so_dien_thoai"})
})
public class ChiNhanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiNhanh;

    @Column(nullable = false, length = 150)
    private String tenChiNhanh;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String diaChi;

    @Column(length = 20)
    private String soDienThoai;

    @Column(nullable = false)
    private Boolean trangThai = true;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    public ChiNhanh() {
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
        if (this.trangThai == null) {
            this.trangThai = true;
        }
    }

    @PreUpdate
    void onUpdate() {
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }

    public void updateDetails(String tenChiNhanh, String diaChi, String soDienThoai) {
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }

    public void updateDetails(String tenChiNhanh, String diaChi, String soDienThoai, Boolean trangThai) {
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        if (trangThai != null) {
            this.trangThai = trangThai;
        }
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }

    public ChiNhanh(Long maChiNhanh, String tenChiNhanh, String diaChi, String soDienThoai, Boolean trangThai) {
        this.maChiNhanh = maChiNhanh;
        this.tenChiNhanh = tenChiNhanh;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.trangThai = trangThai;
    }

    public Long getMaChiNhanh() {
        return maChiNhanh;
    }

    public void setMaChiNhanh(Long maChiNhanh) {
        this.maChiNhanh = maChiNhanh;
    }

    public String getTenChiNhanh() {
        return tenChiNhanh;
    }

    public void setTenChiNhanh(String tenChiNhanh) {
        this.tenChiNhanh = tenChiNhanh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
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
