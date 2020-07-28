package Divide_Conquer;

import java.util.*;

public class q17_letter_combinations_of_a_phone_number {
    private List<String> res = new LinkedList<>();
    private Map<Character, String> map = new HashMap<Character, String>(){{
        put('2', "abc"); put('3', "def"); put('4', "ghi"); put('5', "jkl");
        put('6', "mno"); put('7', "pqrs"); put('8', "tuv"); put('9', "wxyz");
    }};

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0)
            return new ArrayList<>();

        backtrack("", digits, 0);
        return res;
    }

    private void backtrack(String s, String digits, int idx) {
        // 1. 出口 （idx： level）
        if (idx == digits.length()) {
            res.add(s);
            return;
        }
        // 2. 递归分解 drill down
        String letters = map.get(digits.charAt(idx));
        for (int i = 0; i < letters.length(); ++i) {
            backtrack(s + letters.charAt(i), digits, idx+1);
        }
        // 3. 撤销 / 回溯 reverse
        // ∵用了for循环，每次递归完毕会自动回溯 ∴本模块无需再额外写
    }
}
