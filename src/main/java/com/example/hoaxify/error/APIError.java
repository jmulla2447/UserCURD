package com.example.hoaxify.error;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class APIError {

    private LocalDate now = LocalDate.now();

    private long status;

    private String message;

    private String uri;

    private Map<String, String> errorDetails;

    public APIError() {
    }

    public APIError(long status, String message, String uri) {
        this.status = status;
        this.message = message;
        this.uri = uri;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrorDetails() {
        if(errorDetails== null){
            errorDetails = new HashMap<>();
        }
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
