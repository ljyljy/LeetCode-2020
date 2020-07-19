package Divide_Conquer;

import java.util.HashSet;
import java.util.Set;

// q9_474
public class q236_lowest_common_ancestor_ii {
    class ParentTreeNode {
        int val;
        ParentTreeNode parent, left, right;
        ParentTreeNode(int x) { val = x; }
    }
    public ParentTreeNode lowestCommonAncestorII(
            ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // 把A的祖先节点都加入到哈希表中
        ParentTreeNode cur = A;
        Set<ParentTreeNode> parentSet = new HashSet<>();
        while (cur != null) {
            parentSet.add(cur);
            cur = cur.parent;
        }
        // 遍历B的祖先节点，第一个在哈希表中出现的即为答案
        cur = B;
        while (cur != null) {
            if (parentSet.contains(cur)) {
                return cur;
            }
            cur = cur.parent;
        }
        return null;
    }
}
