package Two_Pointers.Sliding_Window;

import java.util.Arrays;

public class q567_permutation_in_string {
    // ❤ 1)数组的比较： Arrays.equals(arr1, arr2);
    // 2) 优化-数组代替哈希
    public boolean checkInclusion_v1(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(); // n1更短(子串))
        if (n1 > n2) return false;

        int[] cnt1 = new int[26]; // 优化-数组代替哈希
        int[] cnt2 = new int[26];
        for (int i = 0; i < n1; i++) { // 窗口长度 == n1（短串）
            cnt1[s1.charAt(i) - 'a']++;
            cnt2[s2.charAt(i) - 'a']++;
        }
        if (Arrays.equals(cnt1, cnt2))
            return true; // s1.equals(s2)
        for (int i = n1; i < n2; i++) { // 滑动窗口(维护长度==n1)
            cnt2[s2.charAt(i) - 'a']++; // 新元素加入滑窗
            cnt2[s2.charAt(i-n1) - 'a']--; // 旧元素(最左)删除
            if (Arrays.equals(cnt1, cnt2))
                return true;
        }
        return false;
    }

//    public boolean checkInclusion(String s1, String s2) {
//        int n1 = s1.length(), n2 = s2.length(); // n1更短(子串))
//        if (n1 > n2) return false;
//
//    }
}
