package br.com.danllopes.reminderapi.infra;

import br.com.danllopes.reminderapi.exceptions.CategoryNotFoundException;
import br.com.danllopes.reminderapi.exceptions.ReminderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<RestErrorMessage> categoryNotFoundHandler(CategoryNotFoundException exception) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }

    @ExceptionHandler(ReminderNotFoundException.class)
    private ResponseEntity<RestErrorMessage> categoryNotFoundHandler(ReminderNotFoundException exception) {
        RestErrorMessage restErrorMessage = new RestErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessage);
    }
}
