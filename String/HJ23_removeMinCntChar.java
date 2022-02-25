package String;

import java.util.Scanner;

public class HJ23_removeMinCntChar {
    //     一种新的编程思想：
    //     1、将26个字母转换成1-26个数字来存储，来比较；
    //     2、数组新用法：用数组索引来代表具体的数字，用具体索引锁存的数字来代表出现的次数
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();
            int[] cnts = new int[26];//?数组代替哈希
            for (char ch: str.toCharArray()) {
                cnts[ch - 'a']++;
            }
            int minCnt = Integer.MAX_VALUE;//最少出现字符的频次
            for (int cnt: cnts) {
                if (cnt > 0 && minCnt > cnt) {
                    minCnt = cnt;
                }
            }
            for (char ch: str.toCharArray()) {
                if (cnts[ch - 'a'] == minCnt)
                    continue;
                System.out.print(ch);
            }
            System.out.println();
        }
    }
}
