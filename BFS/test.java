package BFS;

import java.util.*;

public class test {

    public int getMaxVal(char[] chars) {
        int n = chars.length;
        boolean startWithBig = true;
        String big = "", small = "";
        for (int i = 0; i < n; i+=2) {
            if (startWithBig) {
                if (i == n-1) {
//                    if (chars[i] != '0'){
                        small += chars[i];
//                    }
                    break;
                }else {
                    big += chars[i];
                    small += i+1 < n? chars[i+1] : "";
                }
            } else {
                small += chars[i];
                big += i+1 < n? chars[i+1] : "";

            }
            startWithBig = !startWithBig;
        }
        System.out.println("big=" + big + ", small=" + small);
        return Integer.valueOf(big) * Integer.valueOf(small);
    }

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int[] numsCnt = new int[10];
//        numsCnt = new int[]{0,0,1,1,1,1,0,0,0,1};
//        numsCnt = new int[]{1,0,1,0,1,1,1,0,0,0};
        numsCnt = new int[]{2,3,0,1,0,0,0,0,0,0};
        for (int i = 0; i < 10; i++) {
//            numsCnt[i] = sc.nextInt();
            int cnt = numsCnt[i];
            while (cnt != 0) {
                sb.append("" + i);
                cnt--;
            }
        }
        sb.reverse();
        System.out.println(sb);

        test sol = new test();
        int res = sol.getMaxVal(sb.toString().toCharArray());
        System.out.println(res);
    }
}
