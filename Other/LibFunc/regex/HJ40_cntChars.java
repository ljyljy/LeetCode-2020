package Other.LibFunc.regex;

import java.util.Scanner;

public class HJ40_cntChars {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            int n = str.length();
            int[] cnts = new int[4]; //alphas spaces, digits, others = 0;
            for (char ch: str.toCharArray()) {
                if (Character.isLetter(ch)) { //? (ch+"").matches("[a-zA-Z]"), ?不可["\\String.HJ_msg"]，等价于["_a-zA-Z0-9"] !!!
                    cnts[0]++;
                } else if (ch == ' ') {// (ch+"").matches("\\s")
                    cnts[1]++;
                } else if (Character.isDigit(ch)) { // (ch+"").matches("[0-9]")
                    cnts[2]++;
                } else {
                    cnts[3]++;
                }
            }

            for (int cnt: cnts) {
                System.out.println(cnt);
            }
        }
    }
}
