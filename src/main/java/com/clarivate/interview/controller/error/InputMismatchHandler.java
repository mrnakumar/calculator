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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class InputMismatchHandler {
    private static final Marker MARKER = MarkerFactory.getMarker("INPUT_ERROR");

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<ApiErrorResponse> handleInputMismatch(MethodArgumentTypeMismatchException ex) {
        log.error(MARKER, "failed to accept input", ex);
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
        return ResponseUtils.wrapError(response);
    }
}
