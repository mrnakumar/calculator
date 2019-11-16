package com.clarivate.interview.controller;

import com.clarivate.interview.response.ApiErrorResponse;
import com.clarivate.interview.response.CalculatorResponse;
import org.springframework.http.ResponseEntity;

public class ResponseUtils {
    private ResponseUtils() {
    }

    static ResponseEntity<CalculatorResponse> wrapSuccess(Object result) {
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

    public static ResponseEntity<ApiErrorResponse> wrapError(ApiErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
