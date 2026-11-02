package mayday.repositories;

import mayday.entities.nguoi.Vaitro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VaitroRepository extends JpaRepository<Vaitro, Long> {
    Optional<Vaitro> findByTenVaiTro(String tenVaiTro);
}
