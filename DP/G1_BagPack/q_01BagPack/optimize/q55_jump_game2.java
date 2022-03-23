package DP.G1_BagPack.q_01BagPack.optimize;

import java.util.Arrays;
import java.util.Scanner;

public class q55_jump_game2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = sc.nextInt();
            }
            int maxJump = sc.nextInt();
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[0] = 0;

            for (int i = 0; i < n; i++){
                for (int j = 0; j < i; j++) {
                    if (j + nums[j] >= i)
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
//            System.out.println(dp[n-1]);
            System.out.println(dp[n-1] > maxJump? -1: dp[n-1]);
        }
    }
}
