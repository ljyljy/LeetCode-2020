package String;

import java.util.Scanner;

public class HJ21_easyCode {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] map = {2,2,2,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,7,8,8,8,9,9,9,9}; // 数组代替哈希！?技巧！
        while (sc.hasNext()) {
            String code = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            for (char ch: code.toCharArray()) {
                if (ch >= 'a' && ch <= 'z') {
                    sb.append(map[ch - 'a']);
                } else if (ch >= 'A' && ch <= 'Z') {
                    int ch_ = (ch - 'A' + 1) + 'a';
                    if (ch == 'Z') ch_ = 'a';
                    sb.append((char)ch_);
                } else { // ch >= '1' && ch <= '9'
                    sb.append(ch);
                }
            }
            System.out.println(sb.toString());
        }
    }
}
