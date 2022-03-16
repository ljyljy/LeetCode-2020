package String;

import java.util.Scanner;

public class HJ18_IpAndMask {
    private static final int A = 0, B = 1, C = 2, D = 3, E = 4, INVALID = 5, PRIVATE = 6;
    private static final int IP = 7, MASK = 8;
    private static int[] cnts = new int[7]; // A/B/C/D/E���ַ+�Ƿ�+����;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String[] ipMask = sc.nextLine().split("~");
            String ip = ipMask[0], mask = ipMask[1];
            cntIpAndMask(ip, mask);
        }
        for (int cnt: cnts) System.out.print(cnt + " ");
    }

    private static void cntIpAndMask(String ip, String mask) {
        // 1. �ָͬʱ���Խ��г����Ƿ�Ϸ�
        int[] ipNums = getSplitNums(ip);
        int[] maskNums = getSplitNums(mask);
        // 2. ���кϷ�IP��ֱ�Ӻ���"(0|127)(.).*)"����ʹ�ø�ʽ�Ƿ���
        if (ipNums != null && (ipNums[0] == 0 || ipNums[0] == 127)) return;
        // 3. ͳ�ƷǷ���ַ
        if (ipNums == null || !doubleCheck(ipNums, IP)) {
            cnts[INVALID]++;
            return;
        }
        if (maskNums == null || !doubleCheck(maskNums, MASK)) {
            cnts[INVALID]++;
            return;
        }
        // 4. ͳ�ƺϷ�IP��ַ(A~E)
        if (ipNums[0] >= 1 && ipNums[0] <= 126) {
            cnts[A]++;
        } else if (ipNums[0] >= 128 && ipNums[0] <= 191) {
            cnts[B]++;
        } else if (ipNums[0] >= 192 && ipNums[0] <= 223) {
            cnts[C]++;
        } else if (ipNums[0] >= 224 && ipNums[0] <= 239) {
            cnts[D]++;
        } else if (ipNums[0] >= 240 && ipNums[0] <= 255) {
            cnts[E]++;
        }
        // 5. ͳ��˽��IP
        if (ipNums[0] == 10) {
            cnts[PRIVATE]++;
        } else if (ipNums[0] == 172) {
            if (ipNums[1] >= 16 && ipNums[1] <= 31)
                cnts[PRIVATE]++;
        } else if (ipNums[0] == 192 && ipNums[1] == 168) {
            cnts[PRIVATE]++;
        }
    }

    private static boolean doubleCheck(int[] nums, int type) {
        // IP / MASK����[0,255]
        if (nums[0] > 255 || nums[1] > 255 || nums[2] > 255 || nums[3] > 255)
            return false;
        // ���MASK
        if (type == MASK) {
            StringBuilder sb = new StringBuilder();
            for (int num: nums) {
                sb.append(toBinaryStr(num));
            }
            String maskBitStr = sb.toString(); // 32bit
            // �������룺[ȫ1+ȫ0]��������ȫ0(����)/ȫ1(�㲥)
            return (maskBitStr.matches("1{1,}0{1,}")); // ��reg="[1]+[0]+"
        }
        return true;
    }

    private static String toBinaryStr(int num) {
        String binStr = Integer.toBinaryString(num);
        int n = binStr.length();
        // д��1��ţ���ƺ���֧��JDK11�� str.repeat(n)
//        if (n < 8) binStr = "0".repeat(8-n) + binStr; // ÿ��num����8bit,������ǰ׺'0'
        // д��2
        while (binStr.length() < 8) {
            binStr = "0" + binStr;
        }
//        System.out.println(num+", toBin: " + binStr);
        return binStr;
    }

    private static int[] getSplitNums(String s) {
        String[] ss = s.split("\\.");
        int n = ss.length;
        if (n != 4) return null; // �Ƿ�
        int[] nums = new int[4];
        for (int i = 0; i < n; i++) {
            if (ss[i].isEmpty()) return null; // ˵���Ƿ�
            nums[i] = Integer.valueOf(ss[i]);
        }
        return nums;
    }
}
