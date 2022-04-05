package Sort.Quick_Sort;

import java.util.Arrays;

public class qo_45_arr2minNumber {
    // ���q179, qo_45
    // ��1���ַ��� �Ƚ���Comparator
    public String minNumber0(int[] nums) {
        int n = nums.length;
        String[] numStrs = new String[n];
        for (int i = 0; i < n; i++) {
            numStrs[i] = nums[i] + "";
        }
        Arrays.sort(numStrs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1)); // "10 2" < "2 10", ����
        return String.join("", numStrs);
    }

    // ��2������
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

    private int partition(String[] nums, int start, int end) { // �ַ�������
        String pivot = nums[start]; // "10"
        int i = start + 1, j = end; // "2"
        while (i <= j) { // ��Ҫ����
            while (i <= j && (pivot + nums[i]).compareTo(nums[i] + pivot) > 0) i++;
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
}
