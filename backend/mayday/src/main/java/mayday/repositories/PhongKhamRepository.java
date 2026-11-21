package mayday.repositories;

import java.util.List;

import mayday.entities.cosovatchat.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Truy van phong theo khoa va kiem tra trung ten trong pham vi khoa.
 */
public interface PhongKhamRepository extends JpaRepository<PhongKham, Long> {
    List<PhongKham> findAllByOrderByIdAsc();

    List<PhongKham> findAllByDepartmentIdOrderByIdAsc(Long departmentId);

    boolean existsByDepartmentIdAndNameIgnoreCase(Long departmentId, String name);

    boolean existsByDepartmentIdAndNameIgnoreCaseAndIdNot(Long departmentId, String name, Long id);

    boolean existsPhongKhamByIdAndDepartment_Id(Long id, Long departmentId);
}
