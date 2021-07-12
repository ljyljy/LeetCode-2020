package Two_Pointers;

public class q680_validPalindrome_ii {
    public boolean validPalindrome(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        while (i < j) {
            char lf = s.charAt(i), rt = s.charAt(j);
            if (lf == rt) {
                i++; j--;
            }else {  // 遇到非回文的中心子串
                return checkSub(s, i+1, j) || checkSub(s, i, j-1);
            }
        }
        return true;
    }

    private boolean checkSub(String s, int i, int j) {
        while (i < j) {
            char lf = s.charAt(i), rt = s.charAt(j);
            if (lf == rt) {
                i++; j--;
            }else return false;
        }
        return true;
    }
}
