package mayday.repositories;

import java.util.List;

import mayday.entities.cosovatchat.KhoaPhong;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Truy van khoa theo chi nhanh va kiem tra trung ten trong pham vi chi nhanh.
 */
public interface KhoaPhongRepository extends JpaRepository<KhoaPhong, Long> {
    List<KhoaPhong> findAllByOrderByIdAsc();

    List<KhoaPhong> findAllByBranchIdOrderByIdAsc(Long branchId);

    boolean existsByBranchIdAndNameIgnoreCase(Long branchId, String name);

    boolean existsByBranchIdAndNameIgnoreCaseAndIdNot(Long branchId, String name, Long id);
}
