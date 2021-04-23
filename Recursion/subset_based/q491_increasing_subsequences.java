package Recursion.subset_based;

import java.util.*;

public class q491_increasing_subsequences {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> path = new ArrayDeque<>();
    // 法1：未优化版（必会）
    public List<List<Integer>> findSubsequences_1(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        // 与子集ii不同：1) 不可利用sort辅助去重 2)去重used数组需定义在dfs内
        dfs(nums, 0, path);
        return res;
    }

    private void dfs(int[] nums, int idx, Deque<Integer> path) {
        // 子集问题：保存所有结点(本题)！[而非only叶子(组合、排列问题)]
        if (path.size() >= 2) // idx == n 错！需要所有子集(路径的中间结果)！
            res.add(new ArrayList<>(path));
            /*  // 1) return; 错！ 后续不可return，还需继续下探!!!
            ❤❤❤ 2) if (idx == n) 不可作为终止条件！
            会漏掉形如: [1,3,6,7,9,4,10,5,6]中的子序列[1,3,6,7,9,10]
            因为idx==n只会保存所有最终以nums[n-1](6)为结尾的递增子序列，
            而无法保留idx < n就已经（被continue）终止的路径(如: [1,3,6,7,9,10])
             */


        // if (idx == nums.length)  return; // 可不写，与for中i < n重复

        // ∵求递增子序列
        // ∴去重方法：1)sort预处理（本题不可取×）2) 哈希
        Set<Integer> usedSet = new HashSet<>(); // 定义在dfs函数内部！
        for (int i = idx; i < nums.length; i++) {
            if ((!path.isEmpty() && path.peekLast() > nums[i]) // 1) 升序
                    || usedSet.contains(nums[i]) ) // 2) 去重 - 同一树层)
                continue;

            usedSet.add(nums[i]); // //不允许不同位置的重复数字！不可写i！
//            System.out.println("1) usedSet.added: " + usedSet);
            path.addLast(nums[i]);
            dfs(nums, i+1, path);
            path.removeLast(); // 回溯到上一层(idx_last)
            // usedSet.remove(nums[i]);// ❤ 不可写pop！下探后used自动清空！
//            System.out.println("2) usedSet - backtrack: " + usedSet);
        }
    }

    // 法2-优化：数组(根据题目定义len)代替哈希HashMap
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        dfs2(nums, 0);
        return res;
    }

    private void dfs2(int[] nums, int idx) {
        if (path.size() >= 2) // 保存所有符合条件(路径长度>1)的中间结点
            res.add(new ArrayList<>(path));

        // 优化：数组代替哈希
        // - 题目中，元素∈[-100, 100] -> 映射下标(+100) ∈[0, 200],共201个
        boolean[] used = new boolean[201];
        for (int i = idx; i < nums.length; i++) {
            if ((!path.isEmpty() && path.peekLast() > nums[i])
                    || used[nums[i] + 100])
                continue;
            used[nums[i] + 100] = true; // 优化：数组代替哈希(每层都会重新定义，不可写false)
            path.addLast(nums[i]);
            dfs2(nums, i+1);
            path.removeLast();
        }

    }

    public static void main(String[] args) {
        q491_increasing_subsequences sol = new q491_increasing_subsequences();
        int[] nums = {4,6,7,7}; // {4,7,6,7}
        List<List<Integer>>  res = sol.findSubsequences(nums);
        System.out.println(res);
    }
}
