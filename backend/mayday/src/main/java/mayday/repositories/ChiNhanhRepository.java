package mayday.repositories;

import java.util.List;
import mayday.entities.ChiNhanh;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChiNhanhRepository extends JpaRepository<ChiNhanh, Long> {
    List<ChiNhanh> findAllByOrderByIdAsc();
}
