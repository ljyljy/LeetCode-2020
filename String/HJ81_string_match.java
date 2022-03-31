package String;

import java.util.Scanner;

public class HJ81_string_match {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String pattern = sc.nextLine();
            String str = sc.nextLine();
            System.out.println(isMatch(pattern, str));
        }
    }

    private static boolean isMatch(String p, String s) {
        for (char ch: p.toCharArray()) {
            if (!s.contains(ch+"")) return false;
        }
        return true;
    }
}
