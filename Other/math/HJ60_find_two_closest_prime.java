package Other.math;

import java.util.Scanner;

/**
 * 首先输入的肯定是偶数，从中间开始找的话就是保证两个数差距最小，然后再分别判断两个数是不是质数，就可以了
 */
public class HJ60_find_two_closest_prime {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] res = findPrime(n);
            System.out.println(res[0]);
            System.out.println(res[1]);
        }
    }

    private static int[] findPrime(int n) {
        int half = n / 2;
        for (int i = 0; i < half; i++) {
            if (isPrime(half-i) && isPrime(half+i)) {
                return new int[]{half-i, half+i};
            }
        }
        return new int[]{-1, -1};
    }

    private static boolean isPrime(int num) { // 2, 3, 5,...
        for (int i = 2; i*i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}