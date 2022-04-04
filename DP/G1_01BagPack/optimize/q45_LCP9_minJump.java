package DP.G1_01BagPack.optimize;

import java.util.*;

public class q45_LCP9_minJump {
    // 法1：dp
    // 审题：i处只能跳跃到1)左[0, i-1]或2)右i+jump[i]处
    // 【区别于 跳跃游戏Q45、55！】
    public int minJump_dp(int[] jump) {
        int n = jump.length;
        int[] dp = new int[n]; // 至少【再】弹x次，跳出(从后往前)
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[n-1] = 1; // 边界再弹1次，即可跳出
        for (int i = n-2; i >= 0; i--) {
            // 若越界，则再弹一次即可；否则，需要跳1次到i + jump[i]，并再跳dp[i + jump[i]]次
            dp[i] = i + jump[i] >= n? 1: dp[i + jump[i]] + 1;
            // 因为后面可以往前跳，所以往后遍历一次把后面次数过大的替换掉
            for(int j = i + 1; j < n; j++){
                if (dp[j] <= dp[i]) break; // 剪枝，此后应从j跳，不更新
                dp[j] = dp[i] + 1;// Math.min(dp[j], dp[i] + 1);
            }
        }
        return dp[0];
    }


    // 法2：BFS
    public int minJump(int[] jump) {
        int n = jump.length;
        boolean[] used = new boolean[n+1];
        Deque<int[]> queue = new ArrayDeque<>(); // [idx, step]
        queue.offer(new int[]{0, 1});
        used[0] = true;
        int farthestL = 1; //
        while (!queue.isEmpty()) {
//            int size = queue.size();
            // for (int i = 0; i < size; i++) {
            int[] cur = queue.poll();
            int idx = cur[0], step = cur[1];
            int farIdx = idx + jump[idx];
            if (farIdx >= n) return step;
            if (!used[farIdx]) { // 避免成环
                queue.offer(new int[]{farIdx, step+1});
                used[farIdx] = true;
            }
            // 向左扩展：O(n^2)
            // 某一个位置及其之前所有位置都已经被扩展过，那么一定是最短路，不需要再次被扩展
            // farthestL：记录某个位置及其之前位置均已被扩展，每次更新farthestL 即可
            // TLE:  for (int j = 0...)
            for (int j = farthestL; j < idx; j++) {
                if (!used[j]) {
                    queue.offer(new int[]{j, step+1});
                    used[j] = true;
                }
            }
            // }
            farthestL = Math.max(farthestL, idx+1);
        }
        return -1;
    }
}
