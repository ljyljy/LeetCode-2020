package Divide_Conquer;

public class q50_powx_n {
    /**
     * // 1. 暴力, O(n)
     * res = 1
     * for i: 0 ~ n {
     *     res *= x
     * }
     */

    // 2. 分治【快速幂、递归】
    // x^n = [x^(n/2)]^2(n为偶数)；n为奇数则再乘一个x
    // 子问题：
    //      pow(x, n/2) —— 时间O(logN)
    //  merge:
    //      if n奇数：res = subres * subres * x;
    //      else 偶数：res = subres * subres
    //  【注意：n为负数时，x取倒数：x=1/x，n取正数: n = -n】


    // 3. 牛顿迭代法
    //     （略）
    public double myPow(double x, int n) {
        // Java 代码中 int32 变量 n∈[−2147483648,2147483647]
        // 因此当 n=−2147483648 时，执行【n=-n会因越界】而赋值出错。
        // 解决方法是先将 n 存入 long 变量 N ，后面用 N 操作即可。
        long N = n; // 【一定要将int转为long！】
        if (N == 0) return 1.0;
        if (N < 0) { //n为负数时
            x = 1/x; // x取倒数
            N = -N; // n取正数:
        }
        return fastPow(x, N);

    }

    // 分治1：
    private double fastPow(double x, long N) {
        if (N == 0) return 1.0;
        double subAns = fastPow(x, N/2);
        subAns *= subAns;
        if (N % 2 == 1) // 奇数
            subAns *= x;
        return subAns;
    }
    // 法2： 非递归
    private double fastPow1(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x;
            }
            // 将贡献不断地平方
            x *= x;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N >>= 1; // N /= 2;
        }
        return ans;
    }
}
