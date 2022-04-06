package DataStructure.Tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class q9_126_max_tree {

    public TreeNode maxTree(int[] A) {
        if(A == null || A.length == 0) return null;
        int n = A.length;
        // ά�����ݼ�ջ�������ѹջ+INF(.L)
        Deque<TreeNode> stack = new ArrayDeque<>();
        for(int i = 0; i <= n; i++) {
            TreeNode node = (i == n) ? new TreeNode(Integer.MAX_VALUE)
                    : new TreeNode(A[i]);
            // ��ǰnode>=ջ�������Է���pop����/�Ҹ���֮һ�����ӽ�/С�ߵ���Ϊֱϵ���ף���
            while(!stack.isEmpty() && stack.peek().val <= node.val){
                TreeNode cur = stack.pop();
                // ���ľ��ǰ���pop�����ĵ�cur����ȥL������R��
                if(!stack.isEmpty() && node.val > stack.peek().val) {
                    // peek��Ϊ��С�ߡ�����cur���ӽ�����Ϊ��ֱϵ���ס��������ϣ�cur��peek���Ҳࡿ��
                    // ��node�����򲻶�node��������һ��node������while(stack)Ԫ��"����"
                    stack.peek().right = cur;
                } else { // node��ΪС�ߣ���cur���ӽ�����Ϊֱϵ���ף������ϣ�cur��node����ࡿ��
                    node.left = cur;
                }
            }
            stack.push(node);
        }
        return stack.pop().left; // �ڱ�MAX������
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
