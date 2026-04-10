package uol.publicidade.url_shortener.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> error400(IllegalArgumentException e, HttpServletRequest request) {
        return defaultExceptionResponse(e.getMessage(), e.getClass().getSimpleName(), request.getServletPath(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> error404(EntityNotFoundException e, HttpServletRequest request) {
        return defaultExceptionResponse(e.getMessage(), e.getClass().getSimpleName(), request.getServletPath(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, HttpServletRequest request) {
        String errorMessage = ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred";
        return defaultExceptionResponse(errorMessage, ex.getClass().getSimpleName(), request.getServletPath(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> defaultExceptionResponse(String message, String exceptionName, String requestPath,
                                                            HttpStatus status) {
        Map<String, String> errors = new LinkedHashMap<>();
        errors.put("message", message);
        errors.put("exception", exceptionName);
        errors.put("status", "" + status.value());
        errors.put("path", requestPath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(errors, headers, status);
    }
}
