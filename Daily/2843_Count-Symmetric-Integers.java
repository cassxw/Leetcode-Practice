class Solution {

    // Checks if a num is symmetric
    private boolean isSymmetric(int num) {
        String numStr = String.valueOf(num);
        int length = numStr.length();

        // First, numbers with odd digits cannot be symmetric by definition
        if (length % 2 != 0) {
            return false;
        }

        // Calculate sums for first half and second half of digits.
        int firstSum = 0;   // Sum of first n/2 digits.
        int secondSum = 0;  // Sum of last n/2 digits.
        int count = 0;

        // Iterate through each digit once, adding to appropriate sum.
        for (char ch : numStr.toCharArray()) { // O(d), where d = digit count (max 5)
            if (count < length/2) {
                firstSum += ch;
            } else {
                secondSum += ch;
            }

            count++;
        }

        return firstSum == secondSum;
    }

    public int countSymmetricIntegers(int low, int high) {
        
        // Math (Enumeration)

        // low = positive int.
        // high = positive int.

        // An int x (with 2n digits) is "symmetric"...
        // if the sum of the first n digits = sum of the last n digits of x.
        // Numbers with an odd number of digits are never.

        // Return the number of symmetric integers in the range [low, high].

        // high <= 10^4
        // So, brute force could be worthwhile.

        // EDGE CASE: Optional optimisation
        // Can start from max(low, 11) since numbers < 11 can't be symmetric.
        low = Math.max(low, 11);

        int count = 0;

        // Check each number in range for symmetry.
        for (int i = low; i <= high; i += 1) { // O(n)
            if (isSymmetric(i)) {
                count++;
            }
        }

        return count;

        // O(nd)
    }
}