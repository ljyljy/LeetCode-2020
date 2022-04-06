package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class q9_126_max_tree {

    public TreeNode maxTree(int[] A) {
        if(A == null || A.length == 0) return null;
        int n = A.length;
        // 维护【递减栈】，最后压栈+INF(.L)
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(int i = 0; i <= n; i++) {
            TreeNode node = (i == n) ? new TreeNode(Integer.MAX_VALUE)
                    : new TreeNode(A[i]);
            // 当前node>=栈顶，可以分配pop的左/右父亲之一（更接近/小者的作为直系父亲）。
            while(!stack.isEmpty() && stack.peek().val <= node.val){
                TreeNode cur = stack.pop();
                // 核心就是安排pop出来的点cur，是去L，还是R；
                if(!stack.isEmpty() && node.val > stack.peek().val) {
                    // peek作为【小者】，与cur更接近，作为【直系父亲】（物理上，cur在peek【右侧】）
                    // 若node更大，则不对node操作，下一轮node继续把while(stack)元素"熬走"
                    stack.peek().right = cur;
                } else { // node作为小者，与cur更接近，作为直系父亲（物理上，cur在node【左侧】）
                    node.left = cur;
                }
            }
            stack.push(node);
        }
        return stack.pop().left; // 哨兵MAX的左孩子
    }
}

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
