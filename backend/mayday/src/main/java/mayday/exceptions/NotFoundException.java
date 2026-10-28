package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Bao tai nguyen khong ton tai: HTTP 404 va ma nghiep vu 2001. */
public class NotFoundException extends ApiException {
    public NotFoundException(String resource, Long id) {
        super("2001", HttpStatus.NOT_FOUND, resource + " with id " + id + " was not found");
    }
}
