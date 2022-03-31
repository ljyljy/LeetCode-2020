package DP.P1_Optimize;

import java.lang.reflect.Array;
import java.util.*;

public class HJ92_longest_consecutive_subsequence {
    // 法1：常规 TreeMap
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            int len = str.length();
            char[] ss = str.toCharArray();
//            getLCS(str, len, ss);
            getLCS2(str, len, ss);
        }
    }

    // 法2：动归【荐】
    private static void getLCS2(String str, int n, char[] ss) {
        int[] dp = new int[n];
        if (Character.isDigit(ss[0])) dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if ('0' <= ss[i] && ss[i] <= '9') {
                dp[i] = dp[i - 1] + 1;
            } else dp[i] = 0;
        }

        int maxLen = Arrays.stream(dp).max().getAsInt();
        List<Integer> endIdxList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (maxLen == dp[i]) {
                endIdxList.add(i);
            }
        }

        for (int end : endIdxList) {
            System.out.print(new String(ss, end - maxLen + 1, maxLen));
        }
        System.out.print("," + maxLen + "\n");
    }

    // 法1：TreeMap，长度key降序，val-List<String>拼接
    private static void getLCS(String str, int len, char[] ss) {
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, List<String>> map = new TreeMap<>((o1, o2) -> o2 - o1);// 长度降序
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(ss[i])) {
                sb.append(ss[i]);
            } else {
                int curLen = sb.length();
//                    System.out.println(sb);
                if (curLen > 0) {
                    List<String> list = map.getOrDefault(curLen, new ArrayList<>());
                    list.add(sb.toString());
                    map.put(curLen, list);
                }
                sb.setLength(0);
            }
        }
        if (sb.length() > 0) { // 最后一串数字结尾
            int curLen = sb.length();
            List<String> list = map.getOrDefault(curLen, new ArrayList<>());
            list.add(sb.toString());
            map.put(curLen, list);
        }

        System.out.println(String.join("", map.get(map.firstKey())) + "," + map.firstKey());
    }
}