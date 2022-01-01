package DataStructure.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q9_206_interval_sum {
    class Interval {
        int start, end;
        public Interval(){}
        public Interval(int start, int end) {
            this.start = start; this.end = end;
        }
    }

    // 法1：前缀和【推荐】（场景：静态，范围小，小数据）
    public List<Long> intervalSum_v1(int[] A, List<Interval> queries) {
        List<Long> res = new ArrayList<>();

        if (A == null || A.length == 0) return res;
        int n = A.length;
        long[] prefixSum = new long[n+1];
        for (int i = 1; i <= n; i++)
            prefixSum[i] = prefixSum[i-1] + A[i-1];
        for (Interval query: queries) {
            res.add(prefixSum[query.end+1] - prefixSum[query.start]);
        }
        return res;
    }

    // 法2：线段树(场景：实时动态大数据的区间查询)
    class SegmentTreeNode {
        SegmentTreeNode left, right;
        int start, end;
        long sum;
        public SegmentTreeNode(){}
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

        private SegmentTreeNode build(int start, int end, int[] arr) {
            if (start > end) return null;
            SegmentTreeNode root = new SegmentTreeNode(start, end, arr[start]);
            if (start == end) return root;

            int mid = start + end >> 1;
            root.left = build(start, mid, arr);
            root.right = build(mid+1, end, arr);
            root.sum = root.left.sum + root.right.sum;
            return root;
        }

        // 内部实现细节--方法重载
        private long query(SegmentTreeNode root, int L, int R) {
            if (L > R) return 0;
            if (R < root.start || root.end < L) return 0; // [L, R]完全在root范围外
            if (L <= root.start && root.end <= R) return root.sum;

            // 二分麻烦，面试只写分治即可，过了就行！
            long sumL = query(root.left, L, R);
            long sumR = query(root.right, L, R);
            return sumL + sumR;
        }
    }

    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        List<Long> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        // 创建&构造线段树
        SegmentTree st = new SegmentTree(A);

        for (Interval query: queries) {
            res.add(st.query(query.start, query.end));
        }
        return res;
    }
}
