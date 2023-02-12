// 判断溢出！和java不同！ 类比q8
bool overflow(int res, int mod) {
    //    return (res * 10 / 10 != res); // JAVA
    return res < INT_MIN / 10 || res > INT_MAX / 10
            || (res == INT_MAX / 10 && mod > 7);
}

int reverse(int x){
    int y = 0;
    while (x) {
        if (overflow(y, x % 10)) {
            return 0;
        }
        y = y * 10 + x % 10;
        x /= 10;
    }
    return y;
}