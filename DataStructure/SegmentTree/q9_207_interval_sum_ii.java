package DataStructure.SegmentTree;

public class q9_207_interval_sum_ii {
    class SegmentTreeNode {
        SegmentTreeNode left, right;
        int start, end;
        long sum;
        public SegmentTreeNode() {}
        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    class SegmentTree {
        SegmentTreeNode root;
        public SegmentTree(int[] arr) {
            root = build(0, arr.length-1, arr);
        }

        public long query(int L, int R) {
            return query(root, L, R);
        }

        public void modify(int idx, int val) {
            modify(root, idx, val);
        }

        private SegmentTreeNode build (int start, int end, int[] arr) {
            if (start > end) return null;
            SegmentTreeNode node = new SegmentTreeNode(start, end, arr[start]);
            if (start == end) return node;

            int mid = start + end >> 1;
            node.left = build(start, mid, arr);
            node.right = build(mid+1, end, arr);
            node.sum = node.left.sum + node.right.sum;
            return node;
        }

        private long query (SegmentTreeNode root, int L, int R) {
            if (L > R) return 0;
            if (R < root.start || root.end < L) return 0;
            if (L <= root.start && root.end <= R) return root.sum;

            long sumL = query(root.left, L, R);
            long sumR = query(root.right, L, R);
            return sumL + sumR;
        }

        private void modify(SegmentTreeNode root, int idx, int val) {
            if (root.start == idx && root.end == idx) {
                root.sum = val;
                return;
            }
            int mid = root.start + root.end >> 1;
            if (idx <= mid) modify(root.left, idx, val);
            else modify(root.right, idx, val);
            root.sum = root.left.sum + root.right.sum;
        }

    }

    SegmentTree st;
    public q9_207_interval_sum_ii(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }
        st = new SegmentTree(A);
    }


    public long query(int start, int end) {
        return st.query(start, end);
    }


    public void modify(int idx, int val) {
        st.modify(idx, val);
    }
}
