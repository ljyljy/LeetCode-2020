package Array;

import java.util.*;

public class HW0_10sum {
    private int calcDiff(int[] arr, int n) {
//        Arrays.sort(arr); // 前提：保证arr有序（放在main中了）
        int sum = Arrays.stream(arr).sum();
        int target = sum / 2;

        int i = 0, j = n-1, k = 0;
        int sum1 = 0, sum2 = 0;
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        while (i < j) {
            if (i < j && sum1 < target) {
                sum1 += arr[i];
                list1.add(arr[i++]);
            }
            if (i < j && sum1 < target) {
                sum1 += arr[j];
                list1.add(arr[j--]);
            }
            if (i < j && sum2 < target) {
                sum2 += arr[i];
                list2.add(arr[i++]);
            }
            if (i < j && sum2 < target) {
                sum2 += arr[j];
                list2.add(arr[j--]);
            }
        }
        list2.add(arr[j]);
        sum2 += arr[j];

        System.out.println(list1);
        System.out.println(list2);
        return sum1 - sum2;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 10;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
//            arr[i] = sc.nextInt();
//            arr[i] = i+1;
            arr[i] = new Random().nextInt(20);
        }
        Arrays.sort(arr);

//        for (int num: arr) {
//            System.out.print(num + " ");
//        }
//        System.out.println();
        System.out.println(Arrays.toString(arr)); // ?一维数组的快速打印Arrays.toString(int[])

        System.out.println("\ndistribution of force: ");
        HW0_10sum sol = new HW0_10sum();
        System.out.println("Diff = " + sol.calcDiff(arr, n));

//        // ?扩展：二维数组的快速打印Arrays.deepToString(int[x][])
//        int[][] arr2 = new int[][]{{1,2,3}, {4,5}, {6,7,8,9}, {10}};
//        System.out.println(Arrays.deepToString(arr2));
    }
}
