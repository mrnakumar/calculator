package com.clarivate.interview.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScientificCalculator {
    private final FactorialCalculator factorialCalculator;
    private final PrimeNumberCalculator primeNumberCalculator;

    @Autowired
    public ScientificCalculator(FactorialCalculator factorialCalculator, PrimeNumberCalculator primeNumberCalculator) {
        this.factorialCalculator = factorialCalculator;
        this.primeNumberCalculator = primeNumberCalculator;
    }

    public long square(long n) {
        return Math.multiplyExact(n, n);
    }

    public long factorial(long n) {
        return factorialCalculator.factorial(n);
    }

    public boolean isPrime(long n) {
        return primeNumberCalculator.isPrime(n);
    }
}
