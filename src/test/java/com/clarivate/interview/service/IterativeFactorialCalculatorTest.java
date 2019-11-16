package com.clarivate.interview.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class IterativeFactorialCalculatorTest {
    private final IterativeFactorialCalculator calculator = new IterativeFactorialCalculator();

    @Test
    @Parameters({
            "0,1",
            "1,1",
            "2,2",
            "3,6",
            "4,24"
    })
    public void factorial_validInput_factorialOfGivenNumber(long n, long expected) {
        assertThat(calculator.factorial(n)).isEqualTo(expected);
    }

    @Test
    public void factorial_negativeNumber_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> calculator.factorial(-1)).isInstanceOf(IllegalArgumentException.class);
    }
}