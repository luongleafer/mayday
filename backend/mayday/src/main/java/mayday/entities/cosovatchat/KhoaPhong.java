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
 * Anh xa bang khoaphong; moi khoa bat buoc thuoc mot chi nhanh.
 */
@Entity
@Table(name = "khoaphong", uniqueConstraints =
@UniqueConstraint(name = "uq_khoa_phong_chi_nhanh_ten", columnNames = {"ma_chi_nhanh", "ten_khoa_phong"}))
public class KhoaPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_khoa_phong")
    private Long id;

    @Column(name = "ten_khoa_phong", nullable = false, length = 150)
    private String name;

    @Column(name = "loai_khoa", nullable = false, length = 50)
    private String type;

    @Column(name = "mo_ta", columnDefinition = "text")
    private String description;

    // Chi tai entity cha khi can; khong cascade xoa cha khi xoa entity nay.
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ma_chi_nhanh", nullable = false)
    private ChiNhanh branch;

    protected KhoaPhong() {
    }

    public KhoaPhong(String name, String type, String description, ChiNhanh branch) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.branch = branch;
    }

    public Long getId() {
        return id;
    }

    public void updateDetails(String name, String type, String description, ChiNhanh branch) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.branch = java.util.Objects.requireNonNull(branch, "branch");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChiNhanh getBranch() {
        return branch;
    }

    public void setBranch(ChiNhanh branch) {
        this.branch = branch;
    }
}
