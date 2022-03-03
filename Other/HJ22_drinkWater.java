package Other;

import java.util.Scanner;

public class HJ22_drinkWater {
    // 法1：两瓶可以暂借一空瓶，得新的一瓶 -> 俩瓶子 -> 可喝一瓶 -> 直接n/2即可
    public static void main01(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n > 0)
                System.out.println(n / 2);
        }
    }

    // 法2：常规
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) continue;

            int cnt = 0;
            while (n >= 3) {
                int tmp = n / 3; // 本轮可兑换的新瓶数
                cnt += tmp;
                n = n % 3 + tmp; // 剩余瓶数 + 本轮新喝完
            }
            if (n == 2) cnt += 1;
            System.out.println(cnt);
        }
    }
}
