package mayday.services.hosobenhan;

import mayday.dto.MedicalRecord;
import mayday.dto.MedicalRecordQuery;
import mayday.dto.MedicalRecordSummary;
import mayday.exceptions.MedicalRecordException;
import mayday.repositories.MedicalRecordRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class TruyXuatBenhAnService {
    private static final Set<String> STAFF = Set.of("ROLE_ADMIN", "ROLE_DOCTOR", "ROLE_RECEPTIONIST");
    private final MedicalRecordRepository repository;

    public TruyXuatBenhAnService(MedicalRecordRepository repository) {
        this.repository = repository;
    }

    public List<MedicalRecord> findAll(MedicalRecordQuery query, Authentication user) {
        if (!isStaff(user)) {
            throw MedicalRecordException.forbidden();
        }
        return repository.findAll(query);
    }

    public MedicalRecord findById(long id, Authentication user) {
        if (id <= 0) {
            throw MedicalRecordException.invalid("id must be positive");
        }
        MedicalRecord record = repository.findById(id).orElseThrow(MedicalRecordException::notFound);
        requirePatientAccess(record.patient_id(), user);
        return record;
    }

    public MedicalRecordSummary summary(long id, Authentication user) {
        findById(id, user);
        return repository.findSummary(id).orElseThrow(MedicalRecordException::notFound);
    }

    public List<MedicalRecord> history(MedicalRecordQuery query, Authentication user) {
        requirePatientAccess(query.patientId(), user);
        return repository.findAll(query);
    }

    private void requirePatientAccess(Long patientId, Authentication user) {
        if (isStaff(user)) {
            return;
        }
        boolean patient = user.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_PATIENT"));
        if (!patient || !String.valueOf(patientId).equals(user.getName())) {
            throw MedicalRecordException.forbidden();
        }
    }

    private boolean isStaff(Authentication user) {
        return user.getAuthorities().stream().anyMatch(authority -> STAFF.contains(authority.getAuthority()));
    }
}
