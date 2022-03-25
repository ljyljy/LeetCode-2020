package String;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class HJ45_beautyOfName {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                String name = sc.next();

                int[] chs = new int[26];
                for (char ch: name.toLowerCase().toCharArray()) {
                    chs[ch-'a']++;
                }
                // ?int[]无法自动降序
                Arrays.sort(chs); // 如：aabc=26*2+25+24 ->> 升序[0, ..., 0, 1, 1, 2]
                int ans = 0;
                for (int k = 26; k >= 1; k--) { // 漂亮度k=26, 25, ..., 1
                    ans += chs[k-1] * k;
                    if (chs[k-1] == 0) break; // 升序，前面全0，直接提前退出
                }
                System.out.println(ans);
            }
        }
    }
}
