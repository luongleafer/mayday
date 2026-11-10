package mayday.medicalrecords;

import mayday.controllers.HoSoBenhAnController;
import mayday.dto.responses.ApiResponse;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice(assignableTypes = HoSoBenhAnController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MedicalRecordExceptionHandler {
    @ExceptionHandler(MedicalRecordException.class)
    public ResponseEntity<ApiResponse<Void>> handle(MedicalRecordException exception) {
        return ResponseEntity.status(exception.status())
                .body(new ApiResponse<>(exception.code(), exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<Void>> handleInvalidParameter(MethodArgumentTypeMismatchException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("4000", "Invalid parameter: " + exception.getName(), null));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>> handleIllegalArgument(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse<>("4000", exception.getMessage(), null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidation(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("4000", "Invalid parameter", null));
    }
}
