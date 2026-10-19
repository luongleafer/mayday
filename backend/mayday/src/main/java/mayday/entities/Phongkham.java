package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "phongkham", schema = "public")
public class Phongkham {

    @Id
    @Column(name = "ma_phong_kham", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhongKham;

    @Column(name = "ten_phong_kham", nullable = false)
    private String tenPhongKham;

    @Column(name = "ma_khoa_phong", nullable = false)
    private Long maKhoaPhong;

    protected Phongkham() {
    }

    public Long getMaPhongKham() {
        return maPhongKham;
    }

    public void setMaPhongKham(Long maPhongKham) {
        this.maPhongKham = maPhongKham;
    }

    public String getTenPhongKham() {
        return tenPhongKham;
    }

    public void setTenPhongKham(String tenPhongKham) {
        this.tenPhongKham = tenPhongKham;
    }

    public Long getMaKhoaPhong() {
        return maKhoaPhong;
    }

    public void setMaKhoaPhong(Long maKhoaPhong) {
        this.maKhoaPhong = maKhoaPhong;
    }

}
