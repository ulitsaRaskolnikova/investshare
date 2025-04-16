package ulitsa.raskolnikova.investshare.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ulitsa.raskolnikova.investshare.exception.ExpiredRecoverPasswordTokenException;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ProjectsExceptionsHandler {
    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({
            NoSuchElementException.class,
            ExpiredRecoverPasswordTokenException.class,
            IllegalArgumentException.class,
            InvalidBasicAuthorizationException.class
    })
    public ResponseEntity<String> handleNoSuchElementException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}
