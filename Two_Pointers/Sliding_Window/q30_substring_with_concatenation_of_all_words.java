package Two_Pointers.Sliding_Window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 对比q76、567等不同：
// 1) 两个 Map.key都是String，而非Character；
// 2) 全局仅一个map：need
// 3) 外层多个for，遍历str中的匹配起点[i], i++
//      （注意rt<=n, 即i <= n - cnt * len, 类比q15/16/18，但可取等！左闭右开！）：
//      窗口left从i起, right=i+所有words长度；
//      for内建立window，对应每个单词（每轮重置），
//          若匹配上， lf += word_len，直至lf == rt则整个窗口成功匹配多个单词
//          否则，即seg不匹配need || seg匹配数量足够，就break（重新返回for，执行i++）
// 4) 窗口大小固定！只需要window.add, 无需remove/减小窗口（left++）！
public class q30_substring_with_concatenation_of_all_words {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int n = s.length(), len = words[0].length(), cnt = words.length;
        Map<String, Integer> need = new HashMap<>();
        for (String word:words)
            need.put(word, need.getOrDefault(word, 0)+1);
        for (int i = 0; i <= n - cnt * len; i++) { //类比q18,28，但可以【取等（∵rt开区间）】
            int lf = i, rt = lf + cnt * len; // 滑窗大小=cnt*len
            Map<String, Integer> window = new HashMap<>();
            while (lf < rt) {
                String str2Add = s.substring(lf, lf + len);
                if (!need.containsKey(str2Add) || need.get(str2Add).equals(window.get(str2Add))) {
                    break; // 说明频数已经够了，不能再新加入subMap了，但是此时又多了，故break
                    // 注意window.get可能返回null！
                    // 解决：1) win.getOrDefault 或 2) need.get在前，保证调用equals的对象非null！
                }
                window.put(str2Add, window.getOrDefault(str2Add, 0)+1);
                lf += len;
            }
            if (lf == rt) res.add(i);
        }
        return res;
    }
}
