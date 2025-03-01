package LibraryManagementSystem.configs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDate localDate;

}
