package DP;

import java.util.Arrays;
import java.util.Scanner;

public class q300_HJ24_Chorus_LIS_Reversed {
//    https://blog.nowcoder.net/n/960f798e727e43889dd69959b4214332

/*最长上升子序列问题
中间最高，向两边逐渐减小（相等也不行）。不要求最高的同学左右人数必须相等。
不允许改变队列元素的先后顺序，也就是说，只能剔除不能排序。
计算最少需要出列几名同学满足以上要求，也就是说，剔除某些同学，剩下的队列自然而然的满足要求

（1）计算出每个同学左边最多有几个人满足从左到右依次增大的序列要求（包括自己）。
示例：186 186 150 200 160 130 197 200
      1   1   1   2   2   1   3   4
动态方程：
（2）计算出每个同学右边最多有几个人满足从右到左依次增大的序列要求（包括自己）。
示例：186 186 150 200 160 130 197 200
      3   3   2   3   2   1   1   1
动态方程：
（3）将左右最多序列人数相加减一（自己加了两遍），就得到以该数为中心时，所在队列最多人数。
然后依次算出所有的队列的最多人数，然后与总人数相减，其中最小值就是最少剔除人数。
总人数-该数所在队列人数=需要出队的人数
*/


public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    while (sc.hasNext()) {
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
//             System.out.println(LIS(nums, n));
        int[] LIS_ordered = LIS(nums, n);
        int[] LIS_reversed = LIS2(nums, n);
        System.out.println(calcMinKick(LIS_ordered, LIS_reversed));
    }
}

    private static int[] LIS(int[] nums, int n) {
        int[] dp = new int[n]; // 以【i】结尾，最长LIS
        Arrays.fill(dp, 1);
        //计算各数左侧最长上升序列
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }

    //计算各数右侧最长上升序列
    private static int[] LIS2(int[] nums, int n) {
        int[] dp = new int[n]; // 以【i】结尾，最长LIS
        Arrays.fill(dp, 1);
        for (int i = n-2; i >= 0; i--) {
            for (int j = n-1; j > i; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        return dp;
    }

    private static int calcMinKick(int[] LIS1, int[] LIS2) {
        int n = LIS1.length;
        int minKick = n;

        for (int i = 0; i < n; i++) {
            //计算最少剔除人数 = n - (lmax[i] + rmax[i] - 1)
            int meetCnt = LIS1[i] + LIS2[i] - 1; // 去掉自己重复计算的1次
            minKick = Math.min(minKick, n - meetCnt);
        }
        return minKick;
    }
}

