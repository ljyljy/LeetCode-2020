package Two_Pointers.Sliding_Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q30_substring_with_concatenation_of_all_words {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), len = words[0].length(), cnt = words.length;
        Map<String, Integer> map = new HashMap<>();
        for (String word:words)
            map.put(word, map.getOrDefault(word, 0)+1);
        for (int i = 0; i <= n - cnt * len; i++) {
            int lf = i, rt = lf + cnt * len; // 滑窗大小=cnt*len
            Map<String, Integer> subMap = new HashMap<>();
            while (lf < rt) {
                String curWord = s.substring(lf, lf + len);
                if (!map.containsKey(curWord) || subMap.get(curWord) == map.get(curWord)) {
                    break; // 说明频数已经够了，不能再新加入subMap了，但是此时又多了，故break
                }
                subMap.put(curWord, subMap.getOrDefault(curWord, 0)+1);
                lf += len;
            }
            if (lf == rt) res.add(i);
        }
        return res;
    }
}
