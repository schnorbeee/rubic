package hu.sch.rubicchip.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnresolvableException extends RuntimeException {

    private final String message;

    public UnresolvableException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getStatus() {
        return HttpStatus.EXPECTATION_FAILED;
    }
}
