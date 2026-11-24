package mayday.repositories;

import mayday.entities.nguoi.QuyenHan;
import org.springframework.data.repository.CrudRepository;

public interface QuyenHanRepository extends CrudRepository<QuyenHan, Long> {
    boolean existsByTenQuyenHan(String tenQuyenHan);
}
