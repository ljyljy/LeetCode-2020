package Recursion;

import java.util.ArrayList;
import java.util.List;


public class q95_unique_binary_search_trees_ii {
    public List<TreeNode> generateTrees(int n) {
        return dfs(1, n); // ����ұ�
    }

    private List<TreeNode> dfs (int start, int end) {
        List<TreeNode> res = new ArrayList<>(); // allTrees
        if (start > end) {
            res.add(null);
            return res;
        }
        // ö�ٿ��и��ڵ�
        for (int i = start; i <= end; i++) {
            List<TreeNode> lfTrees = dfs(start, i-1); // ���п��е�����������
            List<TreeNode> rtTrees = dfs(i+1, end);// ���п��е�����������

            for (TreeNode lf: lfTrees) {
                for (TreeNode rt: rtTrees) {
                    // ?������for�ڴ���root������ᱻ���Ǿ�ֵ��
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
