package Tree;

import java.util.*;

public class q145_binary_tree_postorder_traversal {
    // 法1.递归
    public List<Integer> postorderTraversal_0(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left != null) helper(root.left, res);
        if (root.right != null) helper(root.right, res);
        res.add(root.val);
    }

    // 【大一统迭代(推荐模板1)】
    // 法0：❤迭代写法 - 后序(左右中) - 压栈:中右左
    TreeNode nullNode = new TreeNode(Integer.MIN_VALUE);
    public List<Integer> postorderTraversal_stack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (!node.equals(nullNode)) { // 压栈:(中+NULL)右左
                stack.push(node);
                stack.push(nullNode);
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }else { // 说明下一个pop的是根
                res.add(stack.pop().val);
            }
        }
        return res;
    }



    // 法2.非递归v1 【一个栈搞定 -- 不用逆序压栈！】
    // 思路：
    //    顺序压栈【左右根】, 找到最左叶子->弹栈(记last_pop)
    //  ->回溯到父亲->找右儿子（直到叶子）
    //  ->回溯到父亲->父亲也压栈->...(循环)
    public List<Integer> postorderTraversal1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        stack.push(root);
        TreeNode last_pop = root;

        int k = 0;
        while (!stack.isEmpty()) {
            TreeNode p = stack.peekFirst();
            // TEST
            System.out.println(++k + " - (1). peek():  " + p.val);
            if (p.right == last_pop)
                System.out.println(k + " ----  p.right == last_pop:  " + last_pop.val);
            else if (p.left == last_pop)
                System.out.println(k + " ----  p.left == last_pop:  " + last_pop.val);
            //
            if (p.left != null && p.left != last_pop && p.right != last_pop) {
                System.out.println(++k + " - (2). push(left):  " + p.left.val);
                stack.push(p.left);
            } else if (p.right != null && p.right != last_pop) {
                System.out.println(++k + " - (3). push(right):  " + p.right.val);
                stack.push(p.right);
            }
            else { // p为叶子 || 【左右根】顺序压栈
                System.out.println(++k + " - (4). res.add(stack.pop().val):  " + p.val);
                res.add(stack.pop().val);
                last_pop = p;
            }
            // ↓ 死循环
            // if (p.right != null) stack.push(p.right);
            // if (p.left != null) stack.push(p.left);
            // res.add(stack.pop().val);
        }
        return res;
    }

    public List<Integer> postorderTraversal_v2_clear(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        stack.push(root);
        TreeNode last_pop = root;
        while (!stack.isEmpty()) {
            TreeNode p = stack.peekFirst();
            if (p.left != null && p.left != last_pop && p.right != last_pop) {
                stack.push(p.left);
            } else if (p.right != null && p.right != last_pop) {
                stack.push(p.right);
            } else { // p为叶子 || 【左右根】顺序压栈
                res.add(stack.pop().val);
                last_pop = p;
            }
        }
        return res;
    }

    // 法3：非递归v3 【模板】 【需要逆序压栈 -- [左-右-根]の逆 +list逆序！】
    // ❤❤❤【vs'前序遍历'】 -
    // (1) res头插法(逆序插入)
    // (2)入栈顺序-根右左(将'前序'模板中的左右子树颠倒)
    public List<Integer> postorderTraversal(TreeNode head) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;
        while (head != null || !stack.isEmpty()) {
            if (head != null) {
                stack.push(head); // 【1. 根】
                res.add(0, head.val);  // 头插 【1. 根 2.右、左子树插入头部】
                head = head.right; // 【2. 右】
            }else {
                TreeNode last_pop = stack.pop();
                head = last_pop.left;  // 【3. 左】
            }
        }
        return res;
    }

    // 法3：非递归v2
    public List<Integer> postorderTraversal_v3(TreeNode head) {
        List<Integer> res = new ArrayList<>();
        if (head == null) return res;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        int k = 0;
        stack1.push(head);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }

        return res;
    }
}

/*
* 输入：   [1,2,3,4,5,6,7]
输出：     [7,6,3,5,4,2,1]
预期结果： [4,5,2,6,7,3,1]
stdout
1 - (1). peek():  1
2 - (2). push(left):  2
3 - (1). peek():  2
4 - (2). push(left):  4
5 - (1). peek():  4
6 - (4). res.add(stack.pop().val):  4
7 - (1). peek():  2
7 ----  p.left == last_pop:  4
8 - (3). push(right):  5
9 - (1). peek():  5
10 - (4). res.add(stack.pop().val):  5
11 - (1). peek():  2
11 ----  p.right == last_pop:  5
12 - (4). res.add(stack.pop().val):  2
13 - (1). peek():  1
13 ----  p.left == last_pop:  2
14 - (3). push(right):  3
15 - (1). peek():  3
16 - (2). push(left):  6
17 - (1). peek():  6
18 - (4). res.add(stack.pop().val):  6
19 - (1). peek():  3
19 ----  p.left == last_pop:  6
20 - (3). push(right):  7
21 - (1). peek():  7
22 - (4). res.add(stack.pop().val):  7
23 - (1). peek():  3
23 ----  p.right == last_pop:  7
24 - (4). res.add(stack.pop().val):  3
25 - (1). peek():  1
25 ----  p.right == last_pop:  3
26 - (4). res.add(stack.pop().val):  1
* */