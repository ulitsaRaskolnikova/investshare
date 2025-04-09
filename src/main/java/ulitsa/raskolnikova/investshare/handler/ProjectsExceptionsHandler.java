package ulitsa.raskolnikova.investshare.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectsExceptionsHandler {
    private static final String INVALID_QUERY_PARAMETERS_MESSAGE = "Некорректные параметры запроса";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> handleValidationException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return ResponseEntity.notFound().build();
    }
}
