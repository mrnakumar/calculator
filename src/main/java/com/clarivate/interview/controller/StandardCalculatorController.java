package com.clarivate.interview.controller;

import com.clarivate.interview.response.ApiErrorResponse;
import com.clarivate.interview.response.CalculatorResponse;
import com.clarivate.interview.service.StandardCalculator;
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

@Api(tags = "Standard calculator api", description = "primary school operations")
@RestController
@RequestMapping("/calculator")
public class StandardCalculatorController {
    private final StandardCalculator calculator;

    @Autowired
    public StandardCalculatorController(StandardCalculator calculator) {
        this.calculator = calculator;
    }

    @ApiOperation(value = "Add two number", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully adds given numbers", response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Operation caused number overflow or input invalid",
                    response = ApiErrorResponse.class)
    })
    @GetMapping("/add/{a}/and/{b}")
    public ResponseEntity<CalculatorResponse> add(@PathVariable("a") long a, @PathVariable("b") long b) {
        return ResponseUtils.wrapSuccess(calculator.add(a, b));
    }

    @ApiOperation(value = "Subtract b from a, ie. performs a-b", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully difference of input numbers",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Operation caused number overflow or input number invalid",
                    response = ApiErrorResponse.class)
    })
    @GetMapping("/subtract/{b}/from/{a}")
    public ResponseEntity<CalculatorResponse> subtract(@PathVariable("a") long a, @PathVariable("b") long b) {
        return ResponseUtils.wrapSuccess(calculator.subtract(a, b));
    }

    @ApiOperation(value = "Multiply a by b", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully multiply given numbers",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Operation caused number overflow or input number invalid",
                    response = ApiErrorResponse.class)
    })
    @GetMapping("/multiply/{a}/and/{b}")
    public ResponseEntity<CalculatorResponse> multiply(@PathVariable("a") long a, @PathVariable("b") long b) {
        return ResponseUtils.wrapSuccess(calculator.multiply(a, b));
    }

    @ApiOperation(value = "Divides a by b", response = CalculatorResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully performs the division operation",
                    response = ApiErrorResponse.class),
            @ApiResponse(code = 400, message = "Input invalid or divide by zero", response = ApiErrorResponse.class)
    })
    @GetMapping("/divide/{a}/by/{b}")
    public ResponseEntity<CalculatorResponse> divide(@PathVariable("a") long a, @PathVariable("b") long b) {
        return ResponseUtils.wrapSuccess(calculator.divide(a, b));
    }
}
