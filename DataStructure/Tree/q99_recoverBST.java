package DataStructure.Tree;

import java.lang.reflect.Array;
import java.util.*;

public class q99_recoverBST {
    // 法1：dfs+额外空间List=O(n)
    List<TreeNode> res = new ArrayList<>();
    public void recoverTree_dfs(TreeNode root) {
        if (root == null) return;
        dfs(root);
        TreeNode x = null, y = null;
        // BST中序：res理应升序，找到逆序对<x-首个,即Max; y-向右找到min>
        for (int i = 0; i+1 < res.size(); i++) {
            if (res.get(i).val > res.get(i+1).val) {
                if (x == null) x = res.get(i);
                y = res.get(i+1);
            }
        }
        swap(x, y);
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        res.add(root);
        dfs(root.right);
    }

    private void swap(TreeNode x, TreeNode y) {
        // ?坑：Java只有值传递！替换TreeNode tmp在函数外无效！-> 替换TreeNode的值
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
        // WA ↓
        // TreeNode tmp = x;
        // x = y;
        // y = tmp;
    }

    // 法1-2：dfs+空间O(1)
    private TreeNode pre;
    TreeNode x = null, y = null; // 逆序<x > y> -> 替换值
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        dfs2(root);
        if (x != null && y != null) {
            swap(x, y);
        }
    }


    private void dfs2(TreeNode root) {
        if (root == null) return;
        dfs2(root.left);
        // 左[中]右
        if (pre != null) { // 若发现逆序对，更新[pre=x（只更新首次）, cur=y（第二次）]
            if (pre.val > root.val) {
                y = root;
                if (x == null) x = pre;
            }
        }
        pre = root;
        dfs2(root.right);
    }
}
