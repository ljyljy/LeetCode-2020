package String;

import java.util.Scanner;

public class q415_HJ57_add_strings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine(), s2 = sc.nextLine();
            System.out.println(addStrings(s1, s2));
        }
    }

    private static String addStrings(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        int carry = 0;
        StringBuilder res = new StringBuilder();
        for (int i = n1-1, j = n2-1; i >= 0 || j >= 0 || carry != 0; i--, j--) {
            int num1 = i >= 0? s1.charAt(i) - '0': 0;
            int num2 = j >= 0? s2.charAt(j) - '0': 0;
            int curSum = num1 + num2 + carry;
            res.insert(0, curSum % 10);
            carry = curSum / 10;
        }
        return res.toString();
    }
}
