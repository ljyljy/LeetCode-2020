package Sort;

import java.util.*;

public class HJ101_sort_flag {
    // ��1��ֱ������/�����ӡ�����ɣ��Ƽ�����
    private static void printArr(int[] arr, int flag) {
        Arrays.sort(arr);
        int n = arr.length;
        if (flag == 0) {// 0:����1������
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + " ");
            }
        } else {
            for (int i = n-1; i >= 0; i--) {
                System.out.print(arr[i] + " ");
            }
//             // ��3��swap(arr)��˫ָ�룬Ȼ�󳣹��ӡ
//             // �Ż�V2������nums��Ȼ��ת����������㷨ʵ��int[]�Ľ������С�
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
            int flag = sc.nextInt(); // 0:����1������

            printArr(arr, flag); // ��1
        }
    }

    // ��2������-����/��������Integer[]?(������int[])��������ӡ
    private static void printArr2(Integer[] arr, int flag) {
        if (flag == 0) {
            Arrays.sort(arr);
        } else {
            // ������Integer[], ������int[]û�н���Ҳ����ʹ�ñȽ�����?
            Arrays.sort(arr, (o1, o2) -> (o2-o1)); // ����
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
            int flag = sc.nextInt(); // 0:����1������

            printArr2(arr, flag); // ��2
        }
    }


}