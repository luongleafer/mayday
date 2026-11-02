package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Phan biet loi trung du lieu (2004) va du lieu dang duoc su dung (2005). */
public class ConflictException extends ApiException {
    private ConflictException(String code, String message) {
        super(code, HttpStatus.CONFLICT, message);
    }

    public static ConflictException duplicate(String message) {
        return new ConflictException("2004", message);
    }

    public static ConflictException inUse(String message) {
        return new ConflictException("2005", message);
    }
}
