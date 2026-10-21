package mayday.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {
    public NotFoundException(String resource, Long id) {
        super("2001", HttpStatus.NOT_FOUND, resource + " with id " + id + " was not found");
    }
}
