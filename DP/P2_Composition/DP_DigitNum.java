package DP.P2_Composition;

import java.util.*;

// ��λDP���ţ�
//  ��С�ڵ��� n ����������
public class DP_DigitNum {
    List<Integer> list = new ArrayList<>();
    public List<Integer> getAllNum(int num) {
        char[] chsNum = String.valueOf(num).toCharArray();
        // ���ڵ� 0 λ��ʱ��ѡ���Ǳ����Ƶ�(true)��ֻ��ѡ�񲻳����� 0 λ��ֵ
        traversal(chsNum, 0, 0, true);
        return list;
    }

    // �ӵ� idx λ��ʼ����, path ��¼·���� isLimit ��ͼ��ʾ��Ϊ�˷�ֹ��С���� n����ֹ��idxλ������
    private void traversal(char[] chsNum, int idx, int path, boolean isLimit) {
        if (idx == chsNum.length) {
            list.add(path);
            return;
        }
        // "isLimit && bit == top"���ͣ��������ͼ��
        // 1) ����һλ��limit������ǰidxλҲ���뱻limit���Ա�֤ÿһλ�����ɳ���num[idx]
        //      ��ѡ��ǰλbit<top�����������limit����Ϊ�϶��޷�����num
        // 2������һλû��limit��˵����һλҲ���豻limit(��ȡ0~9)��һ�����ᳬ��num
        int top = isLimit? chsNum[idx] - '0': 9;
        for (int bit = 0; bit <= top; bit++) {
            traversal(chsNum, idx+1, path*10 + bit, isLimit && bit == top);
        }
    }

    public static void main(String[] args) {
        int num = 123;
        DP_DigitNum sol = new DP_DigitNum();
        List<Integer> res = sol.getAllNum(num);
        System.out.println(res);
        // [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123]
    }
}
