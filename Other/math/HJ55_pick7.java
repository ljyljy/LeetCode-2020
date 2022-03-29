package Other.math;


import java.util.Scanner;

public class HJ55_pick7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();  //  [1, 30000]
            System.out.println(pick7(num));
        }
    }

    private static int pick7(int num) {
        int cnt = 0;
        if (num < 7) return 0;
        for (int i = 7; i <= num; i++) {
            // 7的倍数 || 各个数位为7
            if (i % 7==0 || (i % 10==7)|| ((i/10) % 10==7) || ((i/100) % 10==7) || ((i/1000) % 10==7) || ((i/10000) % 10 == 7)) {
                cnt++;
            }
        }
        return cnt;
    }
}
