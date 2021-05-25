package DP;

public class q664_strange_printer {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] f = new int[n][n];
        for (int i = n-1; i >= 0; i--) {
            f[i][i] = 1;
            for (int j = i+1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    f[i][j] = f[i][j-1];
                else {
                    int min_ = 0x3f3f3f3f; // Integer.MAX_VALUE >> 1;
                    for (int k = i; k < j; k++) {
                        min_ = Math.min(min_, f[i][k] + f[k+1][j]);
                    }
                    f[i][j] = min_;
                }
            }
        }
        return f[0][n-1];
    }
}
