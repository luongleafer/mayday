package mayday.services;

import mayday.dto.responses.RolePermissionDetail;
import mayday.entities.nguoi.*;
import mayday.exceptions.NotFoundException;
import mayday.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VaiTroQuyenHanService {

    private final VaiTroRepository vaiTroRepository;
    private final QuyenHanRepository quyenHanRepository;
    private final VaiTroQuyenHanRepository vaiTroQuyenHanRepository;
    private final TaiKhoanRepository taiKhoanRepository;

    public VaiTroQuyenHanService(VaiTroRepository vaiTroRepository, QuyenHanRepository quyenHanRepository, VaiTroQuyenHanRepository vaiTroQuyenHanRepository, NguoiRepository nguoiRepository, TaiKhoanRepository taiKhoanRepository) {
        this.vaiTroRepository = vaiTroRepository;
        this.quyenHanRepository = quyenHanRepository;
        this.vaiTroQuyenHanRepository = vaiTroQuyenHanRepository;
        this.taiKhoanRepository = taiKhoanRepository;
    }

    @Transactional
    public void addPermissionToRole(Long roleId, List<Long> permissionIds, Long accountId) {
        VaiTro role = vaiTroRepository.findById(roleId)
                .orElseThrow(
                        () -> new NotFoundException("role", roleId)
                );
        Long assigneeId = taiKhoanRepository.loadPersonIdByAccountId(accountId).orElseThrow(
                () -> new NotFoundException("person with account", accountId)
        );
        List<VaiTroQuyenHan> newRolePerms = new ArrayList<>();
        permissionIds.forEach(
                (permissionId) -> {
                    QuyenHan permission = quyenHanRepository.findById(permissionId)
                            .orElseThrow(
                                    () -> new NotFoundException("permission", permissionId)
                            );
                    if (vaiTroQuyenHanRepository.existsById(
                            new VaiTroQuyenHanId(roleId, permissionId)
                    )) return;
                    VaiTroQuyenHan permRole = new VaiTroQuyenHan();
                    permRole.setRole(role);
                    permRole.setPermission(permission);
                    permRole.setMaNguoiGan(assigneeId);
                    newRolePerms.add(permRole);
                }
        );
        vaiTroQuyenHanRepository.saveAll(newRolePerms);
    }

    public List<RolePermissionDetail> getRolePermissions(Long roleId) {
        if (!vaiTroRepository.existsById(roleId))
            throw new NotFoundException("role", roleId);
        List<VaiTroQuyenHan> rolePerms = vaiTroQuyenHanRepository.loadPermissionByRoleId(roleId);
        return rolePerms.stream().map(RolePermissionDetail::new).toList();
    }

    public void deleteRolePermission(Long roleId, Long permissionId) {
        vaiTroQuyenHanRepository.deleteById(new VaiTroQuyenHanId(roleId, permissionId));
    }


}

