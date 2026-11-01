package mayday.repositories;

import mayday.entities.Nguoi;
import mayday.entities.Taikhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaikhoanRepository extends JpaRepository<Taikhoan, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);
}
