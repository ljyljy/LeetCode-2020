package Array;

import java.util.Arrays;

public class qo_61_isStraight {
    public boolean isStraight(int[] nums) {
        int n = 5; // nums.length;
        Arrays.sort(nums);
        int k = 0;
        while (k < n && nums[k] == 0) k++; // �˳�ʱ��kָ���׸���Ϊ��Ԫ��
        for (int i = k; i+1 < n; i++) {
            if (nums[i] == nums[i+1])
                return false;
        }
        return nums[n-1] - nums[k] <= 4;// ��������棬max-min<=4������
    }
}
