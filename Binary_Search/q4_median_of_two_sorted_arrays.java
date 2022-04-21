package Binary_Search;

public class q4_median_of_two_sorted_arrays {
    // 图文并茂[解法3-最优]：
    //   https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/

    // 时间O(log(m+n)) - 递归/分治
    public double findMedianSortedArrays(int[] A, int[] B) {
        int N = A.length + B.length;
        if (N % 2 != 0) { // 奇数 - 中位数第N/2个 (len=3, 中位数=2nd,idx+1
            return getKth(A, 0, B, 0, N / 2 + 1);
        } else { // 如: N=4, 中位数为第2,3的均值
            double sum1 = getKth(A, 0, B, 0, N / 2);
            double sum2 = getKth(A, 0, B, 0, N / 2 + 1);
            return (sum1 + sum2) * 0.5;
        }
    }

    private double getKth(int[] A, int i, int[] B, int j, int k) {
        int len1 = A.length - i, len2 = B.length - j;
        if (len1 > len2) return getKth(B, j, A, i, k);
        if (i == A.length) return B[j + k - 1]; // ①
        if (k == 1) return Math.min(A[i], B[j]); // 保小的

        int newI = Math.min(i + k/2, A.length);// ①
        int newJ = j + k/2;
        if (A[newI - 1] > B[newJ - 1]) { // B[j:j+k/2]不可能有解-> B从newJ开始
            return getKth(A, i, B, newJ, k - k/2); // 保大的，舍小的
        } else { // A[i:newI]不可能有解-> A从newI开始
            return getKth(A, newI, B, j, k - (newI - i));
        }
    }


    // 时间O(log(m+n)) - 二分查找（but二分排序是NlogN，勿混淆！）
    public double findMedianSortedArrays01(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int N = m + n;
        // 俩升序的中位数 <=> 第k小, k=N/2(奇)或中间俩数 [再求平均(偶)]
        if (N % 2 != 0) { // 奇数 - 中位数第N/2个 (len=3, 中位数=2nd,idx+1)
            int k = N/2+1;
            return getKth01(A, 0, m-1, B, 0, n-1, k);
        }else { // 如: N=4, 中位数为第2,3的均值
            int k1 = N/2, k2 = N/2+1; // k1、k2分别为中间靠左、右
            double sum_L = getKth01(A, 0, m-1, B, 0, n-1, k1);
            double sum_R = getKth01(A, 0, m-1, B, 0, n-1, k2);
            return (sum_L + sum_R) * 0.5; // 或/2.0(double!)
        }
    }

    private double getKth01(int[] A, int L1, int R1,
                            int[] B, int L2, int R2, int k) {
        int len1 = R1 - L1 + 1, len2 = R2 - L2 + 1;
        // 让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2)
            return getKth01(B, L2, R2, A, L1, R1, k);
        // A数组排除完毕（前提：确保A短于B↑）
        if (len1 == 0) return B[L2 + k - 1]; // L2为offset, idx需-1
        // 已经找到第k小的数(俩数组中第k=1小的数)
        if (k == 1) return Math.min(A[L1], B[L2]);
        // 【边界完毕↑】 开始二分（每次最多排除k/2个结点）↓
        int c1 = L1 + Math.min(k / 2, len1) - 1; // 下一轮的中点
        int c2 = L2 + Math.min(k / 2, len2) - 1;
        if (A[c1] <= B[c2]) { // A[L1:c1]不可能有解-> 剪枝
            int newK = k - (c1 - L1 + 1);  // k/2 # 错！因为k/2可能超出A的下标！越界！
            return getKth01(A, c1 + 1, R1, B, L2, R2, newK);
        } else { // B[L2:c2]不可能有解-> 剪枝
            int newK = k - (c2 - L2 + 1);
            return getKth01(A, L1, R1, B, c2+1, R2, newK);
        }

    }
}
