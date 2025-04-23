public class Solution {
    public string AddBinary(string a, string b) {
    
        // Math (String, Bit Manipulation, Simulation)

        // a, b = binary strings.

        // Return the sum of a+b as a binary string.

        // BIT ADDITION LOGIC:
        // 0 + 0 = 0       => sum: 0, carry: 0
        // 0 + 1 = 1       => sum: 1, carry: 0
        // 1 + 1 = 10      => sum: 0, carry: 1
        // 1 + 1 + 1 = 11  => sum: 1, carry: 1

        // Note: No leading zeroes.

        // Start from end of both strings, i.e. right-to-left from LSB.
        int aIndex = a.Length - 1;
        int bIndex = b.Length - 1;
    
        // Stores carry between bit additions.
        int carry = 0;

        // Used to build our final string.
        StringBuilder result = new StringBuilder();
        
        // Continue looping while there's still a bit to process,
        // in either string or a remaining carry.
        while (aIndex >= 0 || bIndex >= 0 || carry > 0) {
            
            // Get the current bit from a if in range, otherwise 0.
            int bitA = 0;
            if (aIndex >= 0) {
                // Convert char to int.
                bitA = a[aIndex] - '0';
            }

            // Get the current bit from b if in range, otherwise 0.
            int bitB = 0;
            if (bIndex >= 0) {
                // Convert char to int.
                bitB = b[bIndex] - '0';
            }

            // Add bits and carry.
            int sum = bitA + bitB + carry;

            // Current bit of result is sum % 2 (0 or 1), inserted to front.
            // A little costly, can be optimised by using Append() => Reverse().
            result.Insert(0, (sum % 2).ToString());

            // Carry is sum / 2 (either 0 or 1).
            carry = sum / 2;

            // Move to the next bits on the left.
            aIndex--;
            bIndex--;
        }

        // Final binary result as a string.
        return result.ToString();
    }
}