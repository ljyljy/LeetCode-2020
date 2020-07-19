package Recursion;

import java.util.ArrayList;
import java.util.List;

public class q22_generate_parentheses {
    List<String> res;
    // 法1 DFS：加法
    public List<String> generateParenthesis(int n) {
        res = new ArrayList<>();
        _generate(0, 0, n, "");
        return res;
    }

    private void _generate(int left, int right, int n, String s) {
        if (left == n && right == n) {
            res.add(s);
            return;
        }
        // 0 <= right < left < n
        if (left < n) // 左括号随时都可加，只要数量不超标
            _generate(left+1, right, n, s+"(");
        if (right < left) // 右括号一定要【严格<】左括号
            _generate(left, right+1, n, s+")");
    }

    // 法2 DFS：减法
    public List<String> generateParenthesis2(int n) {
        res = new ArrayList<>();
        _generate2(n, n, "");
        return res;
    }

    private void _generate2(int left, int right, String str) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }
        // 剪枝（左括号【可以使用的个数】应严格<=右括号可以使用的个数）
        if (left > right) return;
        if (left > 0)
            _generate2(left-1, right,str+"(");
        if (right > 0)
            _generate2(left, right-1,str+")");
    }
}
