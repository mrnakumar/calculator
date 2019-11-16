package com.clarivate.interview.service;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnitParamsRunner.class)
public class NaivePrimeNumberCalculatorTest {
    private final PrimeNumberCalculator calculator = new NaivePrimeNumberCalculator();

    @Test
    @Parameters({
            "2,true",
            "3,true",
            "4,false",
            "11, true",
    })
    public void isPrime_inputGreaterThanOne_returnsWhetherGivenNumberIsPrime(long n, boolean expected) {
        assertThat(calculator.isPrime(n)).isEqualTo(expected);
    }

    @Test
    public void isPrime_inputLessThanTwo_throwsIllegalArgumentException() {
        assertThatThrownBy(() -> calculator.isPrime(1)).isInstanceOf(IllegalArgumentException.class);
    }
}