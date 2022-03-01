package Recursion;

import java.util.ArrayList;
import java.util.List;


public class q95_unique_binary_search_trees_ii {
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n); // 左闭右闭
    }

    private List<TreeNode> dfs (int start, int end) {
        List<TreeNode> res = new ArrayList<>(); // allTrees
        if (start > end) {
            res.add(null);
            return res;
        }
        // 枚举可行根节点
        for (int i = start; i <= end; i++) {
            List<TreeNode> lfTrees = dfs(start, i-1); // 所有可行的左子树集合
            List<TreeNode> rtTrees = dfs(i+1, end);// 所有可行的右子树集合

            for (TreeNode lf: lfTrees) {
                for (TreeNode rt: rtTrees) {
                    // ?必须在for内创建root！否则会被覆盖旧值！
                    TreeNode root = new TreeNode(i);
                    root.left = lf;
                    root.right = rt;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
