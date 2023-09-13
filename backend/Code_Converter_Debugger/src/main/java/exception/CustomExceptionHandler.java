package exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(GptServiceException.class)
    public ResponseEntity<ApiError> handleGptServiceException(GptServiceException ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setError("GPT Service Error");
        apiError.setMessage(ex.getMessage());
        apiError.setPath(request.getDescription(false));
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleOtherExceptions(Exception ex, WebRequest request) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setError("Internal Server Error");
        apiError.setMessage("An internal server error occurred.");
        apiError.setPath(request.getDescription(false));
        
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
