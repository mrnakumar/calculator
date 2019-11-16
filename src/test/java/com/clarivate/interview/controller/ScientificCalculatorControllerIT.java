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
public class ScientificCalculatorControllerIT {
    private static final String TEMPLATE_SQUARE = "square/of/%d";
    private static final String TEMPLATE_FACTORIAL = "factorial/of/%d";
    private static final String TEMPLATE_IS_PRIME = "isprime/%d";

    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    @Test
    public void square_noOverflow_ShouldReturnNumberSquared() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_SQUARE, 5),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(25);
    }

    @Test
    public void square_overflow_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_SQUARE, Long.MAX_VALUE), ApiErrorResponse.class);
        Utils.assertOverFlow(response);
    }

    @Test
    public void factorial_noOverflow_ShouldReturnFactorialOfInput() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_FACTORIAL, 4),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(24);
    }

    @Test
    public void factorial_overflow_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_FACTORIAL, Long.MAX_VALUE), ApiErrorResponse.class);
        Utils.assertOverFlow(response);
    }

    @Test
    public void factorial_negativeNumber_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(
                Utils.makeUrl(base, TEMPLATE_FACTORIAL, -1), ApiErrorResponse.class);
        ApiErrorResponse body = response.getBody();
        Utils.assertBadRequest(body);
        assertThat(body.getMessage()).isEqualTo("can not get factorial of a negative number");
    }

    @Test
    public void isPrime_inputIsPrime_ShouldReturnTrue() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_IS_PRIME, 5),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(true);
    }

    @Test
    public void isPrime_inputIsNotPrime_ShouldReturnFalse() {
        ResponseEntity<CalculatorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_IS_PRIME, 4),
                CalculatorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getResult()).isEqualTo(false);
    }

    @Test
    public void isPrime_invalidInput_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(Utils.makeUrl(base, "isprime/%s",
                Long.MAX_VALUE + "9"), ApiErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Utils.assertBadRequest(response.getBody());
    }

    @Test
    public void isPrime_inputLessThanTwo_ShouldThrowError() {
        ResponseEntity<ApiErrorResponse> response = template.getForEntity(Utils.makeUrl(base, TEMPLATE_IS_PRIME, 1),
                ApiErrorResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ApiErrorResponse body = response.getBody();
        Utils.assertBadRequest(body);
        assertThat(body.getMessage()).isEqualTo("N should be greater than 1");
    }
}