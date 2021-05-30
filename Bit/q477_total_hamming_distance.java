package Bit;

public class q477_total_hamming_distance {
    // 法2：O(31n) -- O(n)
    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        for (int x = 31; x >= 0; x--) {
            int s0 = 0, s1 = 0;
            for (int u : nums) {
                if (((u >> x) & 1) == 1) {
                    s1++;
                } else {
                    s0++;
                }
            }
            // System.out.println("s0="+s0+", s1="+s1+", s0*s1 = "+s0*s1);
            ans += s0 * s1;
        }
        return ans;
    }

    // 法1：TLE(与q461同解法)
    public int totalHammingDistance1(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int xor = nums[i] ^ nums[j];
                while (xor != 0) {
                    int bit = xor & 1;
                    cnt += bit;
                    xor >>= 1;
                }
            }
        }
        return cnt;
    }
}
