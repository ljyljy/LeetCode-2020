package Recursion;

import java.util.Arrays;

public class q1137_nth_tribonacci {
    private int[] memo;
    public int tribonacci(int n) {
        memo = new int[n+1];
        Arrays.fill(memo, -1);
        return fib(n);
    }

    public int fib(int n) {
        if (memo[n] != -1) return memo[n];
        if (n == 0) {
            memo[n] = 0;
            return 0;
        } else if (n == 1 || n == 2) {
            memo[n] = 1;
            return 1;
        }
        memo[n] = fib(n-1) + fib(n-2) + fib(n-3);
        return memo[n];
    }
}
