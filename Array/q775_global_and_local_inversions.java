package Array;

public class q775_global_and_local_inversions {
    // 转换：
    //若存在非局部倒置指的是 nums[i] > nums[j]，其中 i < j – 1，则返回false。
    //  维护前缀最大值:
    //  	遍历j，同时记录其前缀数组(j∈[2,n-1], i∈[0,j-2])，
    //     	若存在Max(nums[i],…, nums[j-2]) > nums[j]，则返回false
    // 	维护后缀最小值:
    //  	遍历i，同时记录其后缀数组(i∈[0,n-3], j∈[i+2,n-1])，故需逆序遍历。
    //  	若存在nums[i] > min(nums[i+2],...,nums[n-1])，则返回false

    // 方法1-2：维护后缀最小值
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
    // 法1-1：维护前缀最大值(C)
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