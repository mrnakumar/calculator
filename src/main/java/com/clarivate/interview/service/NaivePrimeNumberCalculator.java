package com.clarivate.interview.service;

import org.springframework.stereotype.Service;

import java.util.stream.LongStream;

@Service
public class NaivePrimeNumberCalculator implements PrimeNumberCalculator {
    @Override
    public boolean isPrime(long n) {
        if (n < 2) {
            throw new IllegalArgumentException("N should be greater than 1");
        }
        return LongStream.iterate(2, divisor -> divisor + 1)
                .takeWhile(divisor -> divisor * divisor <= n)
                .mapToObj(divisor -> n % divisor == 0)
                .map(multiple -> !multiple)
                .filter(__ -> !__)
                .findAny()
                .orElse(true);
    }
}
