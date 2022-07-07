package String.KMP;

import java.io.*;
import java.util.*;

// ��ȣ�ACW831, Q28, 214
public class acw831_KMP {
    /**
     * 3
     * aba
     * 5
     * ababa
     *
     * ���
     * 0 2
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());
        String pattern = reader.readLine();
        int n = Integer.parseInt(reader.readLine());
        String str = reader.readLine();

        // I/O ���������ݹ��󣬵���I/O��ʱ���� TLE
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
        // 1. ���� - O(n*m)
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

        KMP2_bufferedWriter(str, pattern); // ƥ�䲻�ɹ��򲻴�ӡ

        reader.close();
    }

    private static void KMP2_bufferedWriter(String s, String p) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        if (p.isEmpty()) return;

        int n = s.length(), m = p.length();
        s = " " + s; p = " " + p;
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        // 1) ����next���� - ����p��ǰ��׺
        int[] next = new int[m+1]; // �����ڱ� �೤��+1
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }
        // 2) ƥ��
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
            if (ss[i] == pp[j+1]) j++;
            if (j == m) {
                // ƥ���Ӵ�����ӡ��s�е���ʼ�±꣨ĩβs_i - �Ӵ�����m��
                writer.write((i - m) + " ");
                j = next[j]; // ����Ҫ��ƥ������������Ҫ��jǰ�ƣ���q28����Ҫ��
                // �� ׼��ƥ����һ�������ݵ�'��ǰ׺'��������һ��pp[j+1]Խ��(j==m)��
            }
        }

        writer.flush();//����write�µ����ݣ����ȴ���writers�У�������flush�Ժ󣬻�����������е����ݡ����û�е���flush���򲻻Ὣwriter�е����ݽ��������
        writer.close();
    }

    // KMP��O(n+m), ���q28
    private static List<Integer> KMP(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        if (p.isEmpty()) return ans;

        int n = s.length(), m = p.length();
        s = " " + s; p = " " + p;
        char[] ss = s.toCharArray(), pp = p.toCharArray();
        // 1) ����next���� - ����p��ǰ��׺
        int[] next = new int[m+1];
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && pp[i] != pp[j+1]) j = next[j];
            if (pp[i] == pp[j+1]) j++;
            next[i] = j;
        }
        // 2) ƥ��
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && ss[i] != pp[j+1]) j = next[j];
            if (ss[i] == pp[j+1]) j++;
            if (j == m) {
                ans.add(i-m);
                j = next[j]; // ?����©����ͬ��q28����
            }
        }
        return ans;
    }

    // ������O(n*m)
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
