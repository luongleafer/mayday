package mayday.repositories;

import mayday.entities.nguoi.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaikhoanRepository extends JpaRepository<Taikhoan, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);
}
