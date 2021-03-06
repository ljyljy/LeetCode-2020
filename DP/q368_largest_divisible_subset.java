package DP;

import java.util.*;

public class q368_largest_divisible_subset {
    // 法2: 动归
    public List<Integer> largestDivisibleSubset_DP(int[] nums) {
        Arrays.sort(nums); // 升序
        int n = nums.length;
        int[] dp = new int[n]; // 以i结尾的最长整除子集の长度
        int[] prevs = new int[n]; // 回溯路径用
        for (int i = 0; i < n; i++) {
            int len = 1, prev = i; // 最长长度、前继初始化（=自身i）
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    // dp[i] = Math.max(dp[i], dp[j] + 1) = len; 类似q300、q132
                    if (len < dp[j] + 1) {
                        len = dp[j] + 1;
                        prev = j;
                    }
                }
            }
            dp[i] = len; // 记录「最终长度」
            prevs[i] = prev; // 「从何转移而来」 -- i 或 j
        }

        // 遍历 dp，取得「最大长度dp[i]」和「对应下标i-路径res的终点！」
        int maxLen = -1, idx = -1;
        for (int i = 0; i < n; i++) {
            if (maxLen < dp[i]) {
                maxLen = dp[i];
                idx = i;
            }
        }

        // 回溯path
        List<Integer> res = new ArrayList<>();
        while (res.size() != maxLen) {
            res.add(0, nums[idx]); // 头插！从终点(idx0)向前(prev)回溯
            idx = prevs[idx];// 回溯到prev
        }
        return res;
    }

    // 法1：回溯 - TLE, nums[]长度为 n^3，太多！dfs不可取！
    List<Integer> LDSlist = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<Integer> largestDivisibleSubset_TLE(int[] nums) {
        Arrays.sort(nums); // 升序
        dfs(nums, 0);
        return LDSlist;
    }

    private void dfs(int[] nums, int idx) {
//        if (LDSlist.size() < path.size())    // path回溯下来需要先特判整除！
//            LDSlist = new ArrayList<>(path); // 满足要求才可能替换LSDlist！

//        if (idx == nums.length) return; // 可不写，与for中i<n重复
        for (int i = idx; i < nums.length; i++) {
            if (!check(path, nums[i]))  continue; // vs 子集ii(需特判 整除关系)

            path.addLast(nums[i]); // 子集问题，保存所有中间结点！
            if (LDSlist.size() < path.size()) // 直接覆盖为当前最长path
                LDSlist = new ArrayList<>(path); // 位于path.addLast之后！
            dfs(nums, i+1);
            path.removeLast();
        }
    }

    private boolean check(Deque<Integer> path, int newNum) {
        if (path.isEmpty()) return true;
        for (Integer oldNum: path) { // [所有]old都必须能被new整除
            if (newNum % oldNum != 0) // 如： 8 % (4/2/1) == 0
                return false;
        }
        return true;
    }
}
