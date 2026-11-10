package mayday.entities.cosovatchat;

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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/** Anh xa bang phongkham theo NAMING_CONVENTION.md; khi xoa khoa, ma_khoa_phong duoc chuyen ve NULL. */
@Entity
@Table(name = "phongkham", uniqueConstraints =
        @UniqueConstraint(name = "uq_phong_kham_khoa_ten", columnNames = {"ma_khoa_phong", "ten_phong_kham"}))
public class PhongKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maPhongKham;

    @Column(nullable = false, length = 150)
    private String tenPhongKham;

    @Column(columnDefinition = "text")
    private String moTa;

    // Chi tai entity cha khi can; khi xoa khoa cha, ma_khoa_phong tro thanh NULL.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_khoa_phong")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private KhoaPhong khoaPhong;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected PhongKham() {
    }

    public PhongKham(String tenPhongKham, KhoaPhong khoaPhong) {
        this(tenPhongKham, null, khoaPhong);
    }

    public PhongKham(String tenPhongKham, String moTa, KhoaPhong khoaPhong) {
        this.tenPhongKham = tenPhongKham;
        this.moTa = moTa;
        this.khoaPhong = khoaPhong;
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
    }

    @PreUpdate
    void onUpdate() {
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }

    public Long getMaPhongKham() { return maPhongKham; }
    public void updateDetails(String tenPhongKham, KhoaPhong khoaPhong) {
        updateDetails(tenPhongKham, this.moTa, khoaPhong);
    }
    public void updateDetails(String tenPhongKham, String moTa, KhoaPhong khoaPhong) {
        this.tenPhongKham = tenPhongKham;
        this.moTa = moTa;
        this.khoaPhong = khoaPhong;
        this.thoiDiemCapNhat = OffsetDateTime.now();
    }
    public String getTenPhongKham() { return tenPhongKham; }
    public void setTenPhongKham(String tenPhongKham) { this.tenPhongKham = tenPhongKham; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public KhoaPhong getKhoaPhong() { return khoaPhong; }
    public void setKhoaPhong(KhoaPhong khoaPhong) { this.khoaPhong = khoaPhong; }
    public OffsetDateTime getThoiDiemTao() { return thoiDiemTao; }
    public void setThoiDiemTao(OffsetDateTime thoiDiemTao) { this.thoiDiemTao = thoiDiemTao; }
    public OffsetDateTime getThoiDiemCapNhat() { return thoiDiemCapNhat; }
    public void setThoiDiemCapNhat(OffsetDateTime thoiDiemCapNhat) { this.thoiDiemCapNhat = thoiDiemCapNhat; }
}
