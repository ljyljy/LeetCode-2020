package Other;

import java.util.Arrays;
import java.util.Scanner;

public class HJ34_sortString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            // ? �ַ�������תΪchar[]��Ȼ��Arrays.sort
            char[] ss = str.toCharArray();
            Arrays.sort(ss);
            System.out.println(ss); // ?char[]����ֱ�Ӵ�ӡ��
        }
    }
}
