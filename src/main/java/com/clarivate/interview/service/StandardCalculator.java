package com.clarivate.interview.service;

import org.springframework.stereotype.Service;

@Service
public class StandardCalculator {
    public long add(long a, long b) {
        return Math.addExact(a, b);
    }

    public long subtract(long a, long b) {
        return Math.subtractExact(a, b);
    }

    public long multiply(long a, long b) {
        return Math.multiplyExact(a, b);
    }

    public long divide(long dividend, long divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("divide by zero not allowed");
        }
        return dividend / divisor;
    }
}
