package DataStructure.SegmentTree;

public class q307_range_sum_query_mutable {
    class SegmentTreeNode {
        SegmentTreeNode left, right;
        int start, end, sum;
        public SegmentTreeNode(int start, int end, int sum) {
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

        public int query(int L, int R) {
            return query(root, L, R);
        }

        public void update(int idx, int val) {
            update(root, idx, val);
        }

        private SegmentTreeNode build(int start, int end, int[] arr) {
            if (start > end) return null;
            SegmentTreeNode node = new SegmentTreeNode(start, end, arr[start]);
            if (start == end) return node;

            int mid = start + end >> 1;
            node.left = build(start, mid, arr);
            node.right = build(mid+1, end, arr);
            node.sum = node.left.sum + node.right.sum;
            return node;
        }

        private int query(SegmentTreeNode root, int L, int R) {
            if (L > R) return 0;
            if (R < root.start || root.end < L) return 0;
            if (L <= root.start && root.end <= R) return root.sum;

            int sumL = query(root.left, L, R);
            int sumR = query(root.right, L, R);
            return sumL + sumR;
        }

        private void update(SegmentTreeNode root, int idx, int val) {
            if (root.start == idx && root.end == idx) {
                root.sum = val;
                return;
            }
            int mid = root.start + root.end >> 1;
            if (idx <= mid) update(root.left, idx, val);
            else update(root.right, idx, val);
            root.sum = root.left.sum + root.right.sum;
        }

    }

    SegmentTree st;
    public q307_range_sum_query_mutable(int[] arr) {
        if(arr == null || arr.length == 0) return;
        st = new SegmentTree(arr);
    }

    public void update(int idx, int val) {
        st.update(idx, val);
    }

    public int sumRange(int start, int end) {
        return st.query(start, end);
    }
}
