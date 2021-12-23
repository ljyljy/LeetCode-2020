package DataStructure.Tree;

import java.util.ArrayList;
import java.util.List;

public class q501_find_mode_in_binary_search_tree {
    TreeNode pre;
    int maxCnt = 0, curCnt = 0;
    List<Integer> res = new ArrayList<>();
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[]{};
        dfs(root); // �� ������int[]
        // Integer[] resInt = res.toArray(new Integer[res.size()]);
        return res.stream().mapToInt(Integer::valueOf).toArray(); //
    }

    // BST��������
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        // ?�У�(1)�ȶԱ�pre��cur (2)��Ա�cnt
        if (pre == null) {
            curCnt = 1;
            res.add(root.val); // ��ֻ��һ���ڵ�
        } else if (pre.val == root.val) {
            curCnt++;
        } else {// �����µ�,�����ֵ(cnt����Ϊ1)
            curCnt = 1;
        }

        if (curCnt == maxCnt) {
            res.add(root.val);
        } else if (curCnt > maxCnt) {
            res.clear(); // ��վ�ֵ��Ӧ��"������"
            maxCnt = curCnt; // ����-���򣬱���һ���������ظ���ֵ(cnt++)/�����ֵ(cnt����Ϊ1)
            res.add(root.val); // ����������(���ظ�������)
        }
        pre = root; // ����pre
        dfs(root.right);
    }
}
