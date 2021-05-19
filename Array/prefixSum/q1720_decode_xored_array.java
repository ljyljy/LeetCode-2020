package Array.prefixSum;

public class q1720_decode_xored_array {
    public int[] decode(int[] encoded, int first) {
        // encoded[i-1] XOR arr[i-1] = arr[i]
        int n = encoded.length + 1;
        int[] arr = new int[n];
        arr[0] = first;
        for (int i = 1; i < n; i++) {
            arr[i] = encoded[i-1] ^ arr[i-1];
        }
        return arr;
    }
}
