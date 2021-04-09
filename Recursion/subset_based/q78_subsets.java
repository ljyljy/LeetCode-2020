package Recursion.subset_based;//package permutation_based;

import java.util.*;

// 二刷写法1 —— 推荐（子集 - 模板）
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();

    public List<List<Integer>> subsets(int[] nums) {
        if(nums == null || nums.length == 0) return res;
        Arrays.sort(nums); // 子集、全排列の预处理，加速
        dfs(nums, 0, path); // dfs_v2(nums, 0, path, res);
        return res;
    }

    // 写法1（推荐：子集模板vs全排列、组合问题）：
    private void dfs(int[] nums, int idx, Deque<Integer> path){
        // ↓子集问题：收集【所有结点】(vs全排列、组合、分割：只收集叶子)
        res.add(new ArrayList<>(path));
        if (idx == nums.length) return; // 1)可不写，与for的i<n呼应 /重复
        // ↑ 2)写在res.add以后，否则叶子无法加入res
        for (int i = idx; i < nums.length; i++){
            path.addLast(nums[i]);
            dfs(nums, i+1, path);
            path.removeLast();
        }
    }

    // 写法2（不推荐）：
    private void dfs_v2(int[] nums, int idx, Deque<Integer> path){
        if (idx == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        path.addLast(nums[idx]);
        dfs_v2(nums, idx+1, path);
        path.removeLast();
        dfs_v2(nums, idx+1, path);
    }
}

// 不推荐
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


