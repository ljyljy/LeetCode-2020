package Sort.Quick_Sort;

import java.util.Scanner;

public class quickSort_test {
    public void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;
        int mid = partition_v1(arr, start, end);

        quickSort(arr, start, mid);
        quickSort(arr, mid+1, end);
    }

    private int partition_v1(int[] arr, int start, int end) {
        if (start >= end) return start;
        int pivot = arr[start];
        int i = start+1, j = end;
        while (i <= j) {
            while (i <= j && arr[i] < pivot) i++;
            while (i <= j && arr[j] > pivot) j--;
            if (i < j) {
                swap(arr, i, j);
            }
        }
        swap(arr, start, j);
        return j;
    }

    private void swap (int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            // System.out.println(arr[i]);
        }

        quickSort_test sol = new quickSort_test();
        sol.quickSort(arr, 0, n-1);
        for (int num: arr)
            System.out.print(num+" ");
    }
}
