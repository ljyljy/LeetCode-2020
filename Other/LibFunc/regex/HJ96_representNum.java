package Other.LibFunc.regex;

import java.util.Scanner;

public class HJ96_representNum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            System.out.println(markNums(str));
        }
        sc.close();
    }

    private static String markNums(String str) {
        // ?��Regex�������� $1, $2...����http://php-note.com/article/363.html
        // ?regex�⣬���÷���ʹ�á�$1��, regex��ʹ�á�\\1��, ���HJ20, HJ96
        return str.replaceAll("(\\d+)","\\*$1\\*");
    }


    // 1**3 -> *1****3*�� �м�ԭʼ��"**"���滻
//    private static String markNums_WA(String str) {
//        char[] arr = str.toCharArray();
//        StringBuilder sb = new StringBuilder();
//        for (char ch: str.toCharArray()) {
//            if ('0' <= ch && ch <= '9') {
//                sb.append("*" + ch + "*"); // ���߶�����*
//            } else sb.append(ch);
//        }
//        return sb.toString().replaceAll("[*]{2,}", "");  // ���������м���[**]+ �滻��
//    }
}
