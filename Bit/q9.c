// bool overflow(int res, int mod) {
//     return res < INT_MIN / 10 || res > INT_MAX / 10
//             || (res == INT_MAX && mod > 7);
// }

bool isPalindrome(int x){
    if (x < 0) return false;
    long y = 0; // 反转后的x
    long x_ = x;
    while (x) {
        // if (overflow(y, x % 10)) {
        //     return false;
        // }
        y = y * 10 + x % 10;
        x /= 10;
    }
    return x_ == y;
}