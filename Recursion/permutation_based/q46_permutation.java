package Recursion.permutation_based;//package permutation_based;

import java.util.*;


public class q46_permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (nums == null) return res;
        boolean[] used = new boolean[nums.length];

        dfs(nums, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int idx, boolean[] used, Deque<Integer> path, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;

            System.out.println("  递归之前 => " + path);
            used[i] = true;
            path.addLast(nums[i]);

            dfs(nums, idx+1, used, path, res);
            used[i] = false;
            path.removeLast();
            System.out.println("  递归之后 => " + path);
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