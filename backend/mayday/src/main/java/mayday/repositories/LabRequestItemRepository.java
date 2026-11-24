package mayday.repositories;

import mayday.entities.lab.LabRequestItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LabRequestItemRepository extends JpaRepository<LabRequestItem, Long> {
    List<LabRequestItem> findByLabRequest_Id(Long labRequestId);
}
