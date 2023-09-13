package exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GptServiceException extends RuntimeException {

    public GptServiceException(String message) {
        super(message);
    }
}