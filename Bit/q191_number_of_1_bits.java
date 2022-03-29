package Bit;

import java.util.Scanner;

public class q191_number_of_1_bits {
    public int hammingWeight(int n) {
        int cnt = 0;
        while (n != 0) { // ?����>0, n����Ϊ������
            n -= lowbit(n);
            cnt++;
        }
        return cnt;
    }

    private int lowbit(int x) {
        return x & (-x);
    }


    // ��ͨ�ⷨ: ��λ��HJ62
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int num = sc.nextInt();  //  [1, 30000]
            System.out.println(getCnt(num));
        }
    }

    private static int getCnt(int num) {
        int cnt = 0;
        for (int i = 0; i < 32; i++) {
            cnt += (num & 1);
            num >>= 1;
        }
        return cnt;
    }
}
