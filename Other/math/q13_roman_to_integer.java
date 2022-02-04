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
        int len = s.length(), cnt = val.length;
        int res = 0;
        for (int i = 0, j = 0; i < len && j < cnt; ) { // i:����s��j:����map
            String curS = str[j];
            int curV = val[j], size = curS.length();
            while (i+size <= len // ��if����"III"=3
                    && s.substring(i, i+size).equals(curS)) {
                res += curV;
                i += size;
            }
            j++;
        }
        return res;
    }
}
