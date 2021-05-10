package Tree;

import java.util.*;

public class q872_leaf_similar_trees {
    List<Integer> leaf1 = new ArrayList<>();
    List<Integer> leaf2 = new ArrayList<>();
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        getLeaves(root1, leaf1);
        getLeaves(root2, leaf2);
        // leaf1.sort((o1, o2)->(o1-o2)); // 叶值序列有先后，不可排序！
        // leaf2.sort((o1, o2)->(o1-o2));

        if (leaf1.size() != leaf2.size()) return false;
        for (int i = 0; i < leaf1.size(); i++) {
            if (leaf1.get(i) != leaf2.get(i))
                return false;
        }
        return true;
    }

    private void getLeaves(TreeNode root, List<Integer> list) {
        if (root.left!= null) getLeaves(root.left, list);
        if (root != null && root.left == null && root.right == null)
            list.add(root.val); // 叶子
        if (root.right!= null) getLeaves(root.right, list);
    }
}
