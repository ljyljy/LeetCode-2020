package Divide_Conquer;

//q9_578
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}


public class q236_lowest_common_ancestor_iii {
//    法1：不用ResultType、使用全局变量
    private boolean foundA = false, foundB = false;
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;
        TreeNode res = divConq(root, A, B);
        if (foundA && foundB)
            return res;
        else
            return null;
    }

    private TreeNode divConq(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;

        // 分治
        TreeNode left = divConq(root.left, A, B);
        TreeNode right = divConq(root.right, A, B);

        // 递归出口【【【此题一定要在[分治后]进行判断！】】】
        // 如果 root 是要找的，更新全局变量
        // 当分治开始后：首次找到A/B结点（root == A||B）时，首次更新found为True
        if (root == A || root == B) {
            foundA = (root == A) || foundA;
            foundB = (root == B) || foundB;
            // 此时，foundA/B不一定均True
            return root; // 勿忘返回root！！！！
        }

        // 和 LCA 原题的思路一样
        if (left != null && right != null)
            return root;
        else if (left != null) // 注意这种情况返回的时候是不保证两个都有找到的。可以是只找到一个或者两个都找到
            return left;       // 所以在最后上面要查看是不是两个都找到了
        else if (right != null)
            return right;
        return null;
    }

    //    法2：
    class ResultType {
        public boolean a_exist, b_exist;
        public TreeNode node;
        ResultType(boolean a_exist, boolean b_exist, TreeNode node) {
            this.a_exist = a_exist;
            this.b_exist = b_exist;
            this.node = node;
        }
    }

    public TreeNode lowestCommonAncestor3_2(TreeNode root, TreeNode A, TreeNode B) {
        if (root == null) return null;
        ResultType rt = helper(root, A, B);
        if (rt.a_exist && rt.b_exist)
            return rt.node;
        else
            return null;
    }

    private ResultType helper(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) return new ResultType(false, false, null);

        ResultType left_rt = helper(root.left, a, b);
        ResultType right_rt = helper(root.right, a, b);
        boolean a_exist = left_rt.a_exist || right_rt.a_exist || root == a;
        boolean b_exist = left_rt.b_exist || right_rt.b_exist || root == b;

        if (root == a || root == b)
            return new ResultType(a_exist, b_exist, root);
        if (left_rt.node != null && right_rt.node != null)
            return new ResultType(a_exist, b_exist, root);
        else if (left_rt.node != null)
            return new ResultType(a_exist, b_exist, left_rt.node);
        else if (right_rt.node != null)
            return new ResultType(a_exist, b_exist, right_rt.node);

        return new ResultType(a_exist, b_exist, null);
    }
}


