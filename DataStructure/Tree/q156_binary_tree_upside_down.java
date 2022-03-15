package DataStructure.Tree;

public class q156_binary_tree_upside_down {
    TreeNode newRoot;
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null) return null;
        dfs(root, null); // 半前序：左+根(?无需遍历右!)
        return newRoot;
    }

    private void dfs(TreeNode root, TreeNode pre) {
        if (root == null) return ;
        dfs(root.left, root);
        // ↑ 递归返回时，root为树的最左结点↓（newRoot）
        if (newRoot == null) newRoot = root;
        if (pre != null) {
            root.left = pre.right;
            root.right = pre;
            // 逆序向上遍历，先置空
            pre.right = null;
            pre.left = null;
        }
    }
}
