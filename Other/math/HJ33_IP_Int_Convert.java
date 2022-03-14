package Other.math;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HJ33_IP_Int_Convert {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String ip = sc.nextLine();
            String[] ipNums = ip.split("\\.");
            // 1) IP -> Long
            long ip2Long = convertIpToNum(ipNums);
            System.out.println(ip2Long);
            // 2) Long -> IP
            Long Long2ip = sc.nextLong();
            String ipConverted = convertNumToIp(Long2ip);
            System.out.println(ipConverted);

//            for (String s: ipNums)
//                System.out.println(ip2Long);
        }
    }

    private static String convertNumToIp(Long long2ip) {
        List<String> nums = new ArrayList<>();
        long mod = 0;
        for (int i = 0; i < 4; i++) {
            mod = long2ip % 256; // 从末位算起
            long2ip = long2ip / 256;
            nums.add(0, String.valueOf(mod)); // 头插！高位往前插入 ?list.add(0, xxx)
        }
        return String.join(".", nums);
    }

    private static long convertIpToNum(String[] ipNums) {
        long res = 0;
        for (String ipNum: ipNums) {
            long num = Long.valueOf(ipNum);
            res = res * 256 + num;
        }
        return res;
    }
}
