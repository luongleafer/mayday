package mayday.dto.responses;

/** Du lieu khoa tra ve client; branchId la ID chi nhanh so huu. */
public record DepartmentResponse(Long id, String name, String type, String description, Long branchId) {
}
