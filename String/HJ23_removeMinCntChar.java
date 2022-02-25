package String;

import java.util.Scanner;

public class HJ23_removeMinCntChar {
    //     һ���µı��˼�룺
    //     1����26����ĸת����1-26���������洢�����Ƚϣ�
    //     2���������÷��������������������������֣��þ������������������������ֵĴ���
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String str = sc.nextLine();
            int[] cnts = new int[26];//?��������ϣ
            for (char ch: str.toCharArray()) {
                cnts[ch - 'a']++;
            }
            int minCnt = Integer.MAX_VALUE;//���ٳ����ַ���Ƶ��
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
