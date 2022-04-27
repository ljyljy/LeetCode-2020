package Other.math;

import java.util.Arrays;

public class q172_factorial_trailing_zeroes {
    // 计算n的质因数5的个数
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }

    // 依然溢出！
    public int trailingZeroes_WA(int n) {
        if (n == 0) return 0;
        long[] memo = new long[n+1]; // 存放[1,..., n]
        Arrays.fill(memo, -1);

        long res = dfs(n, memo);
        StringBuilder sb = new StringBuilder(res + "");
        int cnt = 0;
        // System.out.println("res = " + sb);
        while (sb.length() > 0 && sb.charAt(sb.length()-1) == '0') {
            cnt++;
            sb.deleteCharAt(sb.length()-1);
        }
        return cnt;
    }

    private long dfs(int n, long[] memo) {
        if (n >= memo.length || n < 0) return 1;
        if (n == 1) return 1;
        if (memo[n] != -1) return memo[n];

        long res = n * dfs(n-1, memo);
        memo[n] = res;
        // System.out.println(n + "_" + res);
        return res;
    }
}

// TLE↓
//import java.math.BigInteger;
//
//class Solution {
//    public int trailingZeroes(int n) {
//        BigInteger num = BigInteger.ONE;// 坑：必须是BigInteger！int/long会溢出！
//        int cnt = 0;
//
//        for (int i = 2; i <= n; i++) {
//            num = num.multiply(BigInteger.valueOf(i));
//        }
//
//        while (num.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
//            num = num.divide(BigInteger.TEN);
//            cnt++;
//        }
//        return cnt;
//
//        // TLE!!! - 字符串处理×
//        // String str = String.valueOf(num);
//        // int len = str.length();
//        // int cnt = 0, i = len - 1;
//        // // System.out.println(str);
//        // while (i >= 0 && str.charAt(i--) == '0') {
//        //     cnt++;
//        // }
//        // return cnt;
//    }
//
//}