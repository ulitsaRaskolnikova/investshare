package ulitsa.raskolnikova.investshare.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;

@ControllerAdvice
public class ProjectsExceptionsHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleValidationException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidBasicAuthorizationException.class)
    public ResponseEntity<String> handleInvalidBasicAuthorizationException(InvalidBasicAuthorizationException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
