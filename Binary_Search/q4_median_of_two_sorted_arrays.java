package Binary_Search;

public class q4_median_of_two_sorted_arrays {
    // 时间O(log(m+n)) - 二分查找（but二分排序是NlogN，勿混淆！）
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int N = m + n;
        // 俩升序的中位数 <=> 第k小, k=N/2(奇)或中间俩数 [再求平均(偶)]
        if (N % 2 != 0) { // 奇数 - 中位数第N/2个 (len=3, 中位数=2nd=N/2+1)
            int k = N/2+1; // 是第几个！而非idx! 无需-1！！
            return getKth(A, 0, m-1, B, 0, n-1, k);
        }else { // 如: N=4, 中位数为第2,3的均值
            int k1 = N/2, k2 = N/2+1; // k1、k2分别为中间靠左、右
            double sum_L = getKth(A, 0, m-1, B, 0, n-1, k1);
            double sum_R = getKth(A, 0, m-1, B, 0, n-1, k2);
            return (sum_L + sum_R) * 0.5; // 或/2.0(double!)
        }
    }

    private double getKth(int[] A, int L1, int R1,
                          int[] B, int L2, int R2, int k) {
        int len1 = R1 - L1 + 1, len2 = R2 - L2 + 1;
        // 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2)
            return getKth(B, L2, R2, A, L1, R1, k);
        // A数组排除完毕（前提：确保A短于B↑）
        if (len1 == 0) return B[L2 + k - 1]; // L2为offset, idx需-1
        // 已经找到第k小的数(俩数组中第k=1小的数)
        if (k == 1) return Math.min(A[L1], B[L2]);
        // 【边界完毕↑】 开始二分（每次最多排除k/2个结点）↓
        int c1 = L1 + Math.min(k / 2, len1) - 1; // 下一轮的中点
        int c2 = L2 + Math.min(k / 2, len2) - 1;
        if (A[c1] <= B[c2]) { // A[L1:c1]不可能有解-> 剪枝
            int newK = k - (c1 - L1 + 1);  // k/2 # 错！因为k/2可能超出A的下标！越界！
            return getKth(A, c1 + 1, R1, B, L2, R2, newK);
        } else { // B[L2:c2]不可能有解-> 剪枝
            int newK = k - (c2 - L2 + 1);
            return getKth(A, L1, R1, B, c2+1, R2, newK);
        }

    }
}
