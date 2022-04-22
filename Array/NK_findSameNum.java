package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NK_findSameNum {
    public static void main(String[] args) {
        int[] A = new int[]{0, 1, 4, 10};
        int[] B = new int[]{1, 8, 10};
        List<Integer> sames = findSame(A, B);
        for (int num: sames) {
            System.out.print(num + " ");
        }
    }

    private static List<Integer> findSame(int[] A, int[] B) {
        List<Integer> res = new ArrayList<>();
        Arrays.sort(A);
        Arrays.sort(B);
        int n1 = A.length, n2 = B.length;
        int i = 0, j = 0;

        while (i < n1 && j < n2) {
            if (A[i] == B[j]) {
                res.add(A[i]);
                i++; j++;
            } else if (A[i] < B[j]) {
                i++;
            } else j++;
        }
        return res;
    }
}
