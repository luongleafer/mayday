package mayday.exceptions;

import org.springframework.http.HttpStatus;

/** Bao tai nguyen hoac khoa ngoai khong ton tai: HTTP 404 va ma 2003 theo tab 3. response code & Slide 4. */
public class NotFoundException extends ApiException {
    public NotFoundException(String resource, Long id) {
        super("2003", HttpStatus.NOT_FOUND, resource + " with id " + id + " was not found");
    }
}
