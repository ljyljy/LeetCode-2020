package DataStructure.Tree;

import java.lang.reflect.Array;
import java.util.*;

public class q99_recoverBST {
    // ��1��dfs+����ռ�List=O(n)
    List<TreeNode> res = new ArrayList<>();
    public void recoverTree_dfs(TreeNode root) {
        if (root == null) return;
        dfs(root);
        TreeNode x = null, y = null;
        // BST����res��Ӧ�����ҵ������<x-�׸�,��Max; y-�����ҵ�min>
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
        // ?�ӣ�Javaֻ��ֵ���ݣ��滻TreeNode tmp�ں�������Ч��-> �滻TreeNode��ֵ
        int tmp = x.val;
        x.val = y.val;
        y.val = tmp;
        // WA ��
        // TreeNode tmp = x;
        // x = y;
        // y = tmp;
    }

    // ��1-2��dfs+�ռ�O(1)
    private TreeNode pre;
    TreeNode x = null, y = null; // ����<x > y> -> �滻ֵ
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
        // ��[��]��
        if (pre != null) { // ����������ԣ�����[pre=x��ֻ�����״Σ�, cur=y���ڶ��Σ�]
            if (pre.val > root.val) {
                y = root;
                if (x == null) x = pre;
            }
        }
        pre = root;
        dfs2(root.right);
    }
}
