package Recursion.subset_based;

// 给你一串数组，找最接近target的数字组合

import java.util.*;

public class q1755_closest_subsequence_sum {
    // https://leetcode.cn/problems/closest-subsequence-sum/solution/by-mountain-ocean-1s0v/

    // 法2：二分 + 双向DFS - yxc
//    目标值不明确(要求minDiff，而非确切的sum)，背包容量不知，不能用背包，只能枚举子集的和
//    https://www.acwing.com/video/2356/
    private final int N = (int)1e7 + 10; // 双向DFS爆搜，最多N=2^(40/2)=10^6个结果（数组长度1~40）
    private int[] sumMap_L;
    private int n, res, curCnt, target;
    private int start_R, end_R;
    public int minAbsDifference(int[] nums, int target) {
        sumMap_L = new int[N]; // 双向DFS爆搜，最多N=2^(40/2)个结果（数组长度1~40）
        n = nums.length; res = Integer.MAX_VALUE; curCnt = 0; this.target = target;
        start_R = n / 2; end_R = n;

        dfs_L(nums, 0, 0); // 爆搜左边，计算sumMap_L, idx∈[0,start_R)
        Arrays.sort(sumMap_L, 0, curCnt); // 【只对[0, curCnt)排序【左闭右开】，而非整体[0,n) ！】
        for (int i = 0; i < curCnt; i++) {
            System.out.print(sumMap_L[i] + " ");
        }
        System.out.println();
        dfs_R(nums, start_R, 0);
        return res;
    }

    private void dfs_L(int[] nums, int idx_L, int sum_L) {
        if (idx_L == start_R) { // idx_L ∈ [0, (n+1)/2]
            sumMap_L[curCnt++] = sum_L; // 记录枚举的所有∑nums_L[0,..,start_R)可能结果, 【左闭右开】！
            return; // 勿忘！否则死循环！
        }
        dfs_L(nums, idx_L + 1, sum_L); // 不选第idx_L个元素
        dfs_L(nums, idx_L + 1, sum_L + nums[idx_L]); // 选第idx_L个元素
    }

    private void dfs_R(int[] nums, int idx_R, int sum_R) {
        if (idx_R == end_R) { // 固定sum_R, 二分搜索合适的sumMap_L, 求minDiff
            int start_L = 0, end_L = curCnt-1; // curCnt是开区间！sumMap_L有效下标∈[0,curCnt-1]
            // ∵ 需要start、end都做计算，选minDiff∴ ↓使用九章模板！
            while (start_L + 1 < end_L) { // 非[start, mid-1] [mid, end]！
                int mid_L = start_L + end_L >> 1;
                if (sumMap_L[mid_L] + sum_R <= target) {
                    start_L = mid_L;
                } else end_L = mid_L;
            }
            int curDiff1 = Math.abs(sumMap_L[start_L] + sum_R - target);
            res = Math.min(res, curDiff1);
            int curDiff2 = Math.abs(sumMap_L[end_L] + sum_R - target);
            res = Math.min(res, curDiff2);
            return;// 勿忘！否则【死循环！】

            // 法2
//            while (start_L < end_L) { // 注意！[start, mid] [mid+1, end]！
//                int mid_L = start_L + end_L + 1 >> 1;
//                if (sumMap_L[mid_L] + sum_R <= target) {
//                    start_L = mid_L;
//                } else end_L = mid_L - 1;
//            }
//            int curDiff1 = Math.abs(sumMap_L[end_L] + sum_R - target);
//            res = Math.min(res, curDiff1);
//            if (end_L + 1 < curCnt) {
//                int curDiff2 = Math.abs(sumMap_L[end_L+1] + sum_R - target);
//                res = Math.min(res, curDiff2);
//            }
//            return;
        }
        dfs_R(nums, idx_R+1, sum_R);
        dfs_R(nums, idx_R+1, sum_R + nums[idx_R]);
    }


    // 法1-dfs爆搜 (扩展：求出所有符合条件的子序列)：
    //      枚举所有子集（子序列），找minAbs -- TLE,  最大的时间复杂度 2^40
    int len, minDiff = Integer.MAX_VALUE;
    Set<List<Integer>> resSet = new HashSet<>();
    List<List<Integer>> subSets = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public Set<List<Integer>> minAbsDifference_ListRes(int[] nums, int target) {
        Arrays.sort(nums);
        len = nums.length;
        boolean[] used = new boolean[len];
        dfs(nums, target, 0, path, used);
        for (List<Integer> path: subSets) {
//            System.out.println("path: " + path);
            int curSum = 0;
            for (int num: path) {
                curSum += num;
            }
            int curDiff = Math.abs(curSum - target);
            if (curDiff < minDiff) {
                minDiff = curDiff;
                resSet.clear();
                resSet.add(path);
            } else if (curDiff == minDiff) {
                resSet.add(path);
            }
        }
        System.out.println("minDiff = " + minDiff);
        return resSet;
    }

    private void dfs(int[] nums, int target, int idx, Deque<Integer> path, boolean[] used) {
        subSets.add(new ArrayList<>(path));
        if (idx == len) return;

        for (int i = idx; i < len; i++) {
            if (used[i]) continue;
            if (i-1>=0 && nums[i] == nums[i-1] && !used[i-1])  continue;
            path.addLast(nums[idx]);
            used[i] = true;
            dfs(nums, target, i+1, path, used);
            used[i] = false;
            path.removeLast();
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1,2,3,4,5,  6,7,8,9,10}; // sum_L=sum{1,2,3,4,5}的组合总和；sum_R = {6,..,10}的组合总和
//        int target = 13;
        int[] nums = {5,-7,3,5}; // sum_L=sum{1,2,3,4,5}的组合总和；sum_R = {6,..,10}的组合总和
        int target = 6;
        q1755_closest_subsequence_sum sol = new q1755_closest_subsequence_sum();
        int res = sol.minAbsDifference(nums, target);
        System.out.println(res);

        System.out.println("List all res:");
        Set<List<Integer>> resSet = sol.minAbsDifference_ListRes(nums, target);
        for (List<Integer> path: resSet) {
            System.out.println(path);
        }
    }
}
