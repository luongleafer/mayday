package mayday.dto.responses;

/** Du lieu phong tra ve client; departmentId la ID khoa so huu. */
public record RoomResponse(Long id, String name, Long departmentId) {
}
