package DataStructure.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q9_249_countOfSmallerNumberII {
    public List<Integer> countOfSmallerNumberII(int[] A) {
        List<Integer> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        int n = A.length;
        int min = Arrays.stream(A).min().getAsInt();
        int max = Arrays.stream(A).max().getAsInt();
        min = Math.min(min, 0);
//        int shift = Math.abs(min);  // A[i]>=0
        SegmentTree tree = new SegmentTree(max - min + 1);
        for (int i = 0; i < n; i++) {
            tree.add(A[i], 1);
            if (A[i] == min)
                res.add(i, 0);
            else res.add(i, tree.queryCnt(0, A[i]-1));
        }
        return res;
    }

    private class SegmentTreeNode {
        int start, end;
        int cnt;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end) {
            this.start = start; this.end = end;
            this.left = this.right = null;
            this.cnt = 0;
        }
    }

    private class SegmentTree{
        private SegmentTreeNode root;
        private SegmentTreeNode buildTree (int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if (start == end) return node;
            int mid = start + end >> 1;
            node.left = buildTree(start, mid);
            node.right = buildTree(mid+1, end);
            node.cnt = node.left.cnt + node.right.cnt;
            return node;
        }

        private void add (SegmentTreeNode node, int idx, int val) {
            if (node.start == node.end && node.end == idx) {
                node.cnt += val;
                return;
            }
            int mid = node.start + node.end >> 1;
            if (node.start <= idx && idx <= mid)
                add(node.left, idx, val);
            if (mid < idx && idx <= node.end) // 不是elif！
                add(node.right, idx, val);
            node.cnt = node.left.cnt + node.right.cnt; // 勿忘！
        }

        private int queryCnt (SegmentTreeNode node, int start, int end) {
            if (node.start == start && node.end == end)
                return node.cnt;
            int mid = node.start + node.end >> 1;
            int cnt_L = 0, cnt_R = 0;
            if (start <= mid)
                cnt_L = queryCnt(node.left, start, Math.min(mid, end));
            if (mid < end)
                cnt_R = queryCnt(node.right, Math.max(mid+1, start), end);
            return cnt_L + cnt_R;
        }

        public SegmentTree (int n) {
            root = buildTree(0, n-1);
        }

        public void add (int idx, int val) {
            add(root, idx, val);
        }

        public int queryCnt (int start, int end) {
            return queryCnt(root, start, end);
        }
    }

}
