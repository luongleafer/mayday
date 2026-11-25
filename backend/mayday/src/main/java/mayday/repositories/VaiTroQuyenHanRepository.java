package mayday.repositories;

import mayday.entities.nguoi.VaiTroQuyenHan;
import mayday.entities.nguoi.VaiTroQuyenHanId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VaiTroQuyenHanRepository extends CrudRepository<VaiTroQuyenHan, VaiTroQuyenHanId> {

    @Query("select rp from VaiTroQuyenHan rp " +
            "join fetch rp.role " +
            "join fetch rp.permission " +
            "where rp.role.maVaiTro = :id")
    List<VaiTroQuyenHan> loadPermissionByRoleId(Long id);
}
