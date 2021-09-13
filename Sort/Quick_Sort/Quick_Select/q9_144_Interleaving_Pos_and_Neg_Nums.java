package Sort.Quick_Sort.Quick_Select;

public class q9_144_Interleaving_Pos_and_Neg_Nums {
    // 结果ret：将多数者放于首尾！
    // 1) 负多1：-+(L=1),-+,-+,-+,-(R=n-1)
    // 2) 正多1：+(L=0)-,+-,+-,+-(R=n-2),+
    // 3) 正负等: -(L=0)+,-+,-+(R=n-1)
    public void rerange(int[] A) {
        int n = A.length; // 例：(负多1个)[-1, -2, -3, 4, 5, 6, -7]
        int negCnt = partition(A), posCnt = n - negCnt;
        // step1:分区(by快选) -- A' = [-1,-2,-3,-7,5,6,4], negCnt = 4 ↓
//        for (int num: A) System.out.print(num + " ");
//        System.out.println("\n negCnt = " + negCnt);
        int L = negCnt > posCnt? 1: 0;
        int R = negCnt >= posCnt? n-1: n-2;
        interleave(A, L, R); // step2: A'交叉互换
    }

    private void interleave(int[] A, int L, int R) {
        while (L <= R) {
            swap(A, L, R);
            // 间隔互换（skip=2）
            L += 2; R -= 2;
        }
    }

    private int partition(int[] A) {
        int left = 0, right = A.length - 1;
        while (left <= right) {
            while (left <= right && A[left] < 0)
                left++;
            while (left <= right && A[right] > 0)
                right--;
            if (left <= right) {
                swap(A, left, right);
                left++; right--;
            }
        } // 退出后，R< L(=R+1)
        return left; // 或 right+1
    }

    private void swap(int[] A, int left, int right) {
        int tmp = A[left];
        A[left] = A[right];
        A[right] = tmp;
    }
}
