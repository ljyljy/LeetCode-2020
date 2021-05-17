package Other.math;

public class q13_roman_to_integer {
    private int[] val = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
    private String[] str = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    public int romanToInt(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0, j = 0; i < str.length && j < n; i++) {
            int cv = val[i];
            String cs = str[i];
            int size = cs.length();
            while (j + size <= n && s.substring(j, j + size).equals(cs)) {
                ans += cv;
                j += size;
            }
        }
        return ans;
    }
}
