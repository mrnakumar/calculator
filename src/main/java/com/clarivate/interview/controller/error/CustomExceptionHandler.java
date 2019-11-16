package com.clarivate.interview.controller.error;

import com.clarivate.interview.controller.ResponseUtils;
import com.clarivate.interview.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class CustomExceptionHandler {
    private static final Marker MARKER = MarkerFactory.getMarker("COMPUTATION_ERROR");

    @ExceptionHandler({ArithmeticException.class, IllegalArgumentException.class})
    protected ResponseEntity<ApiErrorResponse> handleCustomException(Exception ex) {
        log.error(MARKER, "computation failed", ex);
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseUtils.wrapError(response);
    }
}