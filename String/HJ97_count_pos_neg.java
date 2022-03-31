package String;


import java.util.Scanner;

public class HJ97_count_pos_neg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            calculate(n, arr);
        }
        sc.close();
    }

    private static void calculate(int num, int[] arr) {
        int cntNeg = 0;
        int cntPos = 0;
        int sum = 0;
        for (int i = 0; i < num; i++) {
            if (arr[i] < 0) {
                cntNeg++;
            }
            if (arr[i] > 0) {
                cntPos++;
                sum += arr[i];
            }
        }
        System.out.print(cntNeg + " ");
        if (cntPos == 0) {
            System.out.printf("0.0");
        }
        else {
            System.out.printf("%.1f", (float) sum / cntPos); // 【带格式输出v1】
//        System.out.format("%.1f", (float) sum / cntPos); // 【带格式输出v2】
        }
    }
}