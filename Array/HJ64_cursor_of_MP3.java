package Array;

import java.util.Scanner;

public class HJ64_cursor_of_MP3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int nSongs = sc.nextInt();
            String cmd = sc.next();
            parseCmd(cmd, nSongs);
        }
    }

    private static void parseCmd(String cmd, int nSongs) {
        int pageSize = nSongs < 4? nSongs: 4; // ҳ���СĬ��Ϊ4�����Ǹ�������<4
        int top = 1, cursor = 1; // top����ǰҳ����(ָ��ĸ�����)��cursor�����(ָ��ĸ�����)
        for (char ch: cmd.toCharArray()) {
            if (ch == 'U') {
                if (top == cursor) { // cursor����ҳ��
                    // ���⣺top(1 -> 7=10-4+1) �� һ�㷭ҳ��top--
                    top = top == 1? nSongs - 3: top - 1;
                }
                cursor = cursor == 1? nSongs: cursor-1;
            } else if (ch == 'D') {
                if (cursor == top + 3) { // cursor����ҳ��
                    // ���⣺��cursorҳ��ָ�����һ�׸裬������Ϊ1; ����, һ�㷭ҳtop++
                    top = cursor == nSongs? 1: top + 1;
                }
                cursor = cursor == nSongs? 1: cursor + 1;
            }
        }
        if (pageSize < 4) top = 1; // ���У�������ҳ
        for (int i = 0; i < pageSize; i++) {
            System.out.print(top + i + " ");
        }
        System.out.println();
        System.out.println(cursor);
    }
}
