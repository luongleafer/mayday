package mayday.repositories;

import mayday.entities.KhoaPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KhoaPhongRepository extends JpaRepository<KhoaPhong, Long> {
    List<KhoaPhong> findByChiNhanhId(Long chiNhanhId);
}
