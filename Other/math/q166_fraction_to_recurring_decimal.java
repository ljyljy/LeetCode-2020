package Other.math;

import java.util.HashMap;
import java.util.Map;

public class q166_fraction_to_recurring_decimal {
    // numerator：分子，denominator：分母
    // 模拟 + Map - 时、空O(M), M=答案长度
    public String fractionToDecimal(int numerator, int denominator) {
        long a = numerator, b = denominator; // 转 long 计算，防止溢出
        if (a % b == 0) return String.valueOf(a / b); // 整除，直接返回
        StringBuilder sb = new StringBuilder();
        if (a * b < 0) sb.append("-"); // 结果为负
        a = Math.abs(a); b = Math.abs(b);

        // 计算小数点前的部分，并将余数赋值给 a
        sb.append(String.valueOf(a/b) + ".");
        a %= b; // 计算小数部分，a变为余数
        Map<Long, Integer> map = new HashMap<>(); // <余数num, idx>
        while (a != 0) { // 余数不为0
            map.put(a, sb.length()); // idx=len(sb),∵小数点占了1位，idx无需-1
            a *= 10; // 余数*10，再除以b ↓
            sb.append(a / b); // 商
            a %= b; // 新余数
            // 如果当前余数之前出现过，则将 [出现位置map.get(a) 到 当前位置idx] 的部分抠出来（循环小数部分）
            if (map.containsKey(a)) {
                int firstIdx = map.get(a);
                return String.format("%s(%s)",
                        sb.substring(0, firstIdx), sb.substring(firstIdx));
            }
        } // 退出while，说明小数部分没有循环，最后的余数*10整除了b
        return sb.toString();
    }
}
