package Divide_Conquer;

public class qo_26_isSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        // 1. A == B
        if (dfs(A, B)) return true;
        // 2. A子树 包含 B
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        // # 递归查找子结构内部每个节点是否相同 -- A、B同步移动【and】
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
