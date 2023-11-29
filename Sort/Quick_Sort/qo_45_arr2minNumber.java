package Sort.Quick_Sort;

import java.util.Arrays;

public class qo_45_arr2minNumber {
    // 类比q179, qo_45
    // 法1：字符串 比较器Comparator
    public String minNumber0(int[] nums) {
        int n = nums.length;
        String[] numStrs = new String[n];
        for (int i = 0; i < n; i++) {
            numStrs[i] = nums[i] + "";
        }
        Arrays.sort(numStrs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1)); // "10 2" < "2 10", 升序
        return String.join("", numStrs);
    }
    // 法2：快排
    public String minNumber(int[] nums) {
        int n = nums.length;
        String[] numStrs = new String[n];
        for (int i = 0; i < n; i++) {
            numStrs[i] = nums[i] + "";
        }
        quickSort(numStrs, 0, n - 1);
        return String.join("", numStrs);
    }

    private void quickSort(String[] numStrs, int start, int end) {
        if (start >= end) return;
        int mid = partition(numStrs, start, end);
        quickSort(numStrs, start, mid - 1);
        quickSort(numStrs, mid + 1, end);
    }

    private int partition(String[] nums, int start, int end) { // 字符串升序
        String pivot = nums[start]; // "10"
        int i = start + 1, j = end; // "2"
        while (i <= j) { // 需要升序！
            // 100 10 < 10 100, 100([i])要放在pivot前
            while (i <= j && (nums[i] + pivot).compareTo(pivot + nums[i]) < 0) i++;
            // 10  2  < 2  10, 2([j])要放在pivot后
            while (i <= j && (pivot + nums[j]).compareTo(nums[j] + pivot) < 0) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        swap(nums, j, start);
        return j;
    }

    private void swap(String[] str, int i, int j) {
        String tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }

    public static void main(String[] args) {
        qo_45_arr2minNumber obj = new qo_45_arr2minNumber();
        int[] nums = {63, 42, 13};
        System.out.println(obj.minNumber(nums));
    }
}
