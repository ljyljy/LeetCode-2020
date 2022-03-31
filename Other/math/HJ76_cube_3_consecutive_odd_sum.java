package Other.math;

import java.util.Scanner;

/**
 * 验证尼科彻斯定理，即：任何一个整数m的立方都可以写成m个连续奇数之和。
 *
 * 例如：
 *
 * 1^3=1
 *
 * 2^3=3+5
 *
 * 3^3=7+9+11
 *
 * 4^3=13+15+17+19
 *
 * 输入一个正整数m（m≤100），将m的立方写成m个连续奇数之和的形式输出。
 * 进阶：时间复杂度：O(m) ，空间复杂度：O(1)
 *
 * 输入：
 * 6
 * 输出：
 * 31+33+35+37+39+41
 */
public class HJ76_cube_3_consecutive_odd_sum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();
            decompose(num);
        }
        sc.close();
    }

    private static void decompose(int n) {
        int[] ans = new int[n];
        int mid = n / 2;
        if (n % 2 == 0) {
            ans[mid] = n * n + 1;
        } else ans[mid] = n * n;

        for (int i = mid; i >= 1; i--) {
            ans[i - 1] = ans[i] - 2;
        }

        for (int i = mid; i + 1 < n; i++) {
            ans[i + 1] = ans[i] + 2;
        }
        StringBuilder sb = new StringBuilder();
        for (int num: ans) sb.append(num + "+");
        sb.deleteCharAt(sb.length()-1);
        System.out.println(sb);
    }
}
