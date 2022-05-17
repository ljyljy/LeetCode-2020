package Array.prefixSum;

public class q238_product_of_array_except_self {
    // 法2：空间O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // 先求前缀积prod_L
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1]; // 从res[1]开始考虑nums[0]
        }
        // 再求后缀积prod_R
        int prod_R = 1;
        for (int i = n-1; i >= 0; i--) {
            res[i] = res[i] * prod_R; // 从res[n-2]开始考虑nums[n-1]
            prod_R *= nums[i];
        }
        return res;
    }
    // 法1:常规前缀积 & 后缀积
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] prod_L = new int[n+1], prod_R = new int[n+1];

        prod_L[0] = prod_R[n] = 1;
        for (int i = 1; i <= n; i++) {
            prod_L[i] = prod_L[i-1] * nums[i-1];
        }
        for (int i = n-1; i >= 0; i--) {
            prod_R[i] = nums[i] * prod_R[i+1]; // 注意下标！
        }

        // for (int num: prod_L) System.out.print(num + "\t");
        // System.out.println();
        // for (int num: prod_R) System.out.print(num + "\t");

        for (int i = 0; i < n; i++) {
            res[i] = prod_L[i] * prod_R[i+1]; // 注意下标:prod_L[0:n-1], prod_R[1:n]！
        }
        return res;
    }


}
