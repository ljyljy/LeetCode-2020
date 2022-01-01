package DataStructure.SegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class q315v2_segmentTree {
    // 法2： 线段树
    private class SegmentTreeNode {
        public int start, end, cnt;
        public SegmentTreeNode left, right;
        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.cnt = 0;
        }
    }

    private class SegmentTree {
        private SegmentTreeNode root;

        private SegmentTreeNode buildTree(int start, int end) {
            SegmentTreeNode node = new SegmentTreeNode(start, end);
            if(start == end)  return node; // 勿漏！❤

            int mid = start + (end - start) / 2;
            node.left = buildTree(start, mid);
            node.right = buildTree(mid+1, end);
            node.cnt = node.left.cnt + node.right.cnt;  // 勿漏！❤
            return node; // 勿漏！❤
        }

        private void add(SegmentTreeNode node, int index, int value) {
            if(node.start == node.end && node.end == index){
                node.cnt += value;
                return;
            }

            int mid = node.start + (node.end - node.start) / 2;
            if(node.start <= index && index <= mid){
                add(node.left, index, value);
            }
            if(mid+1 <= index && index <= node.end) {
                add(node.right, index, value);
            }
            node.cnt = node.left.cnt + node.right.cnt; // 勿漏！❤
        }

        private int queryCnt(SegmentTreeNode node, int start, int end) {
            if (start > end) return 0; // 不能>=
            if(node.start == start && node.end == end) {
                return node.cnt;
            }

            int mid = node.start +(node.end - node.start) / 2;
            int sum_L = 0, sum_R = 0;
            sum_L = queryCnt(node.left, start, Math.min(mid, end));
            sum_R = queryCnt(node.right, Math.max(mid+1, start), end);
            return sum_L + sum_R;
        }

        public SegmentTree(int size) { // 构造方法勿漏❤
            root = buildTree(0, size-1);
        }

        public int queryCnt(int start, int end) {
            return queryCnt(root, start, end);
        }

        public void add(int index, int value) {
            add(root, index, value);
        }
    }

    public List<Integer> countSmaller(int[] A) {
        List<Integer> res = new ArrayList<Integer>();
        if(A == null || A.length == 0) return res;
        int max = Arrays.stream(A).max().getAsInt();
        int min = Arrays.stream(A).min().getAsInt();
        min = Math.min(min, 0); // 注意如果min > 0, 那么没必要shift;
        int shift =  Math.abs(min);           // ↓ 不是n！
        SegmentTree tree = new SegmentTree(max - min + 1); // [0, max] -- [min+shift, max+shift]
        for(int i = A.length - 1; i >= 0 ; i --) { // 从后往前遍历
            tree.add(A[i] + shift, 1);// ❤勿忘+shift！！
            if(A[i] == min) { // 勿忘if❤ 比min小的数没有了，所以是0；
                res.add(0, 0); // 头插 - 插在最前面
            } else { // 注意区间右边需-1！！因为需要找比A[i]小的数的个数
                res.add(0, tree.queryCnt(0, A[i]-1 + shift));
            } // ❤勿忘+shift！！
        }
        return res;
    }
}
