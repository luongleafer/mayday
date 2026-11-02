package mayday.repositories;

import mayday.entities.nguoi.Nguoi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiRepository extends JpaRepository<Nguoi, Long> {
}
