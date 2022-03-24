package Other.math;

import java.util.Arrays;

public class q204_count_primes {
    public int countPrimes(int n) {
        if (n <= 1) return 0; // ÖÊÊý£º2,3...
        boolean[] dp = new boolean[n];
        Arrays.fill(dp, true);

        for (int i = 2; i*i < n; i++) {
            if (dp[i]) {
                for (int j = i*i; j < n; j += i) {
                    dp[j] = false;
                }
            }
        }

        int cnt = 0;
        for (int i = 2; i < n; i++) {
            if (dp[i]) cnt++;
        }
        return cnt;
    }
}
