package Binary_Search;

public class q278_first_bad_version {
    public class Solution extends VersionControl {
        // 二分[L, R-1(mid)], [mid+1, end]
        public int firstBadVersion(int n) {
            int start = 1, end = n;
            while (start < end) {
                int mid = start + (end - start) / 2; // TLE溢出: start + end >> 1;
                if (isBadVersion(mid)) { // True：往左找
                    end = mid;
                } else start = mid + 1;
            }
            return start;
        }
        // TLE原因：mid加法溢出（start=2126753390, end=1702766719）
        public int firstBadVersion_2(int n) {
            int start = 1, end = n;
            while (start + 1 < end) {
                int mid = start + (end - start) / 2; // TLE: start + end >> 1;
                if (isBadVersion(mid)) { // True：往左找
                    end = mid;
                } else start = mid;
            }
            if (isBadVersion(start)) return start;
            // if (isBadVersion(end)) return end;
            return end;
        }
    }
}
