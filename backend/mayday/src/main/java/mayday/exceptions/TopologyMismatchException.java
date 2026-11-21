package mayday.exceptions;

import org.springframework.http.HttpStatus;

public class TopologyMismatchException extends ApiException {
    public TopologyMismatchException(String message) {
        super("1004", HttpStatus.BAD_REQUEST, message);
    }
}
