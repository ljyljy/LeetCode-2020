package Sort;

import java.util.*;

public class HJ101_sort_flag {
    // 法1：直接正序/逆序打印【技巧，推荐！】
    private static void printArr(int[] arr, int flag) {
        Arrays.sort(arr);
        int n = arr.length;
        if (flag == 0) {// 0:升序；1：降序
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = n-1; i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
//             // 法3：swap(arr)，双指针，然后常规打印
//             // 优化V2：升序nums，然后反转【如果必须算法实现int[]的降序排列】
//             for (int i = 0, j = n-1; i < j; i++, j--) {
//                 int tmp = nums[i];
//                 nums[i] = nums[j];
//                 nums[j] = tmp;
//             }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int flag = sc.nextInt(); // 0:升序；1：降序

            printArr(arr, flag); // 法1
        }
    }

    // 法2：常规-正序/逆序排序Integer[]?(不能是int[])，正常打印
    private static void printArr2(Integer[] arr, int flag) {
        if (flag == 0) {
            Arrays.sort(arr);
        } else {
            // 必须是Integer[], 基本的int[]没有降序，也不能使用比较器！?
            Arrays.sort(arr, (o1, o2) -> (o2-o1)); // 降序
//             Arrays.sort(arr, Collections.reverseOrder());  // List
        }

        int n = arr.length;
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            Integer[] arr = new Integer[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int flag = sc.nextInt(); // 0:升序；1：降序

            printArr2(arr, flag); // 法2
        }
    }


}