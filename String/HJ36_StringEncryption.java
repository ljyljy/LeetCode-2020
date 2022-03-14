package String;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class HJ36_StringEncryption {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String key = sc.nextLine();
            String code = sc.nextLine();
            Set<Character> keySet = new LinkedHashSet<>();
            boolean[] bitmap = new boolean[26];
            for (char ch: key.toCharArray()) {
                keySet.add(ch);
                bitmap[ch - 'a'] = true;
            }
            StringBuilder ref = new StringBuilder();
            for (char ch: keySet) ref.append(ch);

            for (char c = 'a'; c <= 'z'; c++) {
                if (bitmap[c - 'a']) continue;
                ref.append(c); // 得到“去重key+其他的非重复字母”，长度恰好26
            }

            for (char ch: code.toCharArray()) {
                System.out.print(ref.charAt(ch-'a'));
            }
            System.out.println();
        }
    }
}
