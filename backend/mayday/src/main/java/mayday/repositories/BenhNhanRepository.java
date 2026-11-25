package mayday.repositories;

import mayday.entities.nguoi.BenhNhan;
import mayday.entities.nguoi.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BenhNhanRepository extends JpaRepository<BenhNhan, Long> {
    @Query("SELECT b FROM BenhNhan b JOIN b.nguoi n WHERE " +
            "(:tuKhoa IS NULL OR n.hoTen LIKE %:tuKhoa% OR n.soDienThoai LIKE %:tuKhoa%) AND " +
            "(:gioiTinh IS NULL OR n.gioiTinh = :gioiTinh) AND " +
            "(:namBatDau IS NULL OR YEAR(n.ngaySinh) >= :namBatDau) AND " +
            "(:namKetThuc IS NULL OR YEAR(n.ngaySinh) <= :namKetThuc)")
    Page<BenhNhan> timKiemBenhNhan(
            @Param("tuKhoa") String tuKhoa,
            @Param("gioiTinh") Gender gioiTinh,
            @Param("namBatDau") Integer namBatDau,
            @Param("namKetThuc") Integer namKetThuc,
            Pageable pageable
    );

    @Query("select b from BenhNhan b " +
            "join fetch b.nguoi " +
            "join fetch b.nguoi.taiKhoan " +
            "join fetch b.nguoi.taiKhoan.vaiTro " +
            "where b.maBenhNhan = :id")
    Optional<BenhNhan> loadPatient(Long id);
}
