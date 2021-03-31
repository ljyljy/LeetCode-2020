package Recursion.permutation_based;

import java.util.*;

public class q47_permutations_ii {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if (nums == null) return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int idx, boolean[] used,
                     Deque<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) { // 叶子
            System.out.println("叶子-idx：" + idx +", path:" + path);
            res.add(new ArrayList<>(path));
            return; // （idx深度为num.length时，不一定是叶子！）
        }
        for (int i = 0; i < nums.length; i++) { // 非叶子
            if (used[i]) {
                System.out.println("剪枝(1) -- 已访问used[i] -- i = " + i + ", path:" + path);
                continue;
            } // 已访问过
            // 重复结点（同层）
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1])  {
                System.out.println("剪枝(2) -- 同层、重复 !used[i-1] -- i = " + i + ", path:" + path);
                continue;
            }

            System.out.println("1, dfs前 -- i = " + i + ", path:" + path);
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, i+1, used, path, res);
            used[i] = false; // 回溯
            path.removeLast();
            System.out.println("2, dfs后 -- i = " + i + ", path:" + path);

        }
    }
}

/**
 * 输入
 * [1,1,2]
 * 输出
 * [[1,1,2],[1,2,1],[2,1,1]]
 * 预期结果
 * [[1,1,2],[1,2,1],[2,1,1]]
 *
 *
 * stdout
 * 1, dfs前 -- i = 0, path:[]
 * 剪枝(1) -- 已访问used[i] -- i = 0, path:[1]
 * 1, dfs前 -- i = 1, path:[1]
 * 剪枝(1) -- 已访问used[i] -- i = 0, path:[1, 1]
 * 剪枝(1) -- 已访问used[i] -- i = 1, path:[1, 1]
 * 1, dfs前 -- i = 2, path:[1, 1]
 * 叶子-idx：3, path:[1, 1, 2]
 * 2, dfs后 -- i = 2, path:[1, 1]
 * 2, dfs后 -- i = 1, path:[1]
 * 1, dfs前 -- i = 2, path:[1]
 * 剪枝(1) -- 已访问used[i] -- i = 0, path:[1, 2]
 * 1, dfs前 -- i = 1, path:[1, 2]
 * 叶子-idx：2, path:[1, 2, 1]
 * 2, dfs后 -- i = 1, path:[1, 2]
 * 剪枝(1) -- 已访问used[i] -- i = 2, path:[1, 2]
 * 2, dfs后 -- i = 2, path:[1]
 * 2, dfs后 -- i = 0, path:[]
 * 剪枝(2) -- 同层、重复 !used[i-1] -- i = 1, path:[]
 * 1, dfs前 -- i = 2, path:[]
 * 1, dfs前 -- i = 0, path:[2]
 * 剪枝(1) -- 已访问used[i] -- i = 0, path:[2, 1]
 * 1, dfs前 -- i = 1, path:[2, 1]
 * 叶子-idx：2, path:[2, 1, 1]
 * 2, dfs后 -- i = 1, path:[2, 1]
 * 剪枝(1) -- 已访问used[i] -- i = 2, path:[2, 1]
 * 2, dfs后 -- i = 0, path:[2]
 * 剪枝(2) -- 同层、重复 !used[i-1] -- i = 1, path:[2]
 * 剪枝(1) -- 已访问used[i] -- i = 2, path:[2]
 * 2, dfs后 -- i = 2, path:[]
 */
