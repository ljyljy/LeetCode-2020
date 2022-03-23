package Two_Pointers;

public class q165_compare_version_numbers {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0, j = 0, n1 = v1.length, n2 =v2.length;
        while (i < n1 && j < n2) {
            int num1 = Integer.valueOf(v1[i++]);
            int num2 = Integer.valueOf(v2[j++]);
            if (num1 < num2) return -1;
            else if (num1 > num2) return 1;
        }
        while (i < n1) {
            int num1 = Integer.valueOf(v1[i++]);
            if (num1 != 0) return 1; // v1´ó
        }
        while (j < n2) {
            int num2 = Integer.valueOf(v2[j++]);
            if (num2 != 0) return -1; // v1´ó
        }
        return 0;
    }
}
