package Other.math;

import java.util.Random;

public class q384_shuffle_an_array {
    private int[] nums;
    private int n;
    Random random = new Random(); // ❤

    public q384_shuffle_an_array(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int[] arr = nums.clone(); // √ clone
        for (int i = 0; i < n; i++) {
            swap(arr, i, i + random.nextInt(n -i));
        }
        return arr;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
