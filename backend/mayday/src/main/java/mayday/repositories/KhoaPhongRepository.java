package mayday.repositories;

import java.util.List;
import mayday.entities.KhoaPhong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KhoaPhongRepository extends JpaRepository<KhoaPhong, Long> {
    List<KhoaPhong> findAllByBranchIdOrderByIdAsc(Long branchId);
    boolean existsByBranchIdAndNameIgnoreCase(Long branchId, String name);
    boolean existsByBranchIdAndNameIgnoreCaseAndIdNot(Long branchId, String name, Long id);
}
