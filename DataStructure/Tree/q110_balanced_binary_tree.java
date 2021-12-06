package DataStructure.Tree;

public class q110_balanced_binary_tree {
    private int getDepth (TreeNode root) {
        if (root == null) return 0;

        int depthL = getDepth(root.left);
        if (depthL == -1) return -1;
        int depthR = getDepth(root.right);
        if (depthR == -1) return -1;

        if (Math.abs(depthL - depthR) > 1)
            return -1;
        return 1 + Math.max(depthL, depthR);
    }
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int depthL = getDepth(root.left);
        if (depthL == -1) return false;
        int depthR = getDepth(root.right);
        if (depthR == -1) return false;

        return Math.abs(depthL - depthR) <= 1;
    }
}
