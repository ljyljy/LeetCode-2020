import java.lang.reflect.Array;
import java.util.*;

public class q49_group_anagrams_1star {
    // 法1：排序数组分类 -- 时间O(NKlogK), 空间O(NK)
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> res = new HashMap<>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca); // O(KlogK)
            String key = String.valueOf(ca); // 勿忘将char[]转为Str！
            if (!res.containsKey(key))
                res.put(key, new ArrayList());
            res.get(key).add(s); // ❤
        }
        return new ArrayList(res.values()); // ❤
    }

        // 法2：计数数组分类 -- 时间O(NK), 空间O(NK)
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List> map = new HashMap<>();
        int[] cnt = new int[26];
        for (String s : strs) {
            Arrays.fill(cnt, 0);
            for (char c : s.toCharArray()) cnt[c - 'a']++;

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; ++i) {
                sb.append('#');// 因为怕>9的两位数字无法分割
                sb.append(cnt[i]);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(s);
        }
        return new ArrayList(map.values());
    }


    }
