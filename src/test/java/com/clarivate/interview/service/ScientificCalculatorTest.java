package com.clarivate.interview.service;


import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class ScientificCalculatorTest {
    private final ScientificCalculator calculator = new ScientificCalculator(null, null);

    @Test
    @Parameters({
            "-2,4",
            "-1,1",
            "0,0",
            "1,1",
            "2,4",
            "3,9",
            "4,16"
    })
    public void square_noOverflow_returnsSquareOfGivenNumber(long n, long expected) {
        assertThat(calculator.square(n)).isEqualTo(expected);
    }

    @Test
    public void square_overflow_throwsArithmeticException() {
        assertThatThrownBy(() -> calculator.square(Long.MAX_VALUE)).isInstanceOf(ArithmeticException.class);
    }
}