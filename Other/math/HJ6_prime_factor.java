package Other.math;

import java.util.Scanner;

public class HJ6_prime_factor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long num = sc.nextLong();
        for (long i = 2; i <= (long)Math.sqrt(num); i++) {
            while (num % i == 0) { // while������һֱ����i=2/3/...��
                System.out.print(i + " ");
                num /= i; // �ֽ�������
            }
        }
        System.out.println(num == 1 ? "": num + " ");
    }
}
