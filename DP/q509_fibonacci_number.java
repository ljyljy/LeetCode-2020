package DP;

import java.util.Arrays;

public class q509_fibonacci_number {
    public int fib(int n) {
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);
        memo[0] = 0;
        if (n >= 1) memo[1] = 1;
        return dfs(n, memo);
    }

    private int dfs(int n, int[] memo) {
        if (memo[n] != -1) return memo[n];
        int ans = dfs(n-1, memo) + dfs(n-2, memo);
        memo[n] = ans;
        return ans;
    }
}
