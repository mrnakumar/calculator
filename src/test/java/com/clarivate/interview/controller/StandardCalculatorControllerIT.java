package com.clarivate.interview.controller;

import com.clarivate.interview.response.ApiErrorResponse;
import com.clarivate.interview.response.CalculatorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StandardCalculatorControllerIT {
    private static final long MAX_VALUE = Long.MAX_VALUE;
    private static final String TEMPLATE_ADD = "add/%d/and/%d";
    private static final String TEMPLATE_SUBTRACT = "subtract/%d/from/%d";
    private static final String TEMPLATE_MULTIPLY = "multiply/%d/and/%d";
    private static final String TEMPLATE_DIVIDE = "divide/%d/by/%d";

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/calculator/");
    }

    @Test
    public void add_sumWithInLimit_ShouldReturnSum() throws Exception {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_ADD, 10, 20),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        CalculatorResponse body = response.getBody();
        assertThat(body.getResult()).isEqualTo(30);
    }

    @Test
    public void add_sumTooLarge_ShouldReturnError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_ADD,
                MAX_VALUE, 1), ApiErrorResponse.class);
        Utils.assertOverFlow(response);
    }

    @Test
    public void subtract_noOverflow_ShouldReturnDifference() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_SUBTRACT,
                5, 10), CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(5);
    }

    @Test
    public void subtract_overflow_ShouldReturnError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_SUBTRACT, Long.MIN_VALUE, 1), ApiErrorResponse.class);
        Utils.assertOverFlow(response);
    }

    @Test
    public void multiply_multiplicationWithInLimit_ShouldReturnMultiplication() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_MULTIPLY,
                5, 10), CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(50);
    }

    @Test
    public void multiply_multiplicationResultTooLarge_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_MULTIPLY, MAX_VALUE, 2), ApiErrorResponse.class);
        Utils.assertOverFlow(response);
    }

    @Test
    public void divide_byNonZero_ShouldReturnQuotient() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_DIVIDE, 5, 2),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(2);
    }

    @Test
    public void divide_byZero_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_DIVIDE, MAX_VALUE, 0), ApiErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Utils.assertBadRequest(response.getBody());
    }
}