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
                // ?int[]�޷��Զ�����
                Arrays.sort(chs); // �磺aabc=26*2+25+24 ->> ����[0, ..., 0, 1, 1, 2]
                int ans = 0;
                for (int k = 26; k >= 1; k--) { // Ư����k=26, 25, ..., 1
                    ans += chs[k-1] * k;
                    if (chs[k-1] == 0) break; // ����ǰ��ȫ0��ֱ����ǰ�˳�
                }
                System.out.println(ans);
            }
        }
    }
}
