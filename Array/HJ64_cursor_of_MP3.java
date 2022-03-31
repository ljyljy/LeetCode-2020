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
        int pageSize = nSongs < 4? nSongs: 4; // 页面大小默认为4，除非歌曲数量<4
        int top = 1, cursor = 1; // top：当前页顶部(指向的歌曲号)，cursor：光标(指向的歌曲号)
        for (char ch: cmd.toCharArray()) {
            if (ch == 'U') {
                if (top == cursor) { // cursor处于页顶
                    // 特殊：top(1 -> 7=10-4+1) 或 一般翻页：top--
                    top = top == 1? nSongs - 3: top - 1;
                }
                cursor = cursor == 1? nSongs: cursor-1;
            } else if (ch == 'D') {
                if (cursor == top + 3) { // cursor处于页底
                    // 特殊：若cursor页底指向最后一首歌，则重置为1; 否则, 一般翻页top++
                    top = cursor == nSongs? 1: top + 1;
                }
                cursor = cursor == nSongs? 1: cursor + 1;
            }
        }
        if (pageSize < 4) top = 1; // 特判：永不翻页
        for (int i = 0; i < pageSize; i++) {
            System.out.print(top + i + " ");
        }
        System.out.println();
        System.out.println(cursor);
    }
}
