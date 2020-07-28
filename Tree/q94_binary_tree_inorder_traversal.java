package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q94_binary_tree_inorder_traversal {

//    Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 1. 递归 【荐1】
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null)
                helper(root.left, res);
            res.add(root.val);
            if (root.right != null)
                helper(root.right, res);
        }
    }

    // 2.栈 - 逆（左中右） -- 右中左
    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root; // 勿忘！不要妄自移动root！
        while (curr != null || !stack.isEmpty()) {// 注意是或！！！
            while (curr != null) { // 【1】所有<中, 左(top)>压栈
                stack.push(curr); // 中，addFirst
                curr = curr.left; // 左
            } // end: cur == null4
            curr = stack.pop();// 【2】pop <左>-top / <中> / 右
            res.add(curr.val); // all <左> 、 <中>
            curr = curr.right; // 【3】所有<右>压栈【此时<中>未弹栈，要等到内while才会弹】
        }
        return res;
    }

    // 3. 染色法
//    class Solution:
//    def inorderTraversal(self, root: TreeNode) -> List[int]:
//    stack,rst = [root],[]
//            while stack:
//    i = stack.pop()
//            if isinstance(i,TreeNode):
//            stack.extend([i.right,i.val,i.left])
//    elif isinstance(i,int):
//            rst.append(i)
//            return rst

}
