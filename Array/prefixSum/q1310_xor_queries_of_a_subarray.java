package Array.prefixSum;

public class q1310_xor_queries_of_a_subarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] preXOR = new int[n+1]; //"前缀异或"
        for (int i = 1; i < n+1; i++) {
            preXOR[i] = arr[i-1] ^ preXOR[i-1];
        }
        int[] res = new int[queries.length];
        int i = 0;
        for (int[] query : queries) {
            int idx1 = query[0], idx2 = query[1];
            res[i++] = preXOR[idx2 + 1] ^ preXOR[idx1];
        }
        return res;
    }
}
