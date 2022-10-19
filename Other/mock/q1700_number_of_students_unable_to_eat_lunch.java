package Other.mock;

public class q1700_number_of_students_unable_to_eat_lunch {
    public int countStudents(int[] students, int[] sandwiches) {
        int n = sandwiches.length;
        int[] cnts = new int[2];
        for (int stu: students) {
            cnts[stu]++;
        }
        for (int i = 0; i < n; i++) {
            if (--cnts[sandwiches[i]] < 0) {
                return n - i;
            }
        }
        return 0;
    }
}
