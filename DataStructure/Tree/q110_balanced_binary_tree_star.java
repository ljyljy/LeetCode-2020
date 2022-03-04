package DataStructure.Tree;

public class q110_balanced_binary_tree_star {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return getDepth(root) != -1;
    }

    private int getDepth (TreeNode root) {
        if (root == null) return 0;

        int depthL = getDepth(root.left);
        if (depthL == -1) return -1;  // ÎðÂ©£¡
        int depthR = getDepth(root.right);
        if (depthR == -1) return -1;  // ÎðÂ©£¡

        int diff = Math.abs(depthL - depthR);
        if (diff > 1) return -1;
        else return Math.max(depthL, depthR) + 1;
    }

}
