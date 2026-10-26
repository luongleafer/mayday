package mayday.entities;

import jakarta.persistence.*;

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

    @Column(name = "mo_ta", columnDefinition = "TEXT")
    private String moTa;

    public KhoaPhong() {
    }

    public KhoaPhong(Long id, ChiNhanh chiNhanh, String tenKhoaPhong, String moTa) {
        this.id = id;
        this.chiNhanh = chiNhanh;
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
}
