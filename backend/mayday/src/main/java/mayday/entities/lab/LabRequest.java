package mayday.entities.lab;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lab_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medical_record_id", nullable = false)
    private Long medicalRecordId;

    @Column(name = "requested_by", nullable = false)
    private Long requestedBy;

    @Builder.Default
    @Column(nullable = false)
    private String status = "pending";

    @Column(columnDefinition = "text")
    private String note;

    @Builder.Default
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt = OffsetDateTime.now();

    @Builder.Default
    @OneToMany(mappedBy = "labRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LabRequestItem> items = new ArrayList<>();

    public void addItem(LabRequestItem item) {
        items.add(item);
        item.setLabRequest(this);
    }
}
