package Recursion.permutation_based;//package permutation_based;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q90_subsets_ii {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums);
//        dfs(nums, 0, path, res);
//        dfs2(nums, 0, -1, path, res);

        boolean[] used = new boolean[nums.length];
        dfs3(nums, 0, used, path, res);
        return res;
    }

    // 法1【推荐】：
    private void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
            // ↓ 对同层使用过的元素进行跳过（回溯时）
            if (i != idx && nums[i] == nums[i-1])
                continue;  // 出现重复数字

            path.add(nums[i]); // 不是idx！是i！否则无法去重！
            dfs(nums, i+1, path, res);
            path.remove(path.size()-1);
        }
    }
    // 法2：(使用used[]去重 - 【同层】的结点，即【数值重复 && !used[i-1] -- 本层的重复结点与之前遍历的结点是[水平]关系，算作重复】)
    // 补充：同树枝的重复结点是允许(不算重复)的，
    //      如[1,2,2]子集本身，此时：
    //      【数值重复 && used[i-1]为真--本层的重复结点是从设true后下探的(同气连枝,上下级)】
    private void dfs3(int[] nums, int idx, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) // ↓ 算作'重复'(同层)
                continue;  // !used[i-1] -- 并非“同气连枝”/同一树枝的重复数值

            used[i] = true;
            path.add(nums[i]);

            dfs3(nums, i+1, used, path, res);

            used[i] = false;
            path.remove(path.size()-1);
        }
    }

    // 法1-2：【不推荐】
    private void dfs2(int[] nums, int idx, int lastIdx, List<Integer> path, List<List<Integer>> res) {
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        dfs2(nums, idx + 1, lastIdx, path, res);
        if (idx-1 >= 0 && idx < nums.length
                && idx-1 != lastIdx && nums[idx] == nums[idx-1]) {
            return;
        }

        path.add(nums[idx]);  // 1. pick nums[idx]
        // System.out.println("2. dfs2(idx + 1, idx, path+[nums[idx]]) -- idx=" + idx + ", lastIdx=" + lastIdx);
        dfs2(nums, idx + 1, idx, path, res);
        // System.out.println("3. dfs2(idx + 1, idx, path-[nums[idx]]) -- idx=" + idx + ", lastIdx=" + lastIdx);
        path.remove(path.size()-1);  // 2. not pick
    }

}


