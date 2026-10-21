package mayday.exceptions;

import org.springframework.http.HttpStatus;

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
