package String;

public class q415_add_strings {
    // 应用：见Q43
    public String addStrings(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length();
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = n1-1, j = n2-1; (i >= 0 || j >= 0 || carry != 0); i--, j--) {
            int x = i>=0? num1.charAt(i) - '0': 0;
            int y = j>=0? num2.charAt(j) - '0': 0;
            int sum = (x + y + carry) % 10;
            sb.append(sum); // 若sb.insert(0, sum); - 则最后无需revers
            carry = (x + y + carry) / 10;
        }
        return sb.reverse().toString();
    }
}


