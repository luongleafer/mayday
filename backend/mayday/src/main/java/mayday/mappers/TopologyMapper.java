package mayday.mappers;

import mayday.dto.responses.BranchResponse;
import mayday.dto.responses.DepartmentResponse;
import mayday.dto.responses.RoomResponse;
import mayday.entities.cosovatchat.ChiNhanh;
import mayday.entities.cosovatchat.KhoaPhong;
import mayday.entities.cosovatchat.PhongKham;
import org.springframework.stereotype.Component;

/**
 * Chuyen entity sang DTO trong transaction, khi cac quan he LAZY van con truy cap duoc.
 */
@Component
public class TopologyMapper {
    public BranchResponse toResponse(ChiNhanh branch) {
        return new BranchResponse(branch.getId(), branch.getTenChiNhanh(), branch.getDiaChi(),
                                  branch.getSoDienThoai(), branch.getTrangThai());
    }

    public DepartmentResponse toResponse(KhoaPhong department) {
        return new DepartmentResponse(department.getId(), department.getName(), department.getType(),
                                      department.getDescription(), department.getBranch().getId());
    }

    public RoomResponse toResponse(PhongKham room) {
        return new RoomResponse(room.getId(), room.getName(), room.getDepartment().getId());
    }
}
