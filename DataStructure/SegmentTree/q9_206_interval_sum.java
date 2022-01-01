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

    // ��1��ǰ׺�͡��Ƽ�������������̬����ΧС��С���ݣ�
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

    // ��2���߶���(������ʵʱ��̬�����ݵ������ѯ)
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

        // �ڲ�ʵ��ϸ��--��������
        private long query(SegmentTreeNode root, int L, int R) {
            if (L > R) return 0;
            if (R < root.start || root.end < L) return 0; // [L, R]��ȫ��root��Χ��
            if (L <= root.start && root.end <= R) return root.sum;

            // �����鷳������ֻд���μ��ɣ����˾��У�
            long sumL = query(root.left, L, R);
            long sumR = query(root.right, L, R);
            return sumL + sumR;
        }
    }

    public List<Long> intervalSum(int[] A, List<Interval> queries) {
        List<Long> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        // ����&�����߶���
        SegmentTree st = new SegmentTree(A);

        for (Interval query: queries) {
            res.add(st.query(query.start, query.end));
        }
        return res;
    }
}
