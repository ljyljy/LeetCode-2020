package String;//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1:
//
// 输入: 121
//输出: true
//
//
// 示例 2:
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
//
//
// 示例 3:
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
//
//
// 进阶:
//
// 你能不将整数转为字符串来解决这个问题吗？
// Related Topics 数学


//leetcode submit region begin(Prohibit modification and deletion)

public class q9_valid_palindrome {

    public boolean isPalindrome(String s) {
        //高层次(主干)逻辑。
        // 1.filter out number & char
        // 2.reverse and compare
        String filteredS = _filterNonNumberAndChar(s);
        String reversedS = _reverseString(filteredS);

        return reversedS.equalsIgnoreCase(filteredS);
    }

    private String _reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private String _filterNonNumberAndChar(String s) {
        return s.replaceAll("[^A-Za-z0-9]", "");
    }


//leetcode submit region end(Prohibit modification and deletion)

}
