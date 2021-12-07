package Divide_Conquer;

import Recursion.q9_596_minimum_subtree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class q257_binary_tree_paths {
    // 法1：Traverse
    List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return res;
        dfs(root, root.val + "");
        return res;
    }

    private void dfs(TreeNode root, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(path);
            return;
        }
        if (root.left != null)
            dfs(root.left, path + "->" + root.left.val);
        if (root.right != null)
            dfs(root.right, path + "->" + root.right.val);
    }

    // 法2：分治
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        // 自顶向下
        List<String> leftPaths = binaryTreePaths2(root.left); // 以root为根的所有左子路径
        List<String> rightPaths = binaryTreePaths2(root.right);// 以root为根的所有右子路径

        // 自顶向下：从叶子往上add，所以root.val要在前面插入
        for (String leftpath : leftPaths) {
            res.add(root.val + "->" + leftpath);
        }

        for (String rightPath : rightPaths) {
            res.add(root.val + "->" + rightPath);
        }

        // node is a leaf
        // 从叶子开始，res才不空（然后向上回溯add）
        if (res.size() == 0) {
            res.add("" + root.val);
        }
        return res;
    }
}