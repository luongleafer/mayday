package mayday.repositories;

import mayday.entities.phienkham.PhienKham;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PhienKhamRepository extends CrudRepository<PhienKham, Long> {
    boolean existsByMaHoSoAndMaBacSi(Long maHoSo, Long maBacSi);
    boolean existsByMaHoSo(Long maHoSo);
    List<PhienKham> findByMaHoSo(Long maHoSo);
}
