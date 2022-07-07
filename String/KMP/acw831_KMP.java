package String.KMP;

import java.io.*;
import java.util.*;

// 类比：ACW831, Q28, 214
public class acw831_KMP {
    /**
     * 3
     * aba
     * 5
     * ababa
     *
     * 输出
     * 0 2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());
        String pattern = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();

        // I/O （输入数据过大，导致I/O超时）： TLE
//         Scanner sc = new Scanner(System.in);
//         int m = sc.nextInt();
//         sc.nextLine();
//         String pattern = sc.nextLine();
// //        System.out.println("pattern: " + pattern);
//         int n = sc.nextInt();
//         sc.nextLine();
//         String str = sc.nextLine();
// //        System.out.println("str: " + str);

//        String str = "ababababacd";
//        String pattern = "ababa";
        // 1. 暴力 - O(n*m)
//        List<Integer> ans = BF(str, pattern);
//        System.out.println(ans.toString());

//        // 2. KMP - O(n+m)
//        List<Integer> ans2 = KMP(str, pattern);
//        if (ans2.size() == 0) {
//            writer.write("-1");
//        } else {
////            System.out.println(ans2.toString());
//            for (int idx: ans2) {
////                System.out.print(idx + " ");
//                writer.write(idx + " ");
//            }
//        }

        KMP2_bufferedWriter(str, pattern); // 匹配不成功则不打印

        reader.close();
    }

    private static void KMP2_bufferedWriter(String s, String p) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        if (p.isEmpty()) return;

        int n = s.length(), m = p.length();
        s = " " + s; p = " " + p;
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        // 1) 构造next数组 - 根据p的前后缀
        int[] next = new int[m+1]; // ∵有哨兵 ∴长度+1
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }
        // 2) 匹配
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
            if (ss[i] == pp[j+1]) j++;
            if (j == m) {
                // 匹配子串，打印在s中的起始下标（末尾s_i - 子串长度m）
                writer.write((i - m) + " ");
                j = next[j]; // 若需要【匹配多个】，还需要将j前移！（q28不需要）
                // ↑ 准备匹配下一个：回溯到'好前缀'，否则下一轮pp[j+1]越界(j==m)！
            }
        }

        writer.flush();//所有write下的内容，会先存在writers中，当启用flush以后，会输出存在其中的内容。如果没有调用flush，则不会将writer中的内容进行输出。
        writer.close();
    }

    // KMP：O(n+m), 详见q28
    private static List<Integer> KMP(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.isEmpty()) return ans;

        int n = s.length(), m = p.length();
        s = " " + s; p = " " + p;
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        // 1) 构造next数组 - 根据p的前后缀
        int[] next = new int[m+1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }
        // 2) 匹配
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
            if (ss[i] == pp[j+1]) j++;
            if (j == m) {
                ans.add(i-m);
                j = next[j]; // ?【勿漏，不同于q28！】
            }
        }
        return ans;
    }

    // 暴力：O(n*m)
    private static List<Integer> BF(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        for (int i = 0; i <= n-m; i++) { // O(n)
            String sub = s.substring(i, i + m);
            if (sub.equals(p)) { // O(m)
                ans.add(i);
            }
        }
        return ans;
    }
}
