package mayday.repositories;

import java.util.List;
import mayday.entities.cosovatchat.PhongKham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/** Truy van phong theo khoa va kiem tra trung ten trong pham vi khoa/chi nhanh. */
public interface PhongKhamRepository extends JpaRepository<PhongKham, Long> {
    List<PhongKham> findAllByOrderByMaPhongKhamAsc();
    List<PhongKham> findAllByKhoaPhong_MaKhoaPhongOrderByMaPhongKhamAsc(Long maKhoaPhong);
    boolean existsByKhoaPhong_MaKhoaPhongAndTenPhongKhamIgnoreCase(Long maKhoaPhong, String tenPhongKham);
    boolean existsByKhoaPhong_MaKhoaPhongAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(Long maKhoaPhong, String tenPhongKham, Long maPhongKham);
    boolean existsByKhoaPhong_ChiNhanh_MaChiNhanhAndTenPhongKhamIgnoreCase(Long maChiNhanh, String tenPhongKham);
    boolean existsByKhoaPhong_ChiNhanh_MaChiNhanhAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(Long maChiNhanh, String tenPhongKham, Long maPhongKham);
    boolean existsByKhoaPhongIsNullAndTenPhongKhamIgnoreCaseAndMaPhongKhamNot(String tenPhongKham, Long maPhongKham);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE PhongKham r SET r.khoaPhong = null WHERE r.khoaPhong.maKhoaPhong = :maKhoaPhong")
    void detachDepartment(@Param("maKhoaPhong") Long maKhoaPhong);
    boolean existsByDepartmentIdAndNameIgnoreCase(Long departmentId, String name);

    boolean existsByDepartmentIdAndNameIgnoreCaseAndIdNot(Long departmentId, String name, Long id);

    boolean existsPhongKhamByIdAndDepartment_Id(Long id, Long departmentId);
}
