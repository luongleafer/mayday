package mayday.repositories;

import mayday.dto.MedicalRecord;
import mayday.dto.MedicalRecordSummary;
import mayday.entities.hosobenhan.HoSoBenhAn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface HoSoBenhAnRepository extends CrudRepository<HoSoBenhAn, Long> {

    @Modifying
    @Query("update HoSoBenhAn h set h.trangThai = :trangThai where h.maHoSo = :maHoSo")
    void updateTrangThai(@Param(value = "maHoSo") Long maHoSo, @Param(value = "trangThai") String trangThai);


    List<HoSoBenhAn> findByBenhNhan_MaBenhNhan(Long maBenhNhan);

    @Query(
            "select h from HoSoBenhAn h " +
                    "where (:trangThai is null or h.trangThai = :trangThai) " +
                    "and (:maBenhNhan is null or h.maBenhNhan = :maBenhNhan)" +
                    "order by h.maHoSo desc limit :limit offset :offset"
    )
    List<MedicalRecord> findAll(String trangThai, Long maBenhNhan, Integer limit, Long offset);

    @Query("select h from HoSoBenhAn h where h.maHoSo = :id")
    Optional<MedicalRecord> find(Long id);

    @Query(
            "select h.maHoSo, h.trangThai, n.hoTen as tenBenhNhan from HoSoBenhAn h join Nguoi n on h.maBenhNhan = n.maNguoi where h.maHoSo = :id"
    )
    Optional<MedicalRecordSummary> findSummary(Long id);

    @Query("SELECT h FROM HoSoBenhAn h WHERE h.maBenhNhan = :maBenhNhan AND h.trangThai NOT IN ('completed', 'cancelled')")
    Optional<HoSoBenhAn> findActiveByMaBenhNhan(@Param("maBenhNhan") Long maBenhNhan);

    @Query(
            "select h.genericRecordInformation.thoiDiemKhamLanDau from HoSoBenhAn h where h.maBenhNhan = :id order by h.genericRecordInformation.thoiDiemKhamLanDau asc limit 1"
    )
    Optional<OffsetDateTime> firstRecordById(Long id);
}
