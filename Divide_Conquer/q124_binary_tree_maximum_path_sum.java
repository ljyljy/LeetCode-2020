package Divide_Conquer;

public class q124_binary_tree_maximum_path_sum {
    public int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        // ↓可能存在负结点，尽量避免【若全是负结点，则令左右子树和为0，最后返回最小结点(as root)的val！！】
        int sum_L = Math.max(0, helper(root.left));
        int sum_R = Math.max(0, helper(root.right));
        res = Math.max(res, sum_L + sum_R + root.val); // ❤【情况1. 本层 - 当前最大路径和（本情况无法递归，因为不是单边路径！）】

//        System.out.println("root="+root.val+", sum_L=" + sum_L + ", sum_R=" + sum_R
//                +  ", max(L/R)+root = " + (Math.max(sum_L, sum_R) + root.val)+", -- res=" + res);

        // 情况2/3：因为是从一个结点出发，而返回的应为当前节点及其子路径[左or右子树之一]，故应为“单边最大和”
        //          递归时，对于父来说，只能选择他的左/右孩子之一，接着再往上交付给爷爷。
        return Math.max(sum_L, sum_R) + root.val; // 【2. 递归 - 上层（本层根的左/右孩子及其单边最大子树和）
                                                  // - 下层 (本层根 + 最大单边左/右子树和)， 待与父亲相加构成更大的路径和res】
    }
}
