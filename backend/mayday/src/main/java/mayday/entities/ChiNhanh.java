package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "chinhanh")
public class ChiNhanh {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_chi_nhanh")
    private Long id;

    @Column(name = "ten_chi_nhanh", nullable = false, length = 150)
    private String name;

    @Column(name = "dia_chi", nullable = false, columnDefinition = "text")
    private String address;

    @Column(name = "so_dien_thoai", length = 20)
    private String phone;

    @Column(name = "trang_thai", nullable = false)
    private boolean active = true;

    protected ChiNhanh() {
    }

    public ChiNhanh(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
