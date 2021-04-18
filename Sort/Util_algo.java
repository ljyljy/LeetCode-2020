package Sort;

import java.util.Arrays;

public class Util_algo {

    /**
     * 获取指定范围指定个数的随机数组成的数组
     *
     * @param length
     * @param min
     * @param max
     * @return
     */
    public static int[] getRandomArr(int length, int min, int max) {
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = (int) (Math.random() * (max + 1 - min) + min);
        }
        return arr;
    }


    /**
     * 在数组内原址交换元素
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void print(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }


}
