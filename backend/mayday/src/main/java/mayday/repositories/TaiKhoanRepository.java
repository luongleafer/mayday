package mayday.repositories;

import mayday.entities.nguoi.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);
}
