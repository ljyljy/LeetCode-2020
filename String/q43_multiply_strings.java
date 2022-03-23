package String;

public class q43_multiply_strings {
    /**
     * 计算形式
     *    num1
     *  x num2
     *  ------
     *  result
     *
     *  https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        String res = "0"; // 或""
        int n1 = num1.length(), n2 = num2.length();
        // num2 逐位与 num1 相乘
        for (int j = n2-1; j >= 0; j--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder sb = new StringBuilder();
            // num2逐位[j]相乘 -- 每轮需要在末尾[j+1, n2-1]处补0
            for (int i = j+1; i <= n2-1; i++) { //↑ 0个数=n1-1-j个
                sb.append(0);
            }

            int num2_j = num2.charAt(j) - '0';
            // num2 的第j位数字 num2_j 与 num1 相乘(逐位，从后往前)
            for (int i = n1-1; (i >= 0 || carry != 0); i--) {
                int num1_i = i>=0? num1.charAt(i) - '0': 0;
                int cur = num1_i * num2_j + carry;
                int product = cur % 10;
                sb.append(product); // 最后需要reverse
                // sb.insert(0, product + ""); // 法2：无需reverse
                carry = cur / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, sb.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行逐位相加，返回字符串形式的和, 见Q415
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length(), n2 = num2.length();
        int carry = 0;
        for (int i = n1-1, j = n2-1; (i >= 0 || j >= 0 || carry != 0); i--, j--) {
            int num1_i = i>=0? num1.charAt(i) - '0' : 0;
            int num2_j = j>=0? num2.charAt(j) - '0' : 0;
            int sum = (num1_i + num2_j + carry) % 10;
            sb.append(sum); // 最后需要reverse（逐位向前add，理应头插sb）
            // sb.insert(0, sum);
            carry = (num1_i + num2_j + carry) / 10;
        }
        return sb.reverse().toString();

    }
}
