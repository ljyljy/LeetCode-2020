package Recursion.combination_based;

import java.util.*;

public class q9_90_k_sum_ii {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        Arrays.sort(A);
        dfs(A, k, target, 0);
        return res;
    }
    private void dfs(int[] A, int k, int target, int idx) {
        if (k < 0 || target < 0) return;
        if (k == 0 && target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = idx; i < A.length; i++) {
            path.addLast(A[i]);
            dfs(A, k-1, target - A[i], i + 1);
            path.removeLast();
        }
    }
}
