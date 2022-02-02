package Bit;

public class q7_reverse_integer {
    public int reverse(int x) {
        int res = 0;
        while (x != 0) {
            if (res * 10 / 10 != res)
                return 0; // 若溢出，直接返回0
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    // 写法2
    public int reverse0(int x) {
        int res = 0;
        while (x != 0) {
            if (isOverflow(res))  return 0;
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res;
    }

    // ❤判断溢出
    private boolean isOverflow(int res) {
        return !(res * 10 / 10 == res);
    }

    public static void main(String[] args) {
        // 16进制 - 8bit <=> 2个16进制数字(长度) ∴ 32位数的16进制表示长度=(32/8)*2=8
        int int_min = Integer.MIN_VALUE; // 0x80000000 - [-2147483648]
        int int_max = Integer.MAX_VALUE; // 0x7fffffff - [ 2147483647]
        int n1 = (int_min * 10 / 10);
        int n2 = (int_max * 10 / 10);
        int n5 =  214748364;
        int n6 = -214748364;
        int n3 = -214748364 * 10 + (-9);
        int n4 = 2147483647 + 1;
        int n3_ = n5*10 + 8;
        int n4_ = n6*10 + (-9);

        System.out.println(int_min + ", " + int_max);
        System.out.println(int_max * 10 / 10 == int_max);  // false (int_max*10溢出后为1)
        System.out.println(100 * 10 / 10 == 100);  // true
        System.out.println(int_min * 10 / 10 == int_min);  // false (int_min*10溢出后为-1)
        System.out.println(n5 * 10 / 10 == n5);  // true
        System.out.println(n6 * 10 / 10 == n6);  // true
        System.out.println(n3);  // 2147483647
        System.out.println(n4);  // -2147483648
        System.out.println(214748365 * 10 / 10);  // -214748364
        System.out.println(-214748365 * 10 / 10);  // 214748364
    }
}
