package mayday.exceptions;

import org.springframework.http.HttpStatus;

public class MedicalRecordException extends RuntimeException {
    private final HttpStatus status;
    private final String code;

    public MedicalRecordException(HttpStatus status, String code, String message) {
        super(message);
        this.status = status;
        this.code = code;
    }

    public HttpStatus status() {
        return status;
    }

    public String code() {
        return code;
    }

    public static MedicalRecordException notFound() {
        return new MedicalRecordException(HttpStatus.NOT_FOUND, "4001", "Medical record not found");
    }

    public static MedicalRecordException forbidden() {
        return new MedicalRecordException(HttpStatus.FORBIDDEN, "3106", "Cannot access another user's data");
    }

    public static MedicalRecordException invalid(String message) {
        return new MedicalRecordException(HttpStatus.BAD_REQUEST, "4000", message);
    }
}
