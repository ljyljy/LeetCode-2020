package Bit;

public class q1442_count_triplet_equal_xor {
    // 法2：O(n^2) -- 当S[i]==S[k+1]时，[i+1,k] 的范围内的任意j 都是符合要求的，
    //     对应的三元组个数为 k-i。因此只需枚举下标 i 和 k
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int[] prefix_XOR = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            prefix_XOR[i] = prefix_XOR[i-1] ^ arr[i-1];
        }
        int cnt = 0;
        // a = S[i] ^ S[j], b = S[j] ^ S[k+1]
        //  -> a == b ->  S[i] == S[k+1]
        for (int i = 0; i < n; i++) {
            for (int k = i+1; k < n; k++) { //k从i+1起！∵ i < j <= k
                if (prefix_XOR[i] == prefix_XOR[k+1])
                    cnt += (k - i); // j ∈ (i, k], 共k-i个
            }
        }
        return cnt;
    }

    // 法1：O(n^3)
    public int countTriplets1(int[] arr) {
        int n = arr.length;
        int[] prefix_XOR = new int[n+1];
        for (int i = 1; i < n+1; i++) {
            prefix_XOR[i] = prefix_XOR[i-1] ^ arr[i-1];
        }
        int cnt = 0;
        // a = S[i] ^ S[j], b = S[j] ^ S[k+1]
        //  -> a == b ->  S[i] == S[k+1]
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) { //j从i+1起！∵ i < j
                for (int k = j; k < n; k++) {// k从j起！∵ j <= k
                    if (prefix_XOR[i] == prefix_XOR[k+1])
                        cnt++;
                }
            }
        }
        return cnt;
    }
}
