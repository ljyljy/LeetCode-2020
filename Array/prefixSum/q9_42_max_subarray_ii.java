package Array.prefixSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ��ȣ�q53 -> �ϳ��ӡ�q9_42
public class q9_42_max_subarray_ii {
    // �������ң���������ֱ�������������ͣ�Ȼ��ˢһ���������飬�ҵ���ѵĻ���λ�ã����Ӷ�n+n+n
    // �� ��ȣ��ϳ���
    private int[] dp_L, dp_R;
    private int n;

    public int maxTwoSubArrays(List<Integer> numss) {
        n = numss.size();
        if (n == 2) return numss.get(0) + numss.get(1);
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = numss.get(i);
        }
        System.out.println(Arrays.toString(nums));
        getMaxSum_LR(nums);

        int maxSum = Integer.MIN_VALUE;
        for (int split = 1; split < n; split++) {
            // �������[0:split) + �Ҳ�����[split:n)
            maxSum = Math.max(maxSum, dp_L[split - 1] + dp_R[split]);
        }
        return maxSum;
    }

    // �������ң���������ֱ��������������
    // ����д����ǰ׺�ͣ��ԣ�
    private void getMaxSum_LR(int[] nums) {
        dp_L = new int[n]; // ������ ����������
        dp_R = new int[n]; // ������ ����������
        dp_L[0] = nums[0];
        dp_R[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
//            dp_L[i] = Math.max(nums[i], dp_L[i-1] + nums[i]);
            dp_L[i] = Math.max(nums[i], dp_L[i - 1] + nums[i]); // q53��[0,i]����������������
        }
        // ?��ȫ��[0:i]��ǰ���������ͣ����q9_43
        for (int i = 1; i < n; i++) {
            dp_L[i] = Math.max(dp_L[i], dp_L[i - 1]); // [0,i]��dp_LĿǰ�����ֵ
        }

        System.out.println("dp_L: ");
        System.out.println(Arrays.toString(dp_L));

        // ?��ȫ��[i:n)��ǰ���������ͣ����q9_43
        for (int i = n - 2; i >= 0; i--) {
            dp_R[i] = Math.max(nums[i], dp_R[i + 1] + nums[i]);// q53����: [i,n]����������������
        }

        for (int i = n - 2; i >= 0; i--) {
            dp_R[i] = Math.max(dp_R[i], dp_R[i + 1]); // [i,n]��dp_RĿǰ�����ֵ
        }

        System.out.println("dp_R: ");
        System.out.println(Arrays.toString(dp_R));
    }

    public static void main(String[] args) {
        System.out.println("exp01:");
        List<Integer> numss = new ArrayList<>();
        int[] nums = new int[]{1, 3, -1, 2, -1, 2};
        for (int num : nums) {
            numss.add(num);
        }
        q9_42_max_subarray_ii sol = new q9_42_max_subarray_ii();
        System.out.println(sol.maxTwoSubArrays(numss));

        System.out.println("exp02:");
        List<Integer> numss2 = new ArrayList<>();
        int[] nums2 = new int[]{-1, -2, -3, -100, -1, -50};
        for (int num : nums2) {
            numss2.add(num);
        }
        System.out.println(sol.maxTwoSubArrays(numss2));
    }
}
