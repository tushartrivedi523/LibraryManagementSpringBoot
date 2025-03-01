package LibraryManagementSystem.exceptions;

import LibraryManagementSystem.configs.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> bookAlreadyExistsException(BookAlreadyExistsException bookAlreadyExistsException)
    {
        return new ResponseEntity<>(new ErrorResponse(bookAlreadyExistsException.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDate.now()),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoBookExistsException.class)
    public ResponseEntity<ErrorResponse> noBooksExistsException(NoBookExistsException noBookExistsException)
    {
        return new ResponseEntity<>(new ErrorResponse(noBookExistsException.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generalException(Exception exception)
    {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND, LocalDate.now()),HttpStatus.NOT_FOUND);
    }

}
