package DataStructure.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q9_249_simple_version {
    public List<Integer> countOfSmallerNumberII(int[] A) {
        List<Integer> res = new ArrayList<>();
        int min = Arrays.stream(A).min().getAsInt();
        int max = Arrays.stream(A).max().getAsInt();
        int size = max - min + 1;
        SegmentTree st = new SegmentTree(size);
        for (int i = 0; i < A.length; i++) {
            st.add(A[i] -min, 1);
            if (A[i] == min)
                res.add(0, 0);
            else
                res.add(i, st.queryCnt(0, A[i]-1 -min));
        }
        return res;
    }

    class SegmentTreeNode {
        int start, end, cnt;
        SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end) {
            this.start = start; this.end = end;
            this.left = this.right = null;
            this.cnt = 0;
        }
    }

    class SegmentTree {
        SegmentTreeNode root;
        public SegmentTree (int n) {
            root = build(0, n-1);
        }

        public void add(int idx, int val) {
            add(root, idx, val);
        }

        public int queryCnt(int start, int end) {
            return queryCnt(root, start, end);
        }

        private SegmentTreeNode build(int start, int end) {
            if (start > end) return null; // 不可取等！?
            SegmentTreeNode root = new SegmentTreeNode(start, end);
            if (start == end) return root;
            int mid = start + end >> 1;
            root.left = build(start, mid);
            root.right = build(mid+1, end);
            root.cnt = root.left.cnt + root.right.cnt;
            return root;
        }

        private void add(SegmentTreeNode root, int idx, int val) {
            if (idx < root.start || root.end < idx) return;
            if (root.start == idx && root.end == idx) {
                root.cnt += val;
                return;
            }
            int mid = root.start + root.end >> 1;
            if (idx <= mid) add(root.left, idx, val);
            else  add(root.right, idx, val);
            root.cnt = root.left.cnt + root.right.cnt;
        }

        private int queryCnt(SegmentTreeNode root, int start, int end) {
            if (start > end) return 0;// 不可取等！?
            if (end < root.start || root.end < start) return 0;
            if (start <= root.start && root.end <= end) return root.cnt;
            int cntL = queryCnt(root.left, start, end);
            int cntR = queryCnt(root.right, start, end);
            return cntL + cntR;
        }

    }
}
