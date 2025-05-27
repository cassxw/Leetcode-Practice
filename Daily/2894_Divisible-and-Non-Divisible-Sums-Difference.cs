public class Solution {
    public int DifferenceOfSums(int n, int m) {

        // Math

        // n, m = integers.

        // num1: Sum of all integers in range [1; n] that are not divisible by m.
        // num2: Sum of all integers in range [1; n] that are divisible by m.
        
        int num1 = 0;
        int num2 = 0;

        for (int i = 1; i <= n; i++)
        {
            if (i % m != 0)
            {
                num1 += i;
            }
            else
            {
                num2 += i;
            }
        }

        return num1 - num2;
    }
}