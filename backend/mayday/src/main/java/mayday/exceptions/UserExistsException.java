package mayday.exceptions;

import org.springframework.http.HttpStatus;

public class UserExistsException extends ApiException {
    public UserExistsException(String message) {
        super("9996", HttpStatus.BAD_REQUEST, message);
    }

    public UserExistsException() {
        this("User existed");
    }
}
