package mayday.repositories;

import mayday.entities.nguoi.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
    boolean existsByTenDangNhap(String tenDangNhap);

    Optional<TaiKhoan> findByTenDangNhap(String username);

    void deleteTaiKhoanByTenDangNhap(String username);

    @Query("select a from TaiKhoan a join fetch a.nguoi where a.tenDangNhap = :username")
    Optional<TaiKhoan> findWithPersonByTenDangNhap(String username);
}
