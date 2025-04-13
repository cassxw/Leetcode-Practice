class Solution {

    long mod = 1000000007;

    // Need to use fast exponentiation due to constraints
    public long fastPow(long x, long n) {
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return (1/x * fastPow(1/x, -(n-1))) % mod;
        }

        long result = fastPow(x, n/2);
        result = (result * result) % mod;
        
        if (n % 2 == 1) {
            result = (result * x) % mod;
        }
        
        return result;
    }

    public int countGoodNumbers(long n) {
        
        // Math (Recursion)

        // n = number of digits.

        // "good" if...
        // (1) At even indices, the digit is even: {0, 2, 4, 6, 8} - 5
        // (2) At odd indices, the digit is prime: {2, 3, 5, 7}    - 4

        // Given an integer n, return the total number of
        // "good" digit strings of length n.
        // - Since the answer might be large, return in modulo 10^9 + 7.

        // If n is even: x^n = (x^2)^(n/2)
        // If n is odd:  x^n = x.x^(n-1)

        // The number of even indices: floor((n+1) / 2)
        // The number of odd indices: floor(n/2)

        // Therefore, the total number of "good" numbers:
        // = (number of even).(number of odd)
        // = 5^(floor((n+1) / 2)) . 4^(floor(n/2))

        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;
        
        long result = 1;
        result = fastPow(5, evenCount) % mod;
        result = (result * fastPow(4, oddCount)) % mod;
        
        return (int) result;
    }
}