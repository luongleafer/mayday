package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Bao du lieu dau vao khong hop le: HTTP 400 va ma nghiep vu 2003. */
public class InvalidRequestException extends ApiException {
    public InvalidRequestException(String message) {
        super("2003", HttpStatus.BAD_REQUEST, message);
    }
}
