package Array.prefixSum;

public class q238_product_of_array_except_self {
    // ��2���ռ�O(1)
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        // ����ǰ׺��prod_L
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i-1] * nums[i-1]; // ��res[1]��ʼ����nums[0]
        }
        // �����׺��prod_R
        int prod_R = 1;
        for (int i = n-1; i >= 0; i--) {
            res[i] = res[i] * prod_R; // ��res[n-2]��ʼ����nums[n-1]
            prod_R *= nums[i];
        }
        return res;
    }
    // ��1:����ǰ׺�� & ��׺��
    public int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] prod_L = new int[n+1], prod_R = new int[n+1];

        prod_L[0] = prod_R[n] = 1;
        for (int i = 1; i <= n; i++) {
            prod_L[i] = prod_L[i-1] * nums[i-1];
        }
        for (int i = n-1; i >= 0; i--) {
            prod_R[i] = nums[i] * prod_R[i+1]; // ע���±꣡
        }

        // for (int num: prod_L) System.out.print(num + "\t");
        // System.out.println();
        // for (int num: prod_R) System.out.print(num + "\t");

        for (int i = 0; i < n; i++) {
            res[i] = prod_L[i] * prod_R[i+1]; // ע���±�:prod_L[0:n-1], prod_R[1:n]��
        }
        return res;
    }


}
