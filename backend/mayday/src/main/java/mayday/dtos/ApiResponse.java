package mayday.dtos;

public record ApiResponse<T>(
        String code,
        String message,
        T data
) {
    public static <T> ApiResponse<T> ok(T data) {
        return ok("OK", data);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return new ApiResponse<>("1000", message, data);
    }
}
