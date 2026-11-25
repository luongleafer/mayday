package mayday.repositories;

import mayday.entities.NhatKyUASHangNgay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface NhatKyUASHangNgayRepository extends JpaRepository<NhatKyUASHangNgay,Long> {
    boolean existsByMaBenhNhanAndNgayChamDiem(Long maBenhNhan, LocalDate ngayChamDiem);
    List<NhatKyUASHangNgay> findByMaBenhNhanOrderByNgayChamDiemDesc(Long maBenhNhan);

}
