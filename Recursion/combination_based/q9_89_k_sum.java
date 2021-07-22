package Recursion.combination_based;

import java.util.*;

public class q9_89_k_sum {
    // 动归
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        int[][][] dp = new int[n+1][target+1][k+1];
        // return res.size();
        return cnt;
    }


    // // [错X] 法1：DFS - TLE
    // List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    int cnt = 0;
    public int kSum_TLE(int[] A, int k, int target) {
        if (A == null || A.length == 0) return 0;
        dfs0(0, A, k, target, path);
        // return res.size();
        return cnt;
    }

    private void dfs0(int idx, int[] A, int k, int target,
                      Deque<Integer> path) {

        if (path.size() == k) {
            if (target == 0) {
                // res.add(new ArrayList<>(path));
                cnt++;
            }
            return;
        }

        for (int i = idx; i < A.length; i++) {
            path.addLast(A[i]);
            dfs0(i+1, A, k, target - A[i], path);
            path.removeLast();
        }
    }


    // [错X]法2：DFS + memo - WA
    // int cnt = 0;
    Map<String, Integer> memo = new HashMap<>();
    public int kSum_memo_WA(int[] A, int k, int target) {
        if (A == null || A.length == 0) return 0;
        int ans = dfs(0, A, k, target, path, memo);
        // return res.size();
        return ans;
    }

    private int dfs(int idx, int[] A, int k, int target,
                    Deque<Integer> path, Map<String, Integer> memo) {

        String key = idx + "_" + target;
        if (memo.containsKey(key)) return memo.get(key);

        if (path.size() == k) {
            // if (k == 0) {
            if (target == 0) {
                // res.add(new ArrayList<>(path));
                // cnt++;
                return 1;
            } else {
                memo.put(key, 0);
                return 0;
            }
        }

        int ans = 0;
        for (int i = idx; i < A.length; i++) {
            path.addLast(A[i]);
            ans += dfs(i+1, A, k, target - A[i], path, memo);
            path.removeLast();
        }

        memo.put(key, ans);
        return ans;
    }
}
