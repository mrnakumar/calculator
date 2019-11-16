package com.clarivate.interview.controller.error;

import com.clarivate.interview.controller.ResponseUtils;
import com.clarivate.interview.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CatchAllHandler {
    private static final Marker MARKER = MarkerFactory.getMarker("INTERNAL_ERROR");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAll(Exception ex) {
        log.error(MARKER, "internal error", ex);
        ApiErrorResponse response = new ApiErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        return ResponseUtils.wrapError(response);
    }
}
