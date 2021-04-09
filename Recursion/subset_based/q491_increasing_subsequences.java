package Recursion.subset_based;

import java.util.*;

public class q491_increasing_subsequences {
    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        // 与子集ii不同：1) 不可利用sort辅助去重 2)去重used数组需定义在dfs内
        dfs(nums, 0, path);
        return res;
    }

    private void dfs(int[] nums, int idx, Deque<Integer> path) {
        // 子集问题：保存所有结点(本题)！[而非only叶子(组合、排列问题)]
        if (path.size() >= 2)
            res.add(new ArrayList<>(path)); // 后续不可return，还需继续下探
        // if (idx == nums.length)  return; // 可不写，与for中i < n重复

        // ∵求递增子序列
        // ∴去重方法：1)sort预处理（本题不可取×）2) 哈希
        Set<Integer> usedSet = new HashSet<>(); // 定义在dfs函数内部！
        for (int i = idx; i < nums.length; i++) {
            if ((!path.isEmpty() && path.peekLast() > nums[i]) // 1) 升序
                    || usedSet.contains(nums[i]) ) // 2) 去重 - 同一树层)
                continue;

            usedSet.add(nums[i]);
//            System.out.println("1) usedSet.added: " + usedSet);
            path.addLast(nums[i]);
            dfs(nums, i+1, path);
            path.removeLast(); // 回溯到上一层(idx_last)
            // usedSet.remove(nums[i]);// ❤ 不可写pop！下探后used自动清空！
//            System.out.println("2) usedSet - backtrack: " + usedSet);
        }
    }

    public static void main(String[] args) {
        q491_increasing_subsequences sol = new q491_increasing_subsequences();
        int[] nums = {4,6,7,7}; // {4,7,6,7}
        List<List<Integer>>  res = sol.findSubsequences(nums);
        System.out.println(res);
    }
}
