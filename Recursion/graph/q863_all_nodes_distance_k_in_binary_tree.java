package Recursion.graph;

import BFS.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q863_all_nodes_distance_k_in_binary_tree {
    Map<Integer, TreeNode> parents = new HashMap<>(); // <val, parent>
    List<Integer> res = new ArrayList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // 从 root 出发 DFS，记录每个结点的父结点
        setParents(root);
        // 从 target 出发 DFS，寻找所有深度为 k 的结点
        getNodes_DistK(target, null, k);
        return res;
    }

    private void setParents(TreeNode root) {
        if (root == null) return;
        if (root.left != null) {
            parents.put(root.left.val, root);
            setParents(root.left);
        }
        if (root.right != null) {
            parents.put(root.right.val, root);
            setParents(root.right);
        }
    }

    private void getNodes_DistK(TreeNode root, TreeNode from, int k) {
        if (k < 0 || root == null) return;
        if (k == 0) {
            res.add(root.val);
        }
        if (root.left != from) {
            getNodes_DistK(root.left, root, k-1);
        }
        if (root.right != from) {
            getNodes_DistK(root.right, root, k-1);
        }
        TreeNode parent = parents.get(root.val);
        if (parent != from) {
            getNodes_DistK(parent, root, k-1);
        }
    }
}
