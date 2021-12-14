package Recursion.permutation_based;

import java.util.*;

public class q47_permutations_ii {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        // 同一树层去重(∵去重判断：根据相邻元素 ∴预处理：sorted)
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used);
        return res;
    }

    private void dfs(int[] nums, int idx, boolean[] used) {
        if (path.size() == nums.length) { // 叶子【判断指标不是idx！】
            res.add(new ArrayList<>(path)); // ×: idx==n时，不一定是叶子
            return; // 保存仅【叶子】（不同于[子集]-保存[所有结点]）
        }
        // 全排列 i从0起[如:{1,2} & {2, 1(❤逆序：又从0起)}]（不同于组合、排列startIdx）
        for (int i = 0; i < nums.length; i++) {
            // 1)同一树枝的同一结点(地址同) - ∵ i从0起, 勿漏！
            if (used[i]) continue;
            // 2)同一树层去重（nums[i-1]遍历结束后回溯-used置false）
            if (i > 0 && nums[i-1] == nums[i] && !used[i-1]) continue;
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, i+1, used);
            used[i] = false;
            path.removeLast();
        }
    }


    // 法2（不推荐，但必须理解）- used数组 & set去重
    public List<List<Integer>> permuteUnique2(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length]; // 去重
        dfs2(nums, 0, used);
        return res;
    }

    private void dfs2(int[] nums, int idx, boolean[] used) {
        if(path.size() == nums.length)
            res.add(new ArrayList<>(path));

        Set<Integer> usedSet = new HashSet<>(); // 定义在dfs内(只控制某结点的下一层)
        for(int i = 0; i < nums.length; i++) {
            if (used[i]) continue;  // 去重1) 同一树枝
            // 去重2)同一树层
            if (usedSet.contains(nums[i])) continue;
            // 或 if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;

            usedSet.add(nums[i]); // 无需对应写remove
            used[i] = true;
            path.addLast(nums[i]);
            dfs2(nums, i+1, used);
            used[i] = false;
            path.removeLast();
        }
    }

    // TEST
    private void dfs_test(int[] nums, int idx, boolean[] used,
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
            dfs_test(nums, i+1, used, path, res);
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
