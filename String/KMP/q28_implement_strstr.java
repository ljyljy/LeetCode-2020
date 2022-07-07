package String.KMP;

import java.util.ArrayList;
import java.util.List;

// 类比：ACW831, Q28, 214
public class q28_implement_strstr {
    // 法1：BF双指针 - O(m*n)
    public int strStr_bf(String src, String pp) {
        if (pp.isEmpty()) return 0;
        int n0 = src.length(), n1 = pp.length();
        char[] s = src.toCharArray(), p = pp.toCharArray();
        for (int i = 0; i <= n0 - n1; i++) {// 枚举原串的「发起点」
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int ii = i, jj = 0;
            while (ii < n0 && jj < n1 && s[ii] == p[jj]) {
                ii++; jj++;
            }
            if (jj == n1) return i; // i是匹配起始idx，而非ii <-匹配结尾idx+1
        }
        return -1;
    }

    // 法2-1（推荐）：KMP (首位哨兵，next[]中j从0起)
    public int strStr_kmp1(String src, String pp) {
        if (pp.isEmpty()) return 0;
        int n = src.length(), m = pp.length();
        // 避免next[]统一减一：在原串、模式串/匹配串 首位加哨兵（" "）
        src = " " + src; pp = " " + pp;
        char[] s = src.toCharArray(), p = pp.toCharArray();
        // I) 构造下一跳数组 next[]，与s无关，只看p的前后缀  - O(m)
        int[] next = new int[m+1]; // ∵有哨兵 ∴长度+1
        // 构造过程 i=2nd(∵i之前有哨兵、j)，j=0 开始，i ≤ 匹配串长度 【构造 i 从 2 开始】
            // next[0]无意义，next[1]=0就是从头开始，j退无可退，无需计算next[1]，因此【next从p[2:]起始】
        // ❤ WHY j=0起？∵加入哨兵，且【jの意义为：截止i处,最长匹配的[前缀]长度】
        for (int i = 2, j = 0; i <= m; i++) {
            // ↓ 1) 哨兵后(j>0)  2) ∵哨兵 ∴j+1正串才开始
            while (j > 0 && p[i] != p[j+1]) j = next[j]; // 匹配不成功，持续回溯到下一个可能的匹配点(下一跳)
            if (p[i] == p[j+1]) j++; // 匹配成功，前缀长度++
            // ↓ 匹配成功与否(改变j)，都将最终的j赋给next[i]
            next[i] = j; // j-前缀的长度；结束本次循环，i++
        }
        // II) 匹配过程 O(n)
        //    i = 1(∵哨兵在0处)，j = 0 开始，i ≤ 原串长度 【匹配 i 从 1 开始】
        // WHY j=0起？ - ∵next[0]=0(起始位置)
        for (int i = 1, j = 0; i <= n; i++) {
            // vs 构造：将p[i]替换为s[i]
            while (j > 0 && s[i] != p[j+1]) j = next[j]; // 匹配不成功，回溯到下一跳
            if (s[i] == p[j+1]) j++; // 匹配成功的话，先j++，结束本次循环后 i++
            if (j == m) {
                return i - m; // 整一段匹配成功，直接返回下标
                // j = next[j]; // 若需要匹配多个，还需要将j后移！（q28不需要）
            }
        }
        return -1;
    }


    // 法2-1（无注释版本）：KMP (首位哨兵，next[]中j从0起)
    public int strStr_kmp1_clear(String src, String pp) {
        if (pp.isEmpty()) return 0;
        int n = src.length(), m = pp.length();
        src = " " + src; pp = " " + pp;
        char[] s = src.toCharArray(), p = pp.toCharArray();

        int[] next = new int[m+1];
        // I) 构造下一跳数组 next[]
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p[i] != p[j+1]) j = next[j];
            if (p[i] == p[j+1]) j++;
            next[i] = j;
        }
        // II) 匹配过程 O(n)
        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && s[i] != p[j+1]) j = next[j];
            if (s[i] == p[j+1]) j++;
            if (j == m) {
                return i - m;// 整一段匹配成功，直接返回下标
                // j = next[j]; // 若需要匹配多个，还需要将j后移！（q28不需要）
            }
        }
        return -1;
    }

    // 法2：next[]统一减1
    public int strStr_kmp2(String src, String pp) {
        if(pp.length() == 0) return 0;
        int n = src.length(), m = pp.length();
        char[] s = src.toCharArray(), p = pp.toCharArray();
        int[] next = new int[m];
        next[0] = -1; // 统一减1
        for (int i = 1, j = -1; i < m; i++) {
            while(j >= 0 && p[i] != p[j+1]) j = next[j]; // j可取0！∵正串
            if(p[i] == p[j+1]) j++;
            next[i] = j;
        }

        for(int i = 0, j = -1; i < n; i++) {
            while(j >= 0 && s[i] != p[j+1]) j = next[j];
            if(s[i] == p[j+1]) j++;
            if(j == m-1) return i-j;
        }
        return -1;
    }


    // 类比ACW831_KMP，多次匹配子串，打印所有结果
    public void strStr_KMP_multiple_match(String src, String pp) {
        if (pp.isEmpty()) System.out.println(0);
        int n = src.length(), m = pp.length();
        src = " " + src; pp = " " + pp; // 勿忘！
        char[] s = src.toCharArray(), p = pp.toCharArray();
        List<Integer> res = new ArrayList<>();

        int[] next = new int[m+1];
        // I) 构造下一跳数组 next[]
        for (int i = 2, j = 0; i <= m; i++) {
            while (j > 0 && p[i] != p[j+1]) j = next[j];
            if (p[i] == p[j+1]) j++;
            next[i] = j;
        }

        for (int i = 1, j = 0; i <= n; i++) {
            while (j > 0 && s[i] != p[j+1]) j = next[j];
            if (s[i] == p[j+1]) j++;
            if (j == m) {
                // 匹配子串，打印在s中的起始下标（末尾s_i - 子串长度m）
                res.add(i-m); // 匹配串的起始下标
                j = next[j];// 若需要【匹配多个】，还需要将j前移！（q28不需要）
                // ↑ 准备匹配下一个：回溯到'好前缀'，否则下一轮pp[j+1]越界(j==m)！
            }
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        String src = "ababacaba", pattern = "aba";
        q28_implement_strstr sol = new q28_implement_strstr();
        sol.strStr_KMP_multiple_match(src, pattern);
    }
}
