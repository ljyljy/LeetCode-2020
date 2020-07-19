package Recursion;//You are climbing a stair case. It takes n steps to reach to the top.
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can yo
//u climb to the top?
//
// Note: Given n will be a positive integer.
//
// Example 1:
//
//
//Input: 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//
//
// Example 2:
//
//
//Input: 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
//
// Related Topics 动态规划





/***
// 懵逼的时候：
// 1.暴力？
// 2.基本情况？（复杂问题分解成简单的子问题/简单情况举例）——递推/数学归纳法
//      ——> 找 最近 重复子问题
// 台阶数：路径数
// 1: 1
// 2: 2(1+1; 2)
// 3: f(1)+f(2),即：1阶台阶路径+跨2阶, 2阶台阶路径+跨1阶[1+2; 1+1+1, 2+1]
// 4: f(2)+f(3),即：2阶台阶路径+跨2阶, 3阶台阶路径+跨1阶
// n: f(n-2)+f(n-1) —— Fibonacci O(2^n) （复杂度太高）
//                      || 开数组a[n]-> a[i]=a[i-2]+a[i-1];（保存了中间结果, 空间开销较大）
//                      || 不开数组，只保存最后结果一个值
***/

public class q70_climbing_stairs {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int f1 = 1, f2 = 2, f3 = 3;
        for (int i = 3; i <= n; i++) {
            f3 = f1 + f2; // n: f(n-2)+f(n-1), 但不重复计算，且不开数组保留中间结果
            // 为了下一次循环
            f1 = f2;
            f2 = f3;// 倒着看
        }
        return f3;
    }
}
