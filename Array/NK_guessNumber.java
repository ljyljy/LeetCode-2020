package Array;

import java.util.Scanner;

public class NK_guessNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextInt()) {
            int pick = sc.nextInt();
            int num = sc.nextInt(); // ²Â²âÊý×Ö
            guessNum(pick, num);
        }
    }

    private static void guessNum(int pick, int num) {
        if (pick == num) {
            System.out.println(0);
        } else if (pick > num) {
            System.out.println(1);
        } else System.out.println(-1);
    }
}
