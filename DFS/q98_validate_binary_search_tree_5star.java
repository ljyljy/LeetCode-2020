package Recursion;

public class q98_validate_binary_search_tree_5star {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    // 法一：递归；一定要B！不可小写b！会超时！？
    private Boolean flag = true;
    public boolean isValidBST1(TreeNode root) {
        helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return flag;
    }

    private void helper(TreeNode root, long min, long max) {
        // 1.避免在已经确定false的情况下任然继续遍历 2.root为空，默认为true
        if (!flag || root == null) return;
        if (root.val <= min || root.val >= max) {
            flag = false;
            return;
        }
        helper(root.left, min, root.val);
        helper(root.right, root.val, max);
    }
//
//    private boolean helper1(TreeNode root, Integer min, Integer max) {
//        if (root == null) return true;
//        if (root.val <= min || root.val >= max) return false;
//        return helper(root.left, min, root.val)
//                && helper(root.right, root.val, max);
//    }

    // 法2：中序遍历
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false; // 左
        // 中序遍历后的二叉搜索树【升序】，若当前val<=前一个结点val，则不满足升序
        if (root.val <= pre) return false;
        pre = root.val;
//        if (!isValidBST(root.right)) return false; // 右
        return isValidBST(root.right); // 右
    }
}
