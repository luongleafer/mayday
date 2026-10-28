package mayday.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;
import mayday.dto.responses.ApiResponse;
import mayday.utils.PersistenceUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Chuyen loi nghiep vu, validation va rang buoc database thanh response JSON thong nhat. */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    ResponseEntity<ApiResponse<Object>> handleApiException(ApiException exception) {
        return ResponseEntity.status(exception.getStatus())
                .body(new ApiResponse<>(exception.getCode(), exception.getMessage(), Map.of()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error -> errors.putIfAbsent(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("2003", "Invalid parameter value", errors));
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class})
    ResponseEntity<ApiResponse<Object>> handleMalformedRequest(Exception exception) {
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("2003", "Invalid parameter value", Map.of()));
    }

    // Bat loi rang buoc con lai, ke ca khi du lieu bi thay doi boi request dong thoi.
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<ApiResponse<Object>> handleDataIntegrity(DataIntegrityViolationException exception) {
        if (PersistenceUtils.isUniqueConstraintViolation(exception)) {
            return ResponseEntity.status(409)
                    .body(new ApiResponse<>("2004", "Data already exists", Map.of()));
        }
        return ResponseEntity.badRequest()
                .body(new ApiResponse<>("2003", "Data violates a database constraint", Map.of()));
    }
}
