package mayday.repositories;

import mayday.entities.Benhnhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BenhnhanRepository extends JpaRepository<Benhnhan, Long> {
    @Query("SELECT b FROM Benhnhan b JOIN b.nguoi n WHERE " +
            "(:tuKhoa IS NULL OR n.hoTen LIKE %:tuKhoa% OR n.soDienThoai LIKE %:tuKhoa%) AND " +
            "(:gioiTinh IS NULL OR n.gioiTinh = :gioiTinh) AND " +
            "(:namBatDau IS NULL OR YEAR(n.ngaySinh) >= :namBatDau) AND " +
            "(:namKetThuc IS NULL OR YEAR(n.ngaySinh) <= :namKetThuc)")
    Page<Benhnhan> timKiemBenhNhan(
            @Param("tuKhoa") String tuKhoa,
            @Param("gioiTinh") String gioiTinh,
            @Param("namBatDau") Integer namBatDau,
            @Param("namKetThuc") Integer namKetThuc,
            Pageable pageable
    );
}
