package mayday.repositories;

import mayday.entities.Nguoi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NguoiRepository extends JpaRepository<Nguoi, Long> {
}
