import java.lang.reflect.Array;
import java.util.*;

public class tmp {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> need = new HashMap<>();
        for (String word: words) {
            need.put(word, need.getOrDefault(word, 0) + 1);
        }
        int n = s.length(), cnt = words.length, len = words[0].length();
        for (int i = 0; i <= n-cnt*len; i++) {
            int left = i, right = i + cnt * len;
            Map<String, Integer> window = new HashMap<>();
            while (left < right) {
                String seg = s.substring(left, left + len);
                if (!need.containsKey(seg) || need.get(seg).equals(window.get(seg))) {
                    break;
                }
                window.put(seg, window.getOrDefault(seg, 0) + 1);
                left += len;
            }
            if (left == right) {
                res.add(i);
            }
        }
        return res;
    }

//
//    public int minSwaps(int[] data) {
//        int n = data.length;
//        int[] sum = new int[n+1];
//        for (int i = 1; i <= n; i++) {
//            sum[i] = sum[i-1] + data[i-1];
//        }
//        int cnt_1 = sum[n];
//
//    }
//
//    public static void main(String[] args) {
//        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
//
//    }


}
