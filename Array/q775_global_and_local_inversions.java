package Array;

public class q775_global_and_local_inversions {
    // ת����
    //�����ڷǾֲ�����ָ���� nums[i] > nums[j]������ i < j �C 1���򷵻�false��
    //  ά��ǰ׺���ֵ:
    //  	����j��ͬʱ��¼��ǰ׺����(j��[2,n-1], i��[0,j-2])��
    //     	������Max(nums[i],��, nums[j-2]) > nums[j]���򷵻�false
    // 	ά����׺��Сֵ:
    //  	����i��ͬʱ��¼���׺����(i��[0,n-3], j��[i+2,n-1])���������������
    //  	������nums[i] > min(nums[i+2],...,nums[n-1])���򷵻�false

    // ����1-2��ά����׺��Сֵ
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        int suffixMin = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            suffixMin = Math.min(suffixMin, nums[i + 2]);
            if (nums[i] > suffixMin) {
                return false;
            }
        }
        return true;
    }
}

/*
    // ��1-1��ά��ǰ׺���ֵ(C)
    bool isIdealPermutation(int* nums, int n) {
        int preMax = 0;
        for (int j = 2; j < n; j++) {
            preMax = fmax(preMax, nums[j - 2]);
            if (preMax > nums[j]) {
                return false;
            }
        }
        return true;
    }
*/