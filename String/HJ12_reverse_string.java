package String;

import java.util.Scanner;

public class HJ12_reverse_string {
    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c: str.toCharArray()) {
            sb.insert(0, c);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(reverse(str));
    }
}
