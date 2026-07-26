package taskflow.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private final int status;
    private final String message;
    private final LocalDateTime localDateTime;

    public ErrorResponse(int status, String message){
        this.status = status;
        this.message = message;
        this.localDateTime = LocalDateTime.now();
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
