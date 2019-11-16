package com.clarivate.interview.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@RunWith(JUnitParamsRunner.class)
public class StandardCalculatorTest {
    private final StandardCalculator calculator = new StandardCalculator();

    @Test
    @Parameters({
            "10,200,210",
            "0,200,200",
            "200,0,200",
            "200,-10,190",
            "-200,10,-190",
            "-10,-10,-20",
            "10,0,10",
            "0,10,10",
            "0,-10,-10",
            "-10,0,-10",
            "0,0,0"

    })
    public void add_noOverflow_returnsSumOfGivenNumbers(long a, long b, long expected) {
        assertThat(calculator.add(a, b)).isEqualTo(expected);
    }

    @Test
    public void add_overflow_throwsArithmeticException() {
        assertThatThrownBy(() -> calculator.add(Long.MAX_VALUE, 1)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    @Parameters({
            "10,200,-190",
            "200,10,190",
            "10,0,10",
            "0,10,-10",
            "0,0,0",
            "-10,-10,0"
    })
    public void subtract_noOverflow_returnsDifferenceBetweenGivenNumbers(long a, long b, long expected) {
        assertThat(calculator.subtract(a, b)).isEqualTo(expected);
    }

    @Test
    public void subtract_overflow_throwsArithmeticException() {
        assertThatThrownBy(() -> calculator.subtract(Long.MIN_VALUE, 1)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    @Parameters({
            "10,20,200",
            "10,0,0",
            "10,1,10",
            "-10,2,-20",
            "-10,-2,20"
    })
    public void multiply_noOverflow_returnsMultiplicationOfGivenNumbers(long a, long b, long expected) {
        assertThat(calculator.multiply(a, b)).isEqualTo(expected);
    }

    @Test
    public void multiply_overflow_throwsArithmeticException() {
        assertThatThrownBy(() -> calculator.multiply(Long.MAX_VALUE, 2)).isInstanceOf(ArithmeticException.class);
    }

    @Test
    @Parameters({
            "10,2,5",
            "2,5,0",
            "10,1,10"
    })
    public void divison_validDivisor_integerQuotient(long a, long b, long expected) {
        assertThat(calculator.divide(a, b)).isEqualTo(expected);
    }

    @Test
    public void divison_divisorIsZero_throwsArithmaticException() {
        assertThatThrownBy(() -> calculator.divide(10, 0)).isInstanceOf(ArithmeticException.class);
    }
}