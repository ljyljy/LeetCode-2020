package Recursion.permutation_based;//package permutation_based;

import java.util.*;


public class q46_permutation {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        // ∵全排列 ∴(1) {1,2}、{2,1}是不同的排列 -> for中i从0起，而非idx！
        // - (2) ∵i从0起，∴需要used进行路径(树枝)去重！❤
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used);
        return res;
    }

    private void dfs(int[] nums, int idx, boolean[] used) {
        if (path.size() == nums.length) { // 而非idx == n！❤
            res.add(new ArrayList<>(path));
            return; // 不同于子集，组合、排列仅保存[叶子]
        }
        // ∵ {1,2}、{2,1}是不同的排列 -> for中i从0起，而非idx！
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, i+1, used);
            path.removeLast();
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        q46_permutation sol = new q46_permutation();
        List<List<Integer>> res = sol.permute(nums);
        System.out.println(res);
    }
}


// 对比
// https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
class Solution2 {

    public List<List<Integer>> permute(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            // 3、不用拷贝，因为每一层传递下来的 path 变量都是新建的
            res.add(path);
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                // 1、每一次尝试都创建新的变量表示当前的"状态"
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(nums[i]);

                boolean[] newUsed = new boolean[len];
                System.arraycopy(used, 0, newUsed, 0, len);
                newUsed[i] = true;

                dfs(nums, len, depth + 1, newPath, newUsed, res);
                // 2、无需回溯
            }
        }
    }
}