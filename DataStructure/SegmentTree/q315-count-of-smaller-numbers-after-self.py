# 	Q315. 计算右侧小于当前元素的个数【后面】
# 给定一个整数数组 nums，按要求返回一个新数组 sums。数组 sums 有该性质： sums[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量
# 说明：最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
# 样例1：
# 输入：nums = [5,2,6,1]
# 输出：[2,1,1,0]
# 解释：
# 5 的右侧有 2 个更小的元素 (2 和 1)
# 2 的右侧仅有 1 个更小的元素 (1)
# 6 的右侧有 1 个更小的元素 (1)
# 1 的右侧有 0 个更小的元素


# 思路： O(NlogN)算法沿用count of Smaller numbers 和 count of smaller numbers before self。
# 还是用线段树求解，但是这个题目有负数的情况， 解决方法很简单，就是:
# 求得max和min之后，把整个数组解空间往右shift min位(min<0时)就可以了。
# 注意如果min > 0, 那么没必要shift; 那么segment tree的class一点都不需要改变，
# 只需要把sum的代码，每次+ Math.abs(min);把before的代码稍微改改就可以用了。
# 由题需逆着sum，故list add的时候是add(0,sum) – deque：addFirst;

# 通过64%
class SegmentTree:
    def __init__(self, start, end, sum=0):
        self.start, self.end, self.sum = start, end, sum
        self.left, self.right = None, None

    # 模板の改：去掉初始化用的array->初始化各结点.sum=0
    @classmethod
    def build(cls, start, end):
        if start > end: return None
        if start == end:
            return SegmentTree(start, end)
        node = SegmentTree(start, end)
        mid = start + end >> 1
        node.left = cls.build(start, mid)
        node.right = cls.build(mid + 1, end)
        node.sum = node.left.sum + node.right.sum
        return node

    @classmethod
    def modify(cls, root, idx, val=1):  # 又: add()
        if not root: return None
        if root.start == root.end:
            root.sum = val
            return
        mid = root.start + root.end >> 1
        if root.start <= idx <= mid:
            cls.modify(root.left, idx, val)
        if mid + 1 <= idx <= root.end:
            cls.modify(root.right, idx, val)
        # 法2：
        # if idx <= mid: cls.modify(root.left, idx, val)
        # else: cls.modify(root.right, idx, val)
        root.sum = root.left.sum + root.right.sum

    @classmethod
    def query(cls, root, start, end):
        # if start > end or end <= 0: return 0
        if root.start == start and root.end == end:
            return root.sum
        mid = root.start + root.end >> 1
        sum_lf, sum_rt = 0, 0  # ↓ 小区间
        if start <= mid: sum_lf = cls.query(root.left, start, min(mid, end))
        if mid < end: sum_rt = cls.query(root.right, max(mid + 1, start), end)
        return sum_lf + sum_rt
        # 法2：
        # if end <= mid: return cls.query(root.left, start, end)
        # if mid < start: return cls.query(root.right, start, end)
        # return cls.query(root.left, start, mid) + \
        #        cls.query(root.right, mid + 1, end)


class Solution:
    def countSmaller(self, nums):
        rst = []
        if not nums: return rst
        min_v, max_v = min(nums), max(nums)
        min_v = min(min_v, 0)  # 若min_v < 0, 则保持；若min>0, 则重置0
        root = SegmentTree.build(0, max_v - min_v + 1)
        # 逆序：从后向前query，注意负数情况！注意每轮答案插入rst首部！
        for inv in range(len(nums) - 1, -1, -1):  # [len-1: -1: 0]
            shift = abs(min_v)
            if nums[inv] == min_v:
                rst.insert(0, 0)  # ∵没有比min更小的数
            else:  # 考虑负数：解空间往右shift min位(min_v<0时，否则min=0无需shift)
                # why -1? -- 搜索区间num以左（求比num小的数个数），即[0, idx_num-1]
                rst.insert(0, SegmentTree.query(root, 0, nums[inv] + shift - 1))
            SegmentTree.modify(root, nums[inv] + shift, 1)  # modify/add操作需要在query之后！
        return rst

# public class Solution {
#     /**
#      * @param nums: a list of integers
#      * @return: return a list of integers
#      */
#     public List<Integer> countSmaller(int[] A) {
#         List<Integer> list = new ArrayList<Integer>();
#         if(A == null || A.length == 0) return list;
#         int max = getMax(A);
#         int min = getMin(A);
#         min = Math.min(min, 0); // 注意如果min > 0, 那么没必要shift;
#         SegmentTree tree = new SegmentTree(max - min + 1);
#         for(int i = A.length - 1; i >= 0 ; i--) {
#             if(A[i] == min) { // 比min小的数没有了，所以是0；
#                 list.add(0, 0);
#             } else {
#                 list.add(0, tree.querySum(0, A[i]-1 + Math.abs(min)));
#             }
#             tree.add(A[i] + Math.abs(min), 1);
#         }
#         return list;
#     }
#
#     private int getMax(int[] A) {
#         int max = Integer.MIN_VALUE;
#         for(int i = 0; i < A.length; i++) {
#             max = Math.max(max, A[i]);
#         }
#         return max;
#     }
#
#     private int getMin(int[] A) {
#         int min = Integer.MAX_VALUE;
#         for(int i = 0; i < A.length; i++) {
#             min = Math.min(min, A[i]);
#         }
#         return min;
#     }
#
#     private class SegmentTreeNode {
#         public int sum;
#         public int start;
#         public int end;
#         public SegmentTreeNode left, right;
#         public SegmentTreeNode(int start, int end) {
#             this.start = start;
#             this.end = end;
#             this.sum = 0;
#             this.left = null;
#             this.right = null;
#         }
#     }
#
#     private class SegmentTree {
#         private SegmentTreeNode root;
#
#         private SegmentTreeNode buildTree(int start, int end) {
#             SegmentTreeNode node = new SegmentTreeNode(start, end);
#             if(start == end) {
#                 return node;
#             }
#
#             int mid = start + (end - start) / 2;
#             node.left = buildTree(start, mid);
#             node.right = buildTree(mid+1, end);
#             node.sum = node.left.sum + node.right.sum;
#             return node;
#         }
#
#         private void add(SegmentTreeNode node, int index, int value) {
#             if(node.start == node.end && node.end == index){
#                 node.sum += value;
#                 return;
#             }
#
#             int mid = node.start + (node.end - node.start) / 2;
#             if(node.start <= index && index <= mid){
#                 add(node.left, index, value);
#             }
#             if(mid+1 <= index && index <= node.end) {
#                 add(node.right, index, value);
#             }
#
#             node.sum = node.left.sum + node.right.sum;
#         }
#
#         private int querySum(SegmentTreeNode node, int start, int end) {
#             if(node.start == start && node.end == end) {
#                 return node.sum;
#             }
#
#             int mid = node.start +(node.end - node.start) / 2;
#             int leftsum = 0, rightsum = 0;
#             if(start <= mid) {
#                 leftsum = querySum(node.left, start, Math.min(mid, end));
#             }
#             if(end > mid) {
#                 rightsum = querySum(node.right, Math.max(mid+1, start), end);
#             }
#             return leftsum + rightsum;
#         }
#
#         public SegmentTree(int size) {
#             root = buildTree(0, size-1);
#         }
#
#         public int querySum(int start, int end) {
#             return querySum(root, start, end);
#         }
#
#         public void add(int index, int value) {
#             add(root, index, value);
#         }
#     }
# }
