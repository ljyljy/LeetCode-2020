package DataStructure.Tree;

import java.util.*;

public class q652_find_duplicate_subtrees {
    Map<String, Integer> map = new HashMap();
    List<TreeNode> res = new ArrayList();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return res;
    }

    public String dfs(TreeNode root) {
        if (root == null) return "null";
        String serial = root.val + "," + dfs(root.left) + "," + dfs(root.right);
        int cnt = map.getOrDefault(serial, 0) + 1;
        map.put(serial, cnt);
        if (cnt == 2) {
            res.add(root);
        }
        return serial;
    }
}
