package mayday.exceptions;

import mayday.controllers.hosobenhan.TruyXuatBenhAnController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import mayday.dto.responses.ApiResponse;

@RestControllerAdvice(basePackageClasses = TruyXuatBenhAnController.class)
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
}
