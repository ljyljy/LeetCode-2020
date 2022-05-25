package Sort.Bucket_Sort;

public class q324_wiggle_sort_ii {
    public void wiggleSort(int[] nums) {
        int[] bucket = new int[5001]; // nums[i]∈[0,5000]
        for (int num : nums) bucket[num]++;
        int n = nums.length;
        int smallEnd, bigEnd;// 穿插数字时的上界
        // 总长度为奇数时，“小 大 小 大 小”边界左右都为较小的数；
        // 总长度为偶数时，“小 大 小 大”边界左为较小的数，边界右为较大的数
        if ((n & 1) == 1) { // n % 2 == 1, 奇数
            smallEnd = n - 1;
            bigEnd = n - 2;
        } else {
            bigEnd = n - 1;
            smallEnd = n - 2;
        }

        int idx = 5000; // 从后往前，将桶中数字穿插到数组中，上界为idx
        // 桶中大的数字在后面，小的数字在前面，所以先取出较大的数字，再取出较小的数字
        // 先将桶中的【大数穿插(i+=2)】放在nums中
        for (int i = 1; i <= bigEnd; i+=2) {
            while (bucket[idx] == 0) idx--; // 桶遍历步长为1，而非2！
            nums[i] = idx; // 计数排序，bucket下标idx就是具体元素nums[i]
            bucket[idx]--;
        }
        //再将桶中的【小数穿插(i+=2)】放在nums中
        for (int i = 0; i <= smallEnd; i+=2) {
            while (bucket[idx] == 0) idx--;// 桶遍历步长为1，而非2！
            nums[i] = idx;
            bucket[idx]--;
        }
    }
}
