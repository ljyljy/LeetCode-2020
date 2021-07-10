package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q783_minimum_distance_between_bst_nodes {
    // 法1 - 递归中序 + 数组遍历minDiff(把二叉搜索树转化为有序数组)
    private List<Integer> nodes = new ArrayList<>();
    public int minDiffInBST_v1(TreeNode root) {
        traverse(root);
        if (nodes.size() < 2) return 0;
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < nodes.size(); i++) {
            minDiff = Math.min(minDiff, nodes.get(i) - nodes.get(i-1));
        }
        return minDiff;
    }

    // BST中序遍历 -> 升序数组
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        nodes.add(root.val);
        traverse(root.right);
    }

    // 法2 - 递归中序（同时计算minDiff = pre - cur节点）
    private int ans = Integer.MAX_VALUE;
    private TreeNode pre;
    public int minDiffInBST_v2(TreeNode root) {
        if (root == null) return 0;
        traverse_v2(root);
        return ans;
    }

    private void traverse_v2(TreeNode cur) {
        if (cur == null) return;
        traverse_v2(cur.left); // 1. 左
        if (pre != null)       // 2. 根
            ans = Math.min(ans, cur.val - pre.val);
        pre = cur; // ❤ 一定要写在[根处理块]范围内！写在下面指针会变！
        traverse_v2(cur.right);// 3. 右
    }


    // 法3 - 非递归 中序 - 左中右（同时计算minDiff）
    public int minDiffInBST_v3(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left; // 1. 左
            } else { // cur == null
                cur = stack.pop(); // 2. 根
                if (pre != null)
                    ans = Math.min(ans, cur.val - pre.val);
                pre = cur;
                cur = cur.right; // 3. 右
            }
        }
        return ans;
    }
}
