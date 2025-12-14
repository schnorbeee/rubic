package hu.sch.rubicchip.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UninitializedStateException extends RuntimeException {

    private final String message;

    public UninitializedStateException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getStatus() {
        return HttpStatus.PRECONDITION_FAILED;
    }
}
