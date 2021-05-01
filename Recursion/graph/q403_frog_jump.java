package Recursion.graph;

import java.util.*;

public class q403_frog_jump {
    // 法1：DFS暴搜 - TLE  时、空O(3^n)
    Map<Integer, Integer> map1 = new HashMap<>(); //<idx, No.i>, i∈[0,n-1]
    public boolean canCross_dfs1_TLE(int[] stones) { // stones[i]存储石子idx，i为石子编号❤
        int n = stones.length;
        // 将石子信息存入哈希表
        // 为了快速判断是否存在某块石子，以及快速查找某块石子所在下标
        for (int i = 0; i < n; i++) {
            map1.put(stones[i], i); // 石子<下标idx，编号No.i>（共n块）
        }
        // check first step: (或 if stones[1] != 1)
        // 根据题意，第一步是固定经过步长 1 到达第一块石子（下标为 1）
        if (!map1.containsKey(1)) return false;
        // 第0块石头，下一跳必须只能跳1步， 到达第1块石头（No.1）
        // second step:  第二块石头(idx_No=1,从0起), 上一跳步数k=1(from No.0)
        return dfs(stones, n, 1, 1);
    }

    private boolean dfs(int[] stones, int n, int i_No, int k) {
        if (i_No == n-1) return true; // 成功跳至最后一个石子(第n块石子)
        // 横向遍历：每次可跳[k-1,k+1]步
        for (int i = -1; i <= 1; i++) {
            int jumpCnt = k + i; // 下一步的跳数
            int next_idx = stones[i_No] + jumpCnt; // 下一步的石头整体下标

            if (jumpCnt == 0) continue;// 原地踏步，跳过
            // 如果存在下一步的石子，则跳转到下一步石子，并 DFS 下去
            if (map1.containsKey(next_idx)) {  // ↓ 下探的不是石子下标! 而是石子编号！！
                boolean flag = dfs(stones, n, map1.get(next_idx), jumpCnt);
                if (flag) return true;
            }

        }
        return false;
    }

    // 法2【荐】：DFS + memo 记忆化 -- 时、空 O(n^2)
    Map<Integer, Integer> map = new HashMap<>(); //<idx, No.i>, i∈[0,n-1]
    Map<String, Boolean> memo = new HashMap<>(); // <"idxNo_k", T/F>
    public boolean canCross_dfs2(int[] stones) { // stones[i]存储石子idx，i为石子编号❤
        int n = stones.length;
        for (int i = 0; i < n; i++)
            map.put(stones[i], i);
        if (!map.containsKey(1)) return false;
        return dfs2(stones, n, 1, 1);
    }

    private boolean dfs2(int[] stones, int n, int i_No, int k) {
        String key = i_No + "_" + k;
        if (memo.containsKey(key)) return memo.get(key);
        if (i_No == n-1) return true;
        for (int i = -1; i <= 1; i++) {
            int jumpCnt = k + i;
            int next_idx = stones[i_No] + jumpCnt;
            if (jumpCnt == 0) continue; // 原地踏步，跳过
            if (map.containsKey(next_idx)) {
                boolean flag = dfs2(stones, n, map.get(next_idx), jumpCnt);
                memo.put(key, flag); // 不可写在下句if内！
                if (flag) return true; // 或 memo.get(key)
            }
        }
        memo.put(key, false);
        return false; // 或 memo.get(key)
    }

    // 法3：dp动归
    public boolean canCross_dp(int[] stones) { // stones[i]存储石子idx，i为石子编号❤
        int n = stones.length;
        // check first step:
        if (stones[1] != 1) return false;
        boolean[][] dp = new boolean[n+1][n+1]; // dp[No.i][k]
        dp[1][1] = true;
        for (int cur = 2; cur < n; cur++) {
            for (int prev = 1; prev < cur; prev++) {
                // 从位prev -> cur: 跳跃步长k
                int k = stones[cur] - stones[prev];
                // ∵每次跳跃，idx至少增加 1，而步长k'最多增加 1 (k'<=k+1)
                // ∴从位置 prev(0/1/2...) 发起的跳跃,步长k ≤ prev + 1(1/2/3...)
                if (k <= prev + 1) { // ❤
                    dp[cur][k] = dp[prev][k-1] || dp[prev][k] || dp[prev][k+1];
                    // 上一跳dp[prev][k'], 则下一跳dp[cur][k]=k'+1 || k' || k'-1
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (dp[n-1][i]) return true; // any(dp[n-1][k])为真，存在可行路径
        }
        return false;
    }


}
