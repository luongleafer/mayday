package mayday.repositories;

import mayday.entities.nguoi.Nguoi;
import mayday.entities.nguoi.NhanVien;
import org.springframework.data.repository.CrudRepository;

public interface NhanVienRepository extends CrudRepository<NhanVien, Long> {
}
