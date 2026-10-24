package mayday.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ApiException {
    public InvalidRequestException(String message) {
        super("2003", HttpStatus.BAD_REQUEST, message);
    }
}
