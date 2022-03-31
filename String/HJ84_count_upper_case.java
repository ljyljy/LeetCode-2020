package String;

import java.util.Scanner;

public class HJ84_count_upper_case {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int cnt = 0;
            String line = sc.nextLine();
            for (char ch: line.toCharArray()) {
                // »ò(ch+"").matches("[A-Z]")
                if ('A' <= ch && ch <= 'Z')  cnt++;
            }
            System.out.println(cnt);
        }
        sc.close();
    }
}
