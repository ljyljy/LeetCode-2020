package Binary_Search.bin_Answer;

public class q29_divide_two_integers {
    // 3*(2^1) <  10 < 3*(2^2) -- 倍增(cnt、divisor)/快速乘
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;
    public int divide(int dividend, int divisor) {
        // 溢出情况【负溢->正MAX】（|MIN|=|MAX|+1）
        if (dividend == MIN && divisor == -1) return MAX;
        // 记录结果的符号
        int sign = 1;
        if (dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0) {
            sign = -1;
        }
        // 全部转换成负数，防止溢出
        dividend = dividend > 0? -dividend:dividend;
        divisor = divisor > 0? -divisor:divisor;

        int ans = div(dividend, divisor);
        return sign > 0? ans: -ans;
    }

    // 倍乘法，注意都是负数了，比较的时候与正数相反！(|divided| >= |divisor|)
    // 简单点理解，就是每次减去除数的2^x.倍数(tmp == divisor*(2^x) = divisor*cnt)，剩下的部分继续按这样的规则继续
    private int div(int dividend, int divisor) {
        int ans = 0;
        while (dividend <= divisor) {
            int tmp = divisor, cnt = 1;
            // 这里注意不要写成 tmp + tmp >= dividend，这样写加法有可能会溢出导致死循环
            while (tmp >= dividend - tmp) { // 正数tmp + tmp <= dividend，颠倒 & 防溢
                // tmp 和 cnt 每次对应增加一倍（*2），所以叫倍增
                cnt <<= 1; // 即 2^x
                tmp <<= 1; // 即
            }
            // 被除数减去除数的 2^x 倍数做为新的被除数
            dividend -= tmp;
            ans += cnt;
        }
        return ans;
    }
}
