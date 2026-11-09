package mayday.repositories;

import mayday.entities.nguoi.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);

    Optional<TaiKhoan> findByTenDangNhap(String username);

    void deleteTaiKhoanByTenDangNhap(String username);
}
