package Recursion.permutation_based;//package permutation_based;

import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        if(nums == null) return res;
        Arrays.sort(nums);
        dfs(nums, 0, path, res); // dfs_v2(nums, 0, path, res);
        return res;
    }

    // 写法1：
    private void dfs(int[] nums, int idx, Deque<Integer> path, List<List<Integer>> res){
        res.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++){
            path.addLast(nums[i]);
            dfs(nums, i+1, path, res);
            path.removeLast();
        }
    }
    // 写法2：
    private void dfs_v2(int[] nums, int idx, Deque<Integer> path, List<List<Integer>> res){
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.addLast(nums[idx]);
        dfs_v2(nums, idx+1, path, res);
        path.removeLast();
        dfs_v2(nums, idx+1, path, res);
    }
}
public class q78_subsets {
    private List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) return new ArrayList<>();
        backtrack(nums, new ArrayList<>(), 0);
        return res;
    }

    private void backtrack(int[] nums, ArrayList<Integer> path, int idx) {
        if (idx == nums.length) { // 1. 出口
            res.add(new ArrayList<>(path));
//            res.add(path);
            return;
        }
        backtrack(nums, path, idx+1); // not pick num[idx]
        path.add(nums[idx]);
        backtrack(nums, path, idx+1); // pick num[idx]
        // 【回溯reverse】 撤销当前状态
        path.remove(path.size()-1);
    }

    // 1. 递归的定义
    //    以 path 开头的，配上 nums 以 idx 开始的数所有组合放到 res 里
    private void backtrack2(int[] nums, List<Integer> path, int idx) {
        // 3. 递归出口
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 2. 递归的拆解
        // (如何进入下一层)

        // 选了 nums[index]
        path.add(nums[idx]);
        backtrack2(nums, path, idx+1);
        // 不选 nums[index]
        path.remove(path.size()-1);
        backtrack2(nums, path, idx+1);
    }


}


