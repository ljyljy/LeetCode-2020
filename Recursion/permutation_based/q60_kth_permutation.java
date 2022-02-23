package Recursion.permutation_based;

import java.util.ArrayList;
import java.util.List;

public class q60_kth_permutation {
    // ∑®1£∫dfs+ºÙ÷¶
    String res;
    List<String> resList = new ArrayList<>();

    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n];
        dfs(0, n, k, "", used);
        return resList.get(k-1);
    }

    private void dfs(int idx, int n, int k, String path, boolean[] used) {
        if (idx > n || k < 0 || resList.size() == k) return;
        if (path.length() == n) {
            resList.add(new String(path));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (used[i]) continue;
            used[i] = true;
            dfs(i+1, n, k, path+String.valueOf(i+1), used);
            used[i] = false;
        }
    }
}
