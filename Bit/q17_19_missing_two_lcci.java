package Bit;

import java.util.Arrays;

public class q17_19_missing_two_lcci {
    // 类比 q260
    public int[] missingTwo(int[] nums) {
        int n = nums.length;
        int xor = 0, N = n+2;
        for (int num: nums) {
            xor ^= num;
        }
        for (int i = 1; i <= N; i++) { // 坑：1~N(n+2)，而非n！
            xor ^= i; // xor: nums异或(1~N之间的数，缺2数) & 1~N
        } // xor = 1~N间，消失俩数的xor; 其余数成对异或=0（与q260同）

        int num1 = 0, num2 = 0; // 解法1：num2 = xor ^ num1 或 分组+else
        // 坑：分组遍历需要依次遍历nums[] & 1~N(勿漏！！！)
//        // 【法1】求消失俩数中第一个不同的位数 【(num >> diffBit) & 1 == 0/1】
//        //     从低位往高位，找到a与b第一个不同的位diffBit
//        int diffBit = -1;
//        for (int i = 0; i < 32 && (diffBit == -1); i++) {
//            if (((xor >> i) & 1) == 1) {
//                diffBit = i;
//            }
//        }
//        for (int num: nums) {
//            if (((num >> diffBit) & 1) == 1) {
//                num1 ^= num;
//            } // else num2 ^= num;
//        }
//
//        for (int i = 1; i <= N; i++) {
//            if (((i >> diffBit) & 1) == 1) {
//                num1 ^= i;
//            } // else num2 ^= i;
//        }

        //【法2】求lowbit，【(num & lowbit) == 0/非0的数】
        //    坑：但不一定为1，不是位运算(diffBit)的0/1！ ↑
        int diffNum = lowbit(xor);
        for (int num: nums) {
            if ((num & diffNum) != 0) {
                num1 ^= num;
            }
//            else num2 ^= num;
        }
        for (int i = 1; i <= N; i++) {
            if ((i & diffNum) != 0) {
                num1 ^= i;
            }
//            else num2 ^= i;
        }
        System.out.println("xor=" + xor);
        return new int[]{num1, xor ^ num1};
    }

    private int lowbit(int x) {
        return x & (-x);
    }


    public static void main(String[] args) {
        q17_19_missing_two_lcci sol = new q17_19_missing_two_lcci();
        int[] nums = {2};
        System.out.println(Arrays.toString(sol.missingTwo(nums)));

        int[] nums2 = {1, 2, 3, 4, 6, 7, 9, 10};
        System.out.println(Arrays.toString(sol.missingTwo(nums2)));
    }
}
