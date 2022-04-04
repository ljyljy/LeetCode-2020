package Divide_Conquer;

public class qo_26_isSubStructure {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        // 1. A == B
        if (dfs(A, B)) return true;
        // 2. A���� ���� B
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    private boolean dfs(TreeNode A, TreeNode B) {
        if (B == null) return true;
        if (A == null || A.val != B.val) return false;
        // # �ݹ�����ӽṹ�ڲ�ÿ���ڵ��Ƿ���ͬ -- A��Bͬ���ƶ���and��
        return dfs(A.left, B.left) && dfs(A.right, B.right);
    }
}
