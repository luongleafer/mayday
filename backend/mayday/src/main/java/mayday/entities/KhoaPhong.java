package mayday.entities;

import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "khoaphong")
public class KhoaPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khoa_phong")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_chi_nhanh", nullable = false)
    private ChiNhanh chiNhanh;

    @Column(name = "ten_khoa_phong", nullable = false, length = 150)
    private String tenKhoaPhong;

    @Column(name = "loai_khoa", nullable = false, length = 50)
    private String loaiKhoa;

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public KhoaPhong() {
    }

    public KhoaPhong(Long id, ChiNhanh chiNhanh, String loaiKhoa, String tenKhoaPhong, String moTa) {
        this.id = id;
        this.chiNhanh = chiNhanh;
        this.loaiKhoa = loaiKhoa;
        this.tenKhoaPhong = tenKhoaPhong;
        this.moTa = moTa;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ChiNhanh getChiNhanh() {
        return chiNhanh;
    }

    public void setChiNhanh(ChiNhanh chiNhanh) {
        this.chiNhanh = chiNhanh;
    }

    public String getLoaiKhoa() {
        return loaiKhoa;
    }
    public void setLoaiKhoa(String loaiKhoa) {
        this.loaiKhoa = loaiKhoa;
    }

    public String getTenKhoaPhong() {
        return tenKhoaPhong;
    }

    public void setTenKhoaPhong(String tenKhoaPhong) {
        this.tenKhoaPhong = tenKhoaPhong;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onCreate() {
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = OffsetDateTime.now(ZoneOffset.UTC);
    }
}
