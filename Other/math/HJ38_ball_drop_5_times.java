package Other.math;

import java.util.Scanner;

public class HJ38_ball_drop_5_times {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 5;
        while (sc.hasNext()) {
            double totalH = sc.nextInt();
            double curH = totalH;
            for (int i = 2; i <= n; i++) { // ģ�⣺��[2,5]��
                curH /= 2;
                totalH += curH * 2; // һ̧һ��
            }
            System.out.println(totalH);
            System.out.println(curH / 2); // ��һ������߶�
        }
    }
}

