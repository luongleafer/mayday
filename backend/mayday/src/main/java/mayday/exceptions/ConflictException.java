package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Bao loi trung du lieu hoac du lieu dang duoc su dung: HTTP 409 va ma 2003 theo tab 3. response code & Slide 4. */
public class ConflictException extends ApiException {
    private ConflictException(String code, String message) {
        super(code, HttpStatus.CONFLICT, message);
    }

    public static ConflictException duplicate(String message) {
        return new ConflictException("2003", message);
    }

    public static ConflictException inUse(String message) {
        return new ConflictException("2003", message);
    }
}
