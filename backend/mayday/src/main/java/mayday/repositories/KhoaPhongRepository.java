package mayday.repositories;

import java.util.List;
import mayday.entities.KhoaPhong;
import org.springframework.data.jpa.repository.JpaRepository;

/** Truy van khoa theo chi nhanh va kiem tra trung ten trong pham vi chi nhanh. */
public interface KhoaPhongRepository extends JpaRepository<KhoaPhong, Long> {
    List<KhoaPhong> findAllByOrderByMaKhoaPhongAsc();
    List<KhoaPhong> findAllByChiNhanh_MaChiNhanhOrderByMaKhoaPhongAsc(Long maChiNhanh);
    boolean existsByChiNhanh_MaChiNhanhAndTenKhoaPhongIgnoreCase(Long maChiNhanh, String tenKhoaPhong);
    boolean existsByChiNhanh_MaChiNhanhAndTenKhoaPhongIgnoreCaseAndMaKhoaPhongNot(Long maChiNhanh, String tenKhoaPhong, Long maKhoaPhong);
}
