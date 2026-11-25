package mayday.services;

import mayday.entities.nguoi.QuyenHan;
import mayday.exceptions.NotFoundException;
import mayday.exceptions.UserExistsException;
import mayday.repositories.QuyenHanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuyenHanService {
    private final QuyenHanRepository quyenHanRepository;

    public QuyenHanService(QuyenHanRepository quyenHanRepository) {
        this.quyenHanRepository = quyenHanRepository;
    }

    public QuyenHan createPermission(String name, String description) {
        if (quyenHanRepository.existsByTenQuyenHan(name))
            throw new UserExistsException();
        QuyenHan permission = new QuyenHan();
        permission.setTenQuyenHan(name);
        permission.setMoTa(description);
        return quyenHanRepository.save(permission);
    }

    public List<QuyenHan> getAllPermissions() {
        List<QuyenHan> allPermissions = new ArrayList<>();
        quyenHanRepository.findAll().forEach(allPermissions::add);
        return allPermissions;
    }

    public QuyenHan editPermission(Long id, String name, String description) {
        QuyenHan permission = quyenHanRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("role", id));
        if (quyenHanRepository.existsByTenQuyenHan(name))
            throw new UserExistsException();
        if (name != null && !name.isBlank()) {
            permission.setTenQuyenHan(name.trim());
        }
        if (description != null && !description.isBlank()) {
            permission.setMoTa(description.trim());
        }
        quyenHanRepository.save(permission);
        return permission;
    }

    public void deletePermission(Long id) {
        if (!quyenHanRepository.existsById(id))
            throw new NotFoundException("role", id);
        quyenHanRepository.deleteById(id);
    }


}
