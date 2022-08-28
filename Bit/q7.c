// 判断溢出！和java不同！ 类比q8
bool overflow(int res) {
//    return (res * 10 / 10 != res); // JAVA
    return (res < INT_MIN / 10) || (res > INT_MAX / 10);
}
int reverse(int x){
    int res = 0;
    while (x) {
        if (overflow(res)) {
            return 0;
        }
        res = res * 10 + x % 10;
        x /= 10;
    }
    return res;
}
