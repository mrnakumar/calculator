package com.clarivate.interview.controller;

import com.clarivate.interview.response.ApiErrorResponse;
import com.clarivate.interview.response.CalculatorResponse;
import com.clarivate.interview.service.ScientificCalculator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@Api(tags = "Scientific calculator api", description = "middle school operations")
@RestController
@RequestMapping("/calculator")
public class ScientificCalculatorController {
    private final ScientificCalculator calculator;

    @Autowired
    public ScientificCalculatorController(ScientificCalculator calculator) {
        this.calculator = calculator;
    }

    @ApiOperation(value = "Get the square of a number", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculates the square of input number",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Operation caused number overflow or input number invalid",
                    response = ApiErrorResponse.class)
    })
    @GetMapping(value = "/square/of/{number}")
    public ResponseEntity<CalculatorResponse> square(@PathVariable("number") long number) {
        return ResponseUtils.wrapSuccess(calculator.square(number));
    }

    @ApiOperation(value = "Get the factorial of a number", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully calculates the factorial of input number",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400,
                    message = "Operation caused number overflow or input number is negative or input invalid ",
                    response = ApiErrorResponse.class)
    })
    @GetMapping(value = "/factorial/of/{number}")
    public ResponseEntity<CalculatorResponse> factorial(@PathVariable("number") @Size(min = 0) long number) {
        return ResponseUtils.wrapSuccess(calculator.factorial(number));
    }

    @ApiOperation(value = "Tells if a number is prime", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully determines if input number is prime",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Input is less than 2", response = ApiErrorResponse.class)
    })
    @GetMapping(value = "/isprime/{number}")
    public ResponseEntity<CalculatorResponse> isPrime(@PathVariable("number") @Size(min = 2) long number) {
        return ResponseUtils.wrapSuccess(calculator.isPrime(number));
    }
}
