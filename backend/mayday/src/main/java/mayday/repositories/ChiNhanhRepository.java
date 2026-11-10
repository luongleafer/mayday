package mayday.repositories;

import mayday.entities.ChiNhanh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Spring Data JPA cung cap CRUD; cac ham danh sach sap xep theo ID tang dan va kiem tra trung lap. */
@Repository
public interface ChiNhanhRepository extends JpaRepository<ChiNhanh, Long> {
    List<ChiNhanh> findAllByOrderByMaChiNhanhAsc();
    boolean existsByTenChiNhanhIgnoreCase(String tenChiNhanh);
    boolean existsByTenChiNhanhIgnoreCaseAndMaChiNhanhNot(String tenChiNhanh, Long maChiNhanh);
    boolean existsByDiaChiIgnoreCase(String diaChi);
    boolean existsByDiaChiIgnoreCaseAndMaChiNhanhNot(String diaChi, Long maChiNhanh);
    boolean existsBySoDienThoai(String soDienThoai);
    boolean existsBySoDienThoaiAndMaChiNhanhNot(String soDienThoai, Long maChiNhanh);
}
