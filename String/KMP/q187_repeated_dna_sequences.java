package String.KMP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q187_repeated_dna_sequences {
    // 法2：因此一个能够做到严格O(n)的做法是使用「字符串哈希 + 前缀和」。
//https://mp.weixin.qq.com/s?__biz=MzU4NDE3MTEyMA==&mid=2247489813&idx=1&sn=7f3bc18ca390d85b17655f7164d8e660&chksm=fd9cb20acaeb3b1cc78abf05d6fea6d093098998ce877f799ac478247604bd267fbee6fcd989&token=1342991619&lang=zh_CN#rd
    final int base = 131313;
    int[] h, p;
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        h = new int[n+1]; p = new int[n+1];
        List<String> res = new ArrayList<>();
        // 预处理-字符串哈希【类似<前缀和>】
        p[0] = 1; h[0] = 0;
        for (int i = 1; i <= n; i++) {
            p[i] = p[i-1] * base;
            h[i] = h[i-1] * base + s.charAt(i-1);
        }

        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 1; i + 10 - 1 <= n; i++) { // idx整体前移1位(-1)
//            int j = i + 10; // 滑窗末端, str[i-1, j-1]
//            int hash_ij = h[j-1] - h[i-1] * p[j-i];
//            int cnt = map.getOrDefault(hash_ij, 0) + 1;
//            if (cnt == 2) res.add(s.substring(i-1, j-1));
//            map.put(hash_ij, cnt);
//        }

        // 左闭右开str[i,j)
        for (int i = 0; i + 10 <= n; i++) {
            int j = i + 10; // 滑窗末端, str[i, j]
            int hash_ij = h[j] - h[i] * p[j-i];
            int cnt = map.getOrDefault(hash_ij, 0) + 1;
            if (cnt == 2) res.add(s.substring(i, j));
            map.put(hash_ij, cnt);
        }
        return res;
    }

    // 法1：普通O(C*n), C=10
    public List<String> findRepeatedDnaSequences0(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.isEmpty()) return res;
        int n = s.length();

        Map<String, Integer> seqCntMap = new HashMap<>();
        for (int i = 0; i+10 <= n; i++) {
            String subStr = s.substring(i, i+10);
            int subCnt = seqCntMap.getOrDefault(subStr, 0) + 1;
            if (subCnt == 2) res.add(subStr);
            seqCntMap.put(subStr, subCnt);
        }
        return res;
    }


}
