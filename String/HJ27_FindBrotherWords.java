package String;

import java.util.*;

public class HJ27_FindBrotherWords {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            String[] words = new String[n];
            for (int i = 0; i < n; i++) {
                words[i] = sc.next(); // nextLine()会读一整行！错！
            }
            String src = sc.next();
            int idx = sc.nextInt();

            List<String> brothers = getBrothers(words, src);
            Collections.sort(brothers); // List需要排序

            int size = brothers.size();
            System.out.println(size);
            if (idx-1 >= 0 && idx-1 < size) {
                System.out.println(brothers.get(idx-1));
            }

        }
    }

    private static List<String> getBrothers(String[] words, String src) {
        List<String> res = new ArrayList<>();
        int n0 = src.length();
        for (String word: words) {
            int n1 = word.length();
            if (n0 != n1) continue; // 快速剪枝：长度必相等
            if (word.equals(src)) continue; // 不可相等
            if (!isCharEquals(word, src, n0)) continue; // 字符集&个数一样！

            res.add(word);
        }
        return res;
    }

    private static boolean isCharEquals(String s1, String s2, int n) {
        int[] cnts = new int[26]; // 类比位图/q87/Q567

        for (int i = 0; i < n; i++) {
            cnts[s1.charAt(i) - 'a']++;
            cnts[s2.charAt(i) - 'a']--; // 所有字符--正负最终抵消
        }

//         return Arrays.stream(cnts).sum() == 0; // 错！可能有负数？
        for(int i=0; i<26; i++){
            if(cnts[i]!=0) return false;
        }
        return true;
    }

}
