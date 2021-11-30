package Other.math;

public class q400_findNthDigit {
    // 1 位数的范围是 1 到 9，共有 9 个数，所有 1 位数的位数之和是 1 * 9 = 9。
    // 2 位数的取值范围是 10 到 99，共有 90 个数，所有 2 位数的位数之和是 2 * 90 = 180。
    // 3 位数的取值范围是 100 到 999，共有 900 个数，所有 3 位数的位数之和是 3 * 900 = 2700。
    // 如：n=9+180+2700+8 -> 1000, 1001(即：最后的'1')
    public int findNthDigit(int n) {
        long base = 1, digitCnt = 1;
        while (n > base * digitCnt * 9) {
            n -= base * digitCnt * 9;
            base *= 10;
            digitCnt++; // x位数
        }
        int idx = n-1;
        return getKthDigit(base + idx/digitCnt, idx % digitCnt);
    }

    private int getKthDigit(long num, long k) {
        return String.valueOf(num).charAt((int)k) - '0';
    }
}
