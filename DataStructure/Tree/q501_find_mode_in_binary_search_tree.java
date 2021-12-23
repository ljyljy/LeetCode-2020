package DataStructure.Tree;

import java.util.ArrayList;
import java.util.List;

public class q501_find_mode_in_binary_search_tree {
    TreeNode pre;
    int maxCnt = 0, curCnt = 0;
    List<Integer> res = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        dfs(root); // ↓ 不符合int[]
        // Integer[] resInt = res.toArray(new Integer[res.size()]);
        return res.stream().mapToInt(Integer::valueOf).toArray(); //
    }

    // BST中序：升序
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        // ?中：(1)先对比pre与cur (2)后对比cnt
        if (pre == null) {
            curCnt = 1;
            res.add(root.val); // 若只有一个节点
        } else if (pre.val == root.val) {
            curCnt++;
        } else {// 遇到新的,更大的值(cnt重置为1)
            curCnt = 1;
        }

        if (curCnt == maxCnt) {
            res.add(root.val);
        } else if (curCnt > maxCnt) {
            res.clear(); // 清空旧值对应的"旧众数"
            maxCnt = curCnt; // 中序-升序，遍历一定是遇到重复的值(cnt++)/更大的值(cnt重置为1)
            res.add(root.val); // 加入新众数(不重复的数字)
        }
        pre = root; // 更新pre
        dfs(root.right);
    }
}
