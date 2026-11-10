package mayday.repositories;

import mayday.entities.hosobenhan.HoSoBenhAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HoSoBenhAnRepository extends JpaRepository<HoSoBenhAn, Long> {

    List<HoSoBenhAn> findByBenhNhan_MaBenhNhan(Long maBenhNhan);

    @Modifying
    @Query("update HoSoBenhAn h set h.trangThai = :trangThai where h.maHoSo = :maHoSo")
    void updateTrangThai(@Param(value = "maHoSo") Long maHoSo, @Param(value = "trangThai") String trangThai);
}
