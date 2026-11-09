package mayday.entities.cosovatchat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/**
 * Anh xa bang phongkham; moi phong bat buoc thuoc mot khoa.
 */
@Entity
@Table(name = "phongkham", uniqueConstraints =
@UniqueConstraint(name = "uq_phong_kham_khoa_ten", columnNames = {"ma_khoa_phong", "ten_phong_kham"}))
public class PhongKham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_phong_kham")
    private Long id;

    @Column(name = "ten_phong_kham", nullable = false, length = 150)
    private String name;

    // Chi tai entity cha khi can; khong cascade xoa cha khi xoa entity nay.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_khoa_phong", nullable = false)
    private KhoaPhong department;

    protected PhongKham() {
    }

    public PhongKham(String name, KhoaPhong department) {
        this.name = name;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void updateDetails(String name, KhoaPhong department) {
        this.name = name;
        this.department = java.util.Objects.requireNonNull(department, "department");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public KhoaPhong getDepartment() {
        return department;
    }

    public void setDepartment(KhoaPhong department) {
        this.department = department;
    }
}
