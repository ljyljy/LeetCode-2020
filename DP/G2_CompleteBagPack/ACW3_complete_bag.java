package DP.G2_CompleteBagPack;

import java.util.Arrays;
import java.util.Scanner;

public class ACW3_complete_bag {
    // 一维dp-完全背包：
    public int completeBag(int N, int bagsize, int[] V, int[] P) {
        int[] dp = new int[bagsize + 1];
        dp[0]=0; // 最值/最优化-背包体积=0时，最大价值=0
        for (int i = 0; i < N; i++) { // 外-遍历物品
            for (int j = V[i]; j <= bagsize; j++) { // 内-遍历背包(正序)
                dp[j] = Math.max(dp[j], dp[j-V[i]] + P[i]);
            } // 内层for：V应该是有序的，否则j无法实现逐步递增
        }
        return dp[bagsize];
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), bagsize = sc.nextInt();
        int[] V = new int[N];
        int[] P = new int[N];
        for (int i = 0; i < N; i++) {
            V[i] = sc.nextInt();
            P[i] = sc.nextInt();
        }
        ACW3_complete_bag sol = new ACW3_complete_bag();
        Arrays.sort(V); // 题目多半默认V是升序，无需写
        int maxP = sol.completeBag(N, bagsize, V, P);
        System.out.println(maxP);
    }
}
