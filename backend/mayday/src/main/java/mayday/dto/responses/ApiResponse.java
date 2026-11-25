package mayday.dto.responses;

/** Dinh dang response chung: code la ma nghiep vu, message la thong bao, data la ket qua. */
public record ApiResponse<T>(String code, String message, T data) {
    public static <T> ApiResponse<T> ok(T data) {
        return new ApiResponse<>("1000", "OK", data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return new ApiResponse<>("1000", "Created successfully", data);
    }
}
