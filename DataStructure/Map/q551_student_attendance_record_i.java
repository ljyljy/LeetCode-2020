package DataStructure.Map;

public class q551_student_attendance_record_i {
    public boolean checkRecord(String s) {
        int n = s.length();
        char[] chs = s.toCharArray();

        int k = 0, cntA = 0;
        for (int i = 0; i < n; i++) {
            char ch = chs[i];
            if (ch == 'A') cntA++;
            if (cntA >= 2) return false;

            // 连续 3 天或 3 天以上的迟到（'L'）-- 直接false
            char tmp_ch = ch;
            while (tmp_ch == 'L') {
                k++;
                if (k >= 3) return false;
                if (i + k < n) tmp_ch = chs[i+k];
                else break;
            }
            k = 0;
        }

        return true;

    }
}
