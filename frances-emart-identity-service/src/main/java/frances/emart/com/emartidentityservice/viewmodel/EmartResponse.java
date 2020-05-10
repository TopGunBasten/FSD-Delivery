package frances.emart.com.emartidentityservice.viewmodel;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class EmartResponse {
    // HTTP Response Status Code
    private final HttpStatus status;

    // General Error message
    private final String message;

    // Error code
    private final String errorCode;

    private final Date timestamp;

    protected EmartResponse(final String message, final String errorCode, HttpStatus status) {
            this.message = message;
            this.errorCode = errorCode;
            this.status = status;
            this.timestamp = new Date();
        }

    public static EmartResponse of(final String message, final String errorCode, HttpStatus status) {
        return new EmartResponse(message, errorCode, status);
    }

    public Integer getStatus() {
        return status.value();
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}