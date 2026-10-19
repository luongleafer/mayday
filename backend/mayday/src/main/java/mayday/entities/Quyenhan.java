package mayday.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "quyenhan", schema = "public")
public class Quyenhan {

    @Id
    @Column(name = "ma_quyen_han", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maQuyenHan;

    @Column(name = "ten_quyen_han", nullable = false)
    private String tenQuyenHan;

    @Column(name = "nhom_quyen")
    private String nhomQuyen;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "thoi_diem_tao", nullable = false)
    private OffsetDateTime thoiDiemTao;

    @Column(name = "thoi_diem_cap_nhat", nullable = false)
    private OffsetDateTime thoiDiemCapNhat;

    protected Quyenhan() {
    }

    public Long getMaQuyenHan() {
        return maQuyenHan;
    }

    public void setMaQuyenHan(Long maQuyenHan) {
        this.maQuyenHan = maQuyenHan;
    }

    public String getTenQuyenHan() {
        return tenQuyenHan;
    }

    public void setTenQuyenHan(String tenQuyenHan) {
        this.tenQuyenHan = tenQuyenHan;
    }

    public String getNhomQuyen() {
        return nhomQuyen;
    }

    public void setNhomQuyen(String nhomQuyen) {
        this.nhomQuyen = nhomQuyen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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
