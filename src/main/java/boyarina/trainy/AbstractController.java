package boyarina.trainy;

import boyarina.api.json.HandlerResponse;
import boyarina.exception.NotFoundException;
import boyarina.trainy.mvc.second.exception.DuplicateValueException;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.SignatureException;

@Log4j2
@ControllerAdvice
public class AbstractController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<HandlerResponse> handleNotFoundException(NotFoundException e) {
        log.error("NotFoundException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<HandlerResponse> handleDuplicateKeyValueException(DuplicateValueException e) {
        log.error("DuplicateKeyValueException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HandlerResponse> handleRuntimeException(RuntimeException e) {
        log.error("RuntimeException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<HandlerResponse> handleBadCredentialsException(BadCredentialsException e) {
        log.error("BadCredentialsException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<HandlerResponse> handleExpiredJwtException(ExpiredJwtException e) {
        log.error("ExpiredJwtException throw");
        HandlerResponse response = new HandlerResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
}