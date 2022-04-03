package Two_Pointers.Sliding_Window;

import java.util.Arrays;

public class q395_longest_substring_with_at_least_k_repeating_characters {

    // �Զ���������������������
    public int longestSubstring(String s, int k) {
        int n = s.length();
        int maxLen = 0;
        int[] window = new int[26];

        for (int nType = 1; nType <= 26; nType++) {
            Arrays.fill(window, 0); // ÿ������
            int left = 0, right = 0, uniqueValid = 0, uniqueCnt = 0;

            while (right < n) {
                char ch2Add = s.charAt(right++);
                int cntAdd = ++window[ch2Add - 'a'];
                if (cntAdd == 1) uniqueCnt++; // ��ͬ�ַ�������
                if (cntAdd == k) uniqueValid++; // ���㡾�����ַ�Ƶ��>=k��

//            �������С���������� -- valid���������ƣ��޷���Ϊ��С��������
                // ���� ������ʾ��s ����СдӢ����ĸ��ɣ����26����ĸ����
                while (uniqueCnt > nType) {
                    char ch2Del = s.charAt(left++);
                    int cntDel = --window[ch2Del - 'a'];
                    if (cntDel == 0) uniqueCnt--;
                    if (cntDel == k-1) uniqueValid--; // ����<k! ÿ���ַ�ֻ���Լ�һ�Σ�
                }
                if (uniqueCnt == uniqueValid)
                    maxLen = Math.max(maxLen, right - left);
            }

        }
        return maxLen;
    }

//    // trial����ͨ��������
//    public int longestSubstring_trial(String s, int k) {
//        int n = s.length();
//        int maxLen = 0;
//        char[] ss = s.toCharArray();
//        int[] window = new int[26];
//        int left = 0, right = 0, valid = 0, cnt = 0;
//
//        while (right < n) {
//            char ch2Add = s.charAt(right++);
//            window[ch2Add - 'a']++;
////            if (window[ch2Add - 'a'] == 1) cnt++; ��ͬ�ַ�������
//            if (window[ch2Add - 'a'] == k) valid++; // ���㡾�����ַ�Ƶ��>=k��
//
////            �������С���������� -- valid���������ƣ��޷���Ϊ��С��������
//            // ���� ������ʾ��s ����СдӢ����ĸ��ɣ����26����ĸ����
//        }
//    }
}
