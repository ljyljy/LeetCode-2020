package Array.bit;

public class q1734_decode_xored_permutation {
    public int[] decode(int[] encoded) {
        // perm = [A, B, C, D, E]，那么encoded = [AB, BC, CD, DE]；
        int m = encoded.length; // 得到编码后的长度
        int n = m + 1;
        int[] perm = new int[n]; // 定义原本的整数数组perm
        int ABCDE = 0; // 初始化为0【恒等率】，存放perm中所有数值进行异或的结果
        for (int i = 1; i <= n; i++)
            ABCDE ^= i; // 异或的交换律
        int BC_DE = 0; // 为了得到perm的第一个数值，需要初始化一个“BCDE”为0【恒等率】
        for (int i = 1; i < n; i+=2) //非i<=n！！（encoded长度为m=n-1）
            BC_DE ^= encoded[i];
        perm[0] = ABCDE ^ BC_DE; // 得到第一个数A
        for (int i = 1; i < n; i++) //非i<=n！！perm长度为n，下标到n-1
            perm[i] = perm[i-1] ^ encoded[i-1]; // 回归解法q1720
        return perm;
    }
}
