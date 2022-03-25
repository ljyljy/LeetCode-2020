package String;

import java.util.Scanner;

public class HJ39_IP_SubNet {
    // 类比HJ18
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] mask = sc.nextLine().split("\\.");
            String[] ip1 = sc.nextLine().split("\\.");
            String[] ip2 = sc.nextLine().split("\\.");
            boolean isValid = check(mask) && check(ip1) && check(ip2); // 1. 先检查cnt==4 & val∈[0, 255]
            if (!isValid) {  // ↑ 注意是&&，细心?
                System.out.println(1);
                continue;
            }

            String binMask = getBinStr(mask);
            System.out.println(binMask);
            isValid &= binMask.matches("1{1,}0{1,}"); // [1111...（前全1）0000...（后全0）]，且不存在全1/全0
            if (!isValid) {
                System.out.println(1);
                continue;
            }

            String binIp1 = getBinStr(ip1), binIp2 = getBinStr(ip2);
            boolean sameSubnet = true;
//            StringBuilder and1 = new StringBuilder(), and2 = new StringBuilder();
            for (int i = 0; i < 32; i++) {
                int bit_mask = binMask.charAt(i) - '0';
                int bit_ip1 = binIp1.charAt(i) - '0';
                int bit_ip2 = binIp2.charAt(i) - '0';
                int and1 = bit_mask & bit_ip1, and2 = bit_mask & bit_ip2;
//                and1.append()
                if (and1 != and2) {
                    sameSubnet = false;
                    break;
                }
            }
            System.out.println(sameSubnet? 0: 2);
        }
    }

    private static String getBinStr(String[] nums) {
        StringBuilder binStr = new StringBuilder();
        for (String num: nums) {
            String curBinMask = Integer.toBinaryString(Integer.valueOf(num));
            while (curBinMask.length() < 8) curBinMask = "0" + curBinMask; // 前面padding 0
//            curBinMask = "0".repeat(8-curBinMask.length()) + curBinMask;
            binStr.append(curBinMask);
        }

        return binStr.toString();
    }

    private static boolean check(String[] strs) {
        if (strs.length != 4) return false;
        for (String s: strs) {
            if (s.isEmpty()) return false;

            int num = Integer.valueOf(s); // [0, 255]
            if (num < 0 || num > 255) return false;
        }
        return true;
    }
}