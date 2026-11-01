package mayday.repositories;

import mayday.entities.Hosobenhan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HosobenhanRepository extends JpaRepository<Hosobenhan, Long> {

    List<Hosobenhan> findByBenhnhan_MaBenhNhan(Long maBenhNhan);
}