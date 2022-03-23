package String;

public class q43_multiply_strings {
    /**
     * ������ʽ
     *    num1
     *  x num2
     *  ------
     *  result
     *
     *  https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        String res = "0"; // ��""
        int n1 = num1.length(), n2 = num2.length();
        // num2 ��λ�� num1 ���
        for (int j = n2-1; j >= 0; j--) {
            int carry = 0;
            // ���� num2 ��iλ������ num1 ��˵Ľ��
            StringBuilder sb = new StringBuilder();
            // num2��λ[j]��� -- ÿ����Ҫ��ĩβ[j+1, n2-1]����0
            for (int i = j+1; i <= n2-1; i++) { //�� 0����=n1-1-j��
                sb.append(0);
            }

            int num2_j = num2.charAt(j) - '0';
            // num2 �ĵ�jλ���� num2_j �� num1 ���(��λ���Ӻ���ǰ)
            for (int i = n1-1; (i >= 0 || carry != 0); i--) {
                int num1_i = i>=0? num1.charAt(i) - '0': 0;
                int cur = num1_i * num2_j + carry;
                int product = cur % 10;
                sb.append(product); // �����Ҫreverse
                // sb.insert(0, product + ""); // ��2������reverse
                carry = cur / 10;
            }
            // ����ǰ������¼���Ľ�������Ϊ�µĽ��
            res = addStrings(res, sb.reverse().toString());
        }
        return res;
    }

    /**
     * �������ַ������ֽ�����λ��ӣ������ַ�����ʽ�ĺ�, ��Q415
     */
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int n1 = num1.length(), n2 = num2.length();
        int carry = 0;
        for (int i = n1-1, j = n2-1; (i >= 0 || j >= 0 || carry != 0); i--, j--) {
            int num1_i = i>=0? num1.charAt(i) - '0' : 0;
            int num2_j = j>=0? num2.charAt(j) - '0' : 0;
            int sum = (num1_i + num2_j + carry) % 10;
            sb.append(sum); // �����Ҫreverse����λ��ǰadd����Ӧͷ��sb��
            // sb.insert(0, sum);
            carry = (num1_i + num2_j + carry) / 10;
        }
        return sb.reverse().toString();

    }
}
