package Other.LibFunc.regex;

import java.util.Scanner;

public class HJ31_wordsReverseRegexSplit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
//             String[] strs = str.split("[^a-zA-Z]"); // ?regex：同下
            String[] strs = str.split("[^\\w]"); // ?regex: 任意非字母

            int n = strs.length;

            StringBuilder sb = new StringBuilder();
            for (int i = n - 1; i >= 0; i--) {
                sb.append(strs[i] + " ");
            }
            System.out.println(sb.toString().trim());
        }
    }
}