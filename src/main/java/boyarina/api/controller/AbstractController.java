package boyarina.api.controller;

import boyarina.exception.*;
import boyarina.api.json.HandlerResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public abstract class AbstractController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HandlerResponse> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<HandlerResponse> handleIllegalStateException(IllegalStateException e) {
        log.error("IllegalStateException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<HandlerResponse> handleRateLimitException(RateLimitException e) {
        log.error("RateLimitException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.TOO_MANY_REQUESTS);
    }
}