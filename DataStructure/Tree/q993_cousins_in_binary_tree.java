package DataStructure.Tree;

public class q993_cousins_in_binary_tree {
    private int x_val, y_val;
    private int x_depth, y_depth;
    private TreeNode x_parent, y_parent;
    private boolean x_found, y_found;

    // 法1：DFS - 时、空 O(n)
    public boolean isCousins(TreeNode root, int x, int y) {
        x_val = x; y_val = y;
        dfs(root, 0, null);
        return x_depth == y_depth && x_parent.val != y_parent.val;
    }

    private void dfs(TreeNode root, int depth, TreeNode parent) {
        if (root == null) return;
        if (root.val == x_val) {
            x_depth = depth;
            x_parent = parent;
            x_found = true;
        } else if (root.val == y_val) {
            y_depth = depth;
            y_parent = parent;
            y_found = true;
        }

        // 如果两个节点都找到了，就可以提前退出遍历
        if (x_found && y_found) return;
        dfs(root.left, depth+1, root);
        if (x_found && y_found) return;
        dfs(root.right, depth+1, root);
    }


}
