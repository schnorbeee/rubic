package hu.sch.rubicchip.handlers;

import hu.sch.rubicchip.exceptions.UninitializedStateException;
import hu.sch.rubicchip.exceptions.UnresolvableException;
import hu.sch.rubicchip.handlers.responses.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = UnresolvableException.class)
    public ResponseEntity<ExceptionResponse> handleUnresolvableException(UnresolvableException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getStatus())
                .body(build(e.getStatus(), e));
    }

    @ExceptionHandler(value = UninitializedStateException.class)
    public ResponseEntity<ExceptionResponse> handleUninitializedStateException(UninitializedStateException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(e.getStatus())
                .body(build(e.getStatus(), e));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        log.error(e.getMessage(), e);
        return ResponseEntity.status(status)
                .body(build(status, e));
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        log.error(e.getMessage(), e);
        return ResponseEntity.status(status)
                .body(build(status, e));
    }

    private ExceptionResponse build(HttpStatus statusCode, Throwable throwable) {
        return ExceptionResponse.builder()
                .statusCode(statusCode)
                .message(throwable.getMessage())
                .detail("Location: " + stackTraceToString(throwable.getStackTrace()[0]))
                .build();
    }

    private String stackTraceToString(StackTraceElement origin) {
        String fileName = origin.getFileName();
        return "Fatal :: "
                + (fileName != null ? fileName.substring(0, origin.getFileName().length() - 5) : "Error")
                + " :: "
                + origin.getMethodName();
    }
}
