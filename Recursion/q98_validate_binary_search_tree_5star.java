package Recursion;

public class q98_validate_binary_search_tree_5star {
    // （代码随想录）：
    // 法0: 递归中序, 数组比较（略）
    // 法1-2：递归中序(升序), 递归中比较（Long MIN/MAX）
    public boolean isValidBST2(TreeNode root) {
        // 因为题目要求结点val∈[INT_MIN, INT_MAX]
        // 因此，初始边界的MIN,MAX需要与val错开，选择Long型 ↓
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean dfs (TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        boolean left = dfs(root.left, minVal, root.val); // 左 (当前: 左 < 中)
        if (root.val <= minVal || root.val >= maxVal)
            return false; // 取等！普通BST中不可有重复值！
        boolean right = dfs(root.right, root.val, maxVal); // 右 (当前：中 < 右)
        return left && right;
    }


    // 法1-1：递归；一定要B！不可小写b！会超时！？
    private Boolean flag = true;
    public boolean isValidBST1(TreeNode root) {
        helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return flag;
    }

    private void helper(TreeNode root, long min, long max) {
        // 1.避免在已经确定false的情况下任然继续遍历 2.root为空，默认为true
        if (!flag || root == null) return;
        helper(root.left, min, root.val);
        if (root.val <= min || root.val >= max) {
            flag = false;
            return;
        }
        helper(root.right, root.val, max);
    }

    // 法1-3【递归，通用，荐！】
    private TreeNode pre0;
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean left = isValidBST(root.left); // 左 (当前: 左 < 中)

        if (pre0 != null && root.val <= pre0.val) // BST中序：递增
            return false; // 取等！普通BST中不可有重复值！
        pre0 = root;

        boolean right = isValidBST(root.right); // 右 (当前：中 < 右)
        return left && right;
    }


//
//    private boolean helper1(TreeNode root, Integer min, Integer max) {
//        if (root == null) return true;
//        if (root.val <= min || root.val >= max) return false;
//        return helper(root.left, min, root.val)
//                && helper(root.right, root.val, max);
//    }

    // 法3：中序遍历
    private long pre = Long.MIN_VALUE;
    public boolean isValidBST3(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST(root.left)) return false; // 左
        // 中序遍历后的二叉搜索树【升序】，若当前val<=前一个结点val，则不满足升序
        if (root.val <= pre) return false;
        pre = root.val;
//        if (!isValidBST(root.right)) return false; // 右
        return isValidBST(root.right); // 右
    }
}
