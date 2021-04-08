package Array.MergeSort_array;

public class q88_merge_sorted_array {
    public void merge(int[] A, int m, int[] B, int n) {
        int idx = m + n - 1, pA = m - 1, pB = n - 1;
        while (pA >= 0 && pB >= 0) {
            if (A[pA] > B[pB])
                A[idx--] = A[pA--];
            else A[idx--] = B[pB--];
        }
        while (pA >= 0) A[idx--] = A[pA--];
        while (pB >= 0) A[idx--] = B[pB--];
    }

    public static void main(String[] args) {
        int[] A = {1,2,3,0,0,0};
        int[] B = {2,5,6};
        q88_merge_sorted_array sol = new q88_merge_sorted_array();
        sol.merge(A, A.length - B.length, B, B.length);
        for (int a: A)
            System.out.print(a + " ");
    }
}
