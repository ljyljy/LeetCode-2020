package String;

import java.util.*;

public class HJ11_reverseNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
//         ·¨1£ºsb.reverse()
        StringBuilder sb = new StringBuilder(num + "");
        System.out.println(sb.reverse());
    }
}
