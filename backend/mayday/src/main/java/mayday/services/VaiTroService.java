package mayday.services;

import mayday.dto.RoleCreationRequest;
import mayday.dto.RoleDetailResponse;
import mayday.dto.RoleDto;
import mayday.entities.nguoi.VaiTro;
import mayday.exceptions.NotFoundException;
import mayday.exceptions.UserExistsException;
import mayday.repositories.VaiTroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaiTroService {
    private final VaiTroRepository vaiTroRepository;

    public VaiTroService(VaiTroRepository vaiTroRepository) {
        this.vaiTroRepository = vaiTroRepository;
    }

    public RoleDto createRole(RoleCreationRequest request) {
        if (vaiTroRepository.existsByTenVaiTro(request.getName()))
            throw new UserExistsException("Role existed");
        VaiTro role = new VaiTro();
        role.setTenVaiTro(request.getName());
        role.setTenHienThi(request.getName());
        role.setMoTaVaiTro(request.getDescription());
        if (request.getParentRoleId() != null) {
            var parentRoleId = request.getParentRoleId();
            var parentRole = vaiTroRepository.findById(parentRoleId)
                    .orElseThrow(() -> new NotFoundException("role", parentRoleId));
            role.setParentRole(parentRole);
        }
        VaiTro newRole = vaiTroRepository.save(role);
        return new RoleDto(newRole);
    }

    public List<RoleDetailResponse> getAllRoles() {
        return vaiTroRepository.findAll().stream()
                .map(RoleDetailResponse::new)
                .toList();
    }
}
