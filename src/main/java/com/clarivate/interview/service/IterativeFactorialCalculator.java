package com.clarivate.interview.service;

import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class IterativeFactorialCalculator implements FactorialCalculator {
    @Override
    public long factorial(long n) {
        if (n < 0) {
            throw new IllegalArgumentException("can not get factorial of a negative number");
        }
        return LongStream.iterate(n, x -> x - 1).takeWhile(x -> x > 1).reduce(1, Math::multiplyExact);
    }
}
