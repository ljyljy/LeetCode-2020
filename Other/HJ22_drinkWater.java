package Other;

import java.util.Scanner;

public class HJ22_drinkWater {
    // ��1����ƿ�����ݽ�һ��ƿ�����µ�һƿ -> ��ƿ�� -> �ɺ�һƿ -> ֱ��n/2����
    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n > 0)
                System.out.println(n / 2);
        }
    }

    // ��2������
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) continue;

            int cnt = 0;
            while (n >= 3) {
                int tmp = n / 3; // ���ֿɶһ�����ƿ��
                cnt += tmp;
                n = n % 3 + tmp; // ʣ��ƿ�� + �����º���
            }
            if (n == 2) cnt += 1;
            System.out.println(cnt);
        }
    }
}
