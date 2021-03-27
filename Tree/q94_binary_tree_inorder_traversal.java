package Tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class q94_binary_tree_inorder_traversal {

    // 1. 递归 【荐1】
    public List<Integer> inorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res){
        if (root == null) return;
        if (root.left != null) helper(root.left, res);
        res.add(root.val);
        if (root.right != null) helper(root.right, res);
    }

    // 2.栈 - 逆（左中右）
    public List<Integer> inorderTraversal_2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode p = root; // 勿忘！不要妄自移动root！
        while (p != null || !stack.isEmpty()) {// 注意是或！！！首次为空
            while (p != null) { // 【1】所有<中, 左(top)>压栈
                stack.push(p); // 中，addFirst
                p = p.left; // 左
            } // end: cur == null4
            // 退出while -- 找到[最左子树(左, top) & 最左的父亲(中,次top)] 且 p==null;
            p = stack.pop();// 【2】pop <左>-top / <中> / 右
            res.add(p.val); // all <左> 、 <中>
            p = p.right; // 【3】所有<右>压栈【此时<中>未弹栈，要等到内while才会弹】
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

    // 3. 染色法，只能通过部分案例
//    public List<Integer> inorderTraversal(TreeNode root) {
//         List<Integer> res = new ArrayList<>();
//         Deque<Object> stack = new ArrayDeque<>();
//         if (root != null)  stack.push(root);
//         else return res;
//
//         while (!stack.isEmpty()) {
//             Object i = stack.pop();
//             // if (int.class.isInstance(i)){
//             //     System.out.println("int.class.isInstance(i): "+ i);
//                 // int val = Integer.parseInt(i.toString());
//                 // if (val != -1)
//                 //     res.add(val);
//             // }
//             if (i instanceof TreeNode){
//                 System.out.println(((TreeNode) i).val);
//                 // i = (TreeNode) i;
//                 if (((TreeNode) i).right != null)
//                     stack.push(((TreeNode) i).right);
//                 else stack.push(-1);
//                 stack.push(((TreeNode) i).val);
//                 if (((TreeNode) i).left != null)
//                     stack.push(((TreeNode) i).left);
//                 else stack.push(-1);
//             } else{
//                 int val = Integer.parseInt(i.toString());
//                 if (val != -1)
//                     res.add(val);
//             }
//         }
//         return res;
//     }

}
