package mayday.repositories;

import mayday.entities.hosobenhan.HoSoBenhAn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoSoBenhAnRepository extends CrudRepository<HoSoBenhAn, Long> {

    @Modifying
    @Query("update HoSoBenhAn h set h.trangThai = :trangThai where h.maHoSo = :maHoSo")
    void updateTrangThai(@Param(value = "maHoSo") Long maHoSo, @Param(value = "trangThai") String trangThai);


    List<HoSoBenhAn> findByBenhNhan_MaBenhNhan(Long maBenhNhan);
}
