package Bit;

import java.util.HashSet;
import java.util.Set;

public class q326_power_of_three {
    // 法1：试除法 - O(logn)
    public boolean isPowerOfThree0(int n) {
        while (n != 0 && n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

    // 法2：倍数 & 约数 - O(1)
//    注意：这并不是快速判断 x 的幂的通用做法，当且仅当 x为质数可用。
    public boolean isPowerOfThree1(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    // 法3：打表 set
    // 更容易想到的「不使用循环/递归」的做法是进行打表预处理。
    //使用 static 代码块，预处理出不超过 int 数据范围的所有 3 的幂，最后 O(1) 查表返回。
    static Set<Integer> set = new HashSet<>();
    static {
        int cur = 1;
        set.add(1);
        // 防止int溢出，使用除法 ↓
        while (cur < Integer.MAX_VALUE / 3) {
            cur *= 3;
            set.add(cur);
        }
    }

    public boolean isPowerOfThree(int n) {
        return n > 0 && set.contains(n);
    }
}
