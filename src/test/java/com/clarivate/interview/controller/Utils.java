package com.clarivate.interview.controller;

import com.clarivate.interview.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

class Utils {

    private Utils() {
    }

    public static String makeUrl(URL base, String template, Object... args) {
        return base + String.format(template, args);
    }

    public static void assertOverFlow(ResponseEntity<ApiErrorResponse> response) {
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ApiErrorResponse error = response.getBody();
        assertBadRequest(error);
        assertThat(error.getMessage()).isEqualTo("long overflow");
    }

    public static void assertBadRequest(ApiErrorResponse error) {
        assertThat(error.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(error.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}
