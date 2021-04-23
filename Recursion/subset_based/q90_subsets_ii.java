package Recursion.subset_based;//package permutation_based;

import java.util.*;

// 推荐写法1（即dfs3）
class Solution_q90 {
    List<List<Integer>> res = new ArrayList<>();
    Deque<Integer> path = new ArrayDeque<>();
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums); // 去重前提（配合i!=idx / !used[i-1]）
        boolean[] used = new boolean[nums.length];
        dfs(nums, 0, used, path);
        return res;
    }

    private void dfs(int[] nums, int idx, boolean[] used, Deque<Integer> path) {
        res.add(new ArrayList<>(path));
        if (idx == nums.length) return; // 可不写(与for中 i<n重复)
        for (int i = idx; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1] && !used[i-1])
                continue; // 同一树层-去重(前提: sorted)
            used[i] = true;
            path.addLast(nums[i]);
            dfs(nums, i+1, used, path);
            used[i] = false;
            path.removeLast();
        }
    }

    // 法4 Set去重:1) 有序 2) dfs内部(某一节点的下一层)，而非类成员变量(控制整个树)！
    public List<List<Integer>> subsetsWithDup4(int[] nums) {
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums); // 去重前提(∵set内部{1,2}与{2,1}判定重复)
        dfs4(nums, 0);
        return res;
    }

    private void dfs4(int[] nums, int idx) {
        res.add(new ArrayList<>(path));
        Set<Integer> usedSet = new HashSet<>(); // dfs内部(控制某节点的下一层--for循环の上层)
        for (int i = idx; i < nums.length; i++) { // 遍历一个树层❤
            if (usedSet.contains(nums[i]))
                continue; // 同一树层(由uset位置决定)-去重!
            usedSet.add(nums[i]); // ∵定义在dfs内部 ∴只控制某一树层 ∴无需对称再写remove
            path.addLast(nums[i]);
            dfs4(nums, i+1);
            path.removeLast();
        }
    }
}
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

    // 法1【理解】：❤本题也可以不用used数组来去重（见下），因为递归的时候下一个startIndex是i+1而不是0。
    //如果要是全排列的话，每次要从0开始遍历，为了跳过已入栈的元素，需要使用used。
    private void dfs(int[] nums, int idx, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
            // ↓ 对同层使用过的元素进行跳过（回溯时）
            if (i != idx && nums[i] == nums[i-1])
                continue; // 出现重复数字（前提：排序,重复数字idx相邻）

            path.add(nums[i]); // 不是idx！是i！否则无法去重！
            dfs(nums, i+1, path, res);
            path.remove(path.size()-1);
        }
    }
    // 法2【推荐】：(使用used[]去重 - 【同层】的结点，即【数值重复 && !used[i-1] -- 本层的重复结点与之前遍历的结点是[水平]关系，算作重复】)
    // 补充：同树枝的重复结点是允许(不算重复)的，
    //      如[1,2,2]子集本身，此时：
    //      【数值重复 && used[i-1]为真--本层的重复结点是从设true后下探的(同气连枝,上下级)】
    private void dfs3(int[] nums, int idx, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for (int i = idx; i < nums.length; i++) {
// 去重2：重复且前一个相同元素刚选过置否(也可以不加not去重另一部分)
// 写 !used[i - 1] 是因为 nums[i-1] 在DFS过程中刚刚被撤销选择 - 见q47.全排列ii
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


