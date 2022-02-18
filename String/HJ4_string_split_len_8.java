package String;

import java.util.Scanner;

public class HJ4_string_split_len_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = new String(sc.nextLine());
            // 技巧：str后缀8个0，然后截取，除非str恰好长为8
            if (str.length() % 8 != 0) {
                str = str + "00000000";
            }
            while(str.length() >= 8) {
                System.out.println(str.substring(0, 8)); // [0, 7]
                str = str.substring(8); // [8, end]
            }
        }
    }
}
