package com.clarivate.interview.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class ApiErrorResponse {
    @Getter
    private int statusCode;

    @Getter
    private HttpStatus status;

    @Getter
    private String message;

    @Getter
    private LocalDateTime timestamp;

    public ApiErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.statusCode = status.value();
        this.message = message;
        this.timestamp = LocalDateTime.now(ZoneOffset.UTC);
    }
}
