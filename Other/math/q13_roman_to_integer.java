package Other.math;

public class q13_roman_to_integer {
    private int[] val = new int[]{
            1000,900,500,400,
            100,90,50,40,
            10,9,5,4,1};
    private String[] str = new String[]{
            "M","CM","D","CD",
            "C","XC","L","XL",
            "X","IX","V","IV","I"};

    public int romanToInt(String s) {
        int n = s.length(), cnt = val.length;
        int res = 0;
        for (int i = 0, j = 0; i < n && j < cnt; ) { // i:遍历s，j:遍历map
            String curS = str[j];
            int curV = val[j], len = curS.length();
            while (i+len <= n // 非if：如"III"=3
                    && s.substring(i, i+len).equals(curS)) {
                res += curV;
                i += len;
            }
            j++;
        }
        return res;
    }
}
