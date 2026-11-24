package mayday.repositories;

import mayday.entities.lab.LabRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRequestRepository extends JpaRepository<LabRequest, Long> {
    List<LabRequest> findByMedicalRecordIdOrderByCreatedAtDesc(Long medicalRecordId);
}
