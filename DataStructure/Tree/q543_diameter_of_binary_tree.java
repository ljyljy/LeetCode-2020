package DataStructure.Tree;

public class q543_diameter_of_binary_tree {
    private int maxCnt = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return maxCnt -1; // 最大直径/边数=最大节点数-1
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int len_L = dfs(root.left);
        int len_R = dfs(root.right);
        // 全局最大节点数=左节点数+右节点数+自己
        maxCnt = Math.max(maxCnt, len_L + len_R + 1);
        return Math.max(len_L, len_R) + 1;  //返回单边节点数
    }
}
