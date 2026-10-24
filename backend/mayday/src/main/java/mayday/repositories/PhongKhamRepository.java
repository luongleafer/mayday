package mayday.repositories;

import java.util.List;
import mayday.entities.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhongKhamRepository extends JpaRepository<PhongKham, Long> {
    List<PhongKham> findAllByOrderByIdAsc();
    List<PhongKham> findAllByDepartmentIdOrderByIdAsc(Long departmentId);
    boolean existsByDepartmentIdAndNameIgnoreCase(Long departmentId, String name);
    boolean existsByDepartmentIdAndNameIgnoreCaseAndIdNot(Long departmentId, String name, Long id);
}
