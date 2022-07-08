package Two_Pointers;

public class q680_validPalindrome_ii {
    // 法1：双指针+贪心+分治：从两侧向中间找到不等的字符，删除后判断是否回文
    public boolean validPalindrome0(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        while (i < j) {
            char lf = s.charAt(i), rt = s.charAt(j);
            if (lf == rt) {
                i++;
                j--;
            } else {  // 遇到非回文的中心子串
                return checkSub(s, i + 1, j) || checkSub(s, i, j - 1);
            }
        }
        return true;
    }

    private boolean checkSub(String s, int i, int j) {
        while (i < j) {
            char lf = s.charAt(i), rt = s.charAt(j);
            if (lf == rt) {
                i++;
                j--;
            } else return false;
        }
        return true;
    }

    // 写法2
    public boolean validPalindrome(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        while (i <= j) {
            char lf = s.charAt(i), rt = s.charAt(j);
            if (lf == rt) {
                i++;
                j--;
            } else {
                boolean flag1 = isPalindrome(s.substring(i, j)); // 子串s[i,j-1]
                boolean flag2 = isPalindrome(s.substring(i + 1, j + 1)); // 子串s[i+1,j]
                return flag1 || flag2;
            }
        }
        return true;
    }

    private boolean isPalindrome(String s) {
        StringBuilder rev_s = new StringBuilder(s).reverse();
        return s.equals(rev_s.toString());
    }
}
