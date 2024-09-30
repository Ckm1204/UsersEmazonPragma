package pragma.users.infraestructure.exceptions.advisor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



public class ExceptionResponse {


    private  String message;
    private  String status;
    private  LocalDateTime timestamp;

    public ExceptionResponse(String message, String status, LocalDateTime timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }
    public ExceptionResponse() {
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
