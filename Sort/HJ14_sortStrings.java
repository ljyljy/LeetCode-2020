package Sort;

import java.util.Arrays;
import java.util.Scanner;

public class HJ14_sortStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] strs = new String[n];
//         int i = 0;
//         while (sc.hasNext()) {
//             strs[i++] = sc.next(); // ? nextLine()´íÎó£¡
//         }
        for (int i = 0; i < n; i++) {
            strs[i] = sc.next();
        }
        Arrays.sort(strs); // , (o1, o2)->(o1.compareTo(o2)));
        for (String str: strs) {
            System.out.println(str);
        }
    }
}

