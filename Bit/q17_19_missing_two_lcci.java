package Bit;

import java.util.Arrays;

public class q17_19_missing_two_lcci {
    // ��� q260
    public int[] missingTwo(int[] nums) {
        int n = nums.length;
        int xor = 0, N = n+2;
        for (int num: nums) {
            xor ^= num;
        }
        for (int i = 1; i <= N; i++) { // �ӣ�1~N(n+2)������n��
            xor ^= i; // xor: nums���(1~N֮�������ȱ2��) & 1~N
        } // xor = 1~N�䣬��ʧ������xor; �������ɶ����=0����q260ͬ��

        int num1 = 0, num2 = 0; // �ⷨ1��num2 = xor ^ num1 �� ����+else
        // �ӣ����������Ҫ���α���nums[] & 1~N(��©������)
//        // ����1������ʧ�����е�һ����ͬ��λ�� ��(num >> diffBit) & 1 == 0/1��
//        //     �ӵ�λ����λ���ҵ�a��b��һ����ͬ��λdiffBit
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

        //����2����lowbit����(num & lowbit) == 0/��0������
        //    �ӣ�����һ��Ϊ1������λ����(diffBit)��0/1�� ��
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
