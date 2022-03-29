package Other.math;

import java.util.Scanner;

/**
 * ��������Ŀ϶���ż�������м俪ʼ�ҵĻ����Ǳ�֤�����������С��Ȼ���ٷֱ��ж��������ǲ����������Ϳ�����
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