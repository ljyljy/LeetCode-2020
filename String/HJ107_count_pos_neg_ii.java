package String;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HJ107_count_pos_neg_ii {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> nums = new ArrayList<>();
        while (sc.hasNext()) {
            nums.add(sc.nextInt());
        }
        sc.close();
        calculate(nums);
    }

    private static void calculate(List<Integer> nums) {
        int cntNeg = 0;
        int cntPos = 0;
        int sum = 0;
        for (int num: nums) {
            if (num < 0) cntNeg++;
            if (num > 0) {
                cntPos++;
                sum += num;
            }
        }
        System.out.println(cntNeg);
        if (cntPos == 0) {
            System.out.println("0.0");
        }
        else {
            System.out.printf("%.1f\n", (float) sum / cntPos); // 【带格式输出v1】
//        System.out.format("%.1f", (float) sum / cntPos); // 【带格式输出v2】
        }
    }
}