package Divide_Conquer;

import java.util.*;

public class q17_letter_combinations_of_a_phone_number {
    private List<String> res = new LinkedList<>();
    private Map<Character, String> map = new HashMap<Character, String>(){{
        put('2', "abc"); put('3', "def"); put('4', "ghi"); put('5', "jkl");
        put('6', "mno"); put('7', "pqrs"); put('8', "tuv"); put('9', "wxyz");
    }};

    public List<String> letterCombinations1(String digits) {
        if (digits == null || digits.length() == 0)
            return new ArrayList<>();

        backtrack("", digits, 0);
        return res;
    }

    private void backtrack(String path, String digits, int idx) {
        // 1. 出口 （idx： level）
        if (idx == digits.length()) {
            res.add(path);
            return;
        }
        // 2. 递归分解 drill down
        String letters = map.get(digits.charAt(idx));
        for (int i = 0; i < letters.length(); ++i) { // 而非i+1！！ ↓
            backtrack(path + letters.charAt(i), digits, idx+1);
        }
        // 3. 撤销 / 回溯 reverse
        // ∵用了for循环，每次递归完毕会自动回溯 ∴本模块无需再额外写
    }

    // 写法2：
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        dfs(digits, 0, "", res);
        return res;
    }

    private void dfs(String digits, int idx, String path, List<String> res) {
        if (path.length() == digits.length()) { // 树深=digits长度
            res.add(path);
            return;
        }
        String letters = map.get(digits.charAt(idx));
        for (int i = 0; i <letters.length(); i++) { // i>=【0】: 遍历当前数字idx对应的所有字母
            // idx+1: 此处递归的下一层是idx+1(下一个数字对应的字母组合)，而i+1仍处于当前层的下一个字母（意义不同）
            dfs(digits, idx+1, path + letters.charAt(i), res); // 而非i+1！！
        }
    }
}
