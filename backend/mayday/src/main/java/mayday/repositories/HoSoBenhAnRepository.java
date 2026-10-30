package mayday.repositories;

import mayday.entities.HoSoBenhAn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HoSoBenhAnRepository extends CrudRepository<HoSoBenhAn, Long> {

    @Modifying
    @Query("update HoSoBenhAn h set h.trangThai = :trangThai where h.maHoSo = :maHoSo")
    void updateTrangThai(@Param(value = "maHoSo") Long maHoSo, @Param(value = "trangThai") String trangThai);
}
