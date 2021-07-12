package Two_Pointers;

public class q125_valid_palindrome {
    public boolean isPalindrome(String s) {

        if (s == null || s.length() == 0) return false;
        int n = s.length();
        int i = 0, j = n - 1;
        char[] chars = s.toCharArray();

        while (i < j) {
            while (i < j && !check(s.charAt(i))) i++;
            while (i < j && !check(s.charAt(j))) j--;

            char lf = Character.toLowerCase(s.charAt(i));
            char rt = Character.toLowerCase(s.charAt(j));
            if (i < j && lf != rt) return false;
            // String lf = (""+s.charAt(i)).toLowerCase();
            // String rt = (""+s.charAt(j)).toLowerCase();
            // if (i < j && !lf.equals(rt))
            //     return false;

            i++; j--;
        }
        return true;
    }

    private boolean check(char ch) {
        return Character.isDigit(ch) || Character.isLetter(ch);
//        return ('0' <= ch && ch <= '9') || ('a' <= ch && ch <= 'z') || ('A' <= ch && ch <= 'Z');
    }
}
