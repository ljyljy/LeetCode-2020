package Array.Sweep_Line;

public class q1109_corporate_flight_bookings {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];
        for (int[] booking: bookings) {
            int first = booking[0], last = booking[1];
            int seats = booking[2];
            for (int i = first-1; i < last; i++) { // idx: No.-1
                res[i] += seats;
            }
        }
        return res;
    }
}
