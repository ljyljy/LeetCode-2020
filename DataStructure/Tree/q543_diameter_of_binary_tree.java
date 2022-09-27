package DataStructure.Tree;

public class q543_diameter_of_binary_tree {
    private int maxCnt = 1;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return maxCnt -1; // ���ֱ��/����=���ڵ���-1
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int len_L = dfs(root.left);
        int len_R = dfs(root.right);
        // ȫ�����ڵ���=��ڵ���+�ҽڵ���+�Լ�
        maxCnt = Math.max(maxCnt, len_L + len_R + 1);
        return Math.max(len_L, len_R) + 1;  //���ص��߽ڵ���
    }
}
