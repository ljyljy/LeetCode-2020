package String;

import java.util.Scanner;

public class HJ4_string_split_len_8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = new String(sc.nextLine());
            // ���ɣ�str��׺8��0��Ȼ���ȡ������strǡ�ó�Ϊ8
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
