package mayday.medicalrecords;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

@Repository
public class MedicalRecordRepository {
    private static final String COLUMNS = """
            h.ma_ho_so AS medical_record_id,
            h.ma_benh_nhan AS patient_id,
            h.trang_thai AS status,
            h.ma_bac_si AS assigned_doctor_id,
            h.ma_nguoi_tao AS created_by
            """;
    private final JdbcClient jdbc;

    public MedicalRecordRepository(JdbcClient jdbc) {
        this.jdbc = jdbc;
    }

    public List<MedicalRecord> findAll(MedicalRecordQuery query) {
        StringBuilder sql = new StringBuilder("SELECT " + COLUMNS + " FROM hosobenhan h WHERE 1=1");
        if (query.status() != null) {
            sql.append(" AND h.trang_thai = :status");
        }
        if (query.patientId() != null) {
            sql.append(" AND h.ma_benh_nhan = :patientId");
        }
        sql.append(" ORDER BY h.ma_ho_so DESC LIMIT :limit OFFSET :offset");
        var statement = jdbc.sql(sql.toString()).param("limit", query.limit()).param("offset", query.offset());
        if (query.status() != null) {
            statement = statement.param("status", query.status());
        }
        if (query.patientId() != null) {
            statement = statement.param("patientId", query.patientId());
        }
        return statement.query(MedicalRecord.class).list();
    }

    public Optional<MedicalRecord> findById(long id) {
        return jdbc.sql("SELECT " + COLUMNS + " FROM hosobenhan h WHERE h.ma_ho_so = :id")
                .param("id", id).query(MedicalRecord.class).optional();
    }

    public Optional<MedicalRecordSummary> findSummary(long id) {
        return jdbc.sql("""
                SELECT h.ma_ho_so AS medical_record_id, h.trang_thai AS status,
                       n.ho_ten AS patient_name,
                       h.thoi_diem_kham_lan_dau AS created_at
                FROM hosobenhan h
                JOIN benhnhan b ON b.ma_benh_nhan = h.ma_benh_nhan
                JOIN nguoi n ON n.ma_nguoi = b.ma_benh_nhan
                WHERE h.ma_ho_so = :id
                """)
                .param("id", id).query(MedicalRecordSummary.class).optional();
    }
}
