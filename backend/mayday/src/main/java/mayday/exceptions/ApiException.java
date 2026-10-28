package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Loi nghiep vu co kem ma phan hoi va HTTP status de exception handler xu ly thong nhat. */
public class ApiException extends RuntimeException {
    private final String code;
    private final HttpStatus status;

    public ApiException(String code, HttpStatus status, String message) {
        super(message);
        this.code = code;
        this.status = status;
    }

    public String getCode() { return code; }
    public HttpStatus getStatus() { return status; }
}
