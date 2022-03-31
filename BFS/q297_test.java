package BFS;

import java.util.Scanner;

public class q297_test {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()) {
//            String[] tree = sc.nextLine().split(",");
        String sysIn = "1,2,3,4,null,2,4,null,null,4";
        String[] tree = sysIn.split(",");
        int n = tree.length;

//        Integer[] tree2 = new Integer[n]; // –¥∑®2£∫Integer[]‘ –Ì¥Ê¥¢null÷µ
//        for (int i = 0; i < n; i++) {
//            if (!"null".equals(tree[i]))
//                tree2[i] = Integer.valueOf(tree[i]);
//            else tree2[i] = null;
//            System.out.println(tree2[i]);
//        }
//        }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode() {}

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
