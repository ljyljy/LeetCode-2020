package Other.math;

public class q172_factorial_trailing_zeroes {
    // ����n��������5�ĸ���
    public int trailingZeroes(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt += n / 5;
            n /= 5;
        }
        return cnt;
    }
}

// TLE��
//import java.math.BigInteger;
//
//class Solution {
//    public int trailingZeroes(int n) {
//        BigInteger num = BigInteger.ONE;// �ӣ�������BigInteger��int/long�������
//        int cnt = 0;
//
//        for (int i = 2; i <= n; i++) {
//            num = num.multiply(BigInteger.valueOf(i));
//        }
//
//        while (num.mod(BigInteger.TEN).equals(BigInteger.ZERO)) {
//            num = num.divide(BigInteger.TEN);
//            cnt++;
//        }
//        return cnt;
//
//        // TLE!!! - �ַ��������
//        // String str = String.valueOf(num);
//        // int len = str.length();
//        // int cnt = 0, i = len - 1;
//        // // System.out.println(str);
//        // while (i >= 0 && str.charAt(i--) == '0') {
//        //     cnt++;
//        // }
//        // return cnt;
//    }
//
//}