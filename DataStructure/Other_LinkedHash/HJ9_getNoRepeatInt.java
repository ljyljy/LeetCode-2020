package DataStructure.Other_LinkedHash;

import java.util.*;

public class HJ9_getNoRepeatInt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder sb = new StringBuilder(str);
        sb = sb.reverse();
        char[] chs = sb.toString().toCharArray();
        // ���򣬵���ĿҪ�����������sb��Ҫ��ת
        Set<Character> nums = new LinkedHashSet<>();
        for (char ch: chs) {
            nums.add(ch);
        }
        for (char ch: nums) {
            System.out.print(ch);
        }
    }
}
