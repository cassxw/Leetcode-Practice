public class Solution {
    public int[] FindEvenNumbers(int[] digits) {
        
        // Array (Hash Table, Sorting, Enumeration)

        // digits = non-distinct integer array, where each element is a digit.

        // Find all unique integers that follow the following:
        // (1) It consists of the concatenation of 3 elements from digits, in any order.
        // (2) It does not have leading zeroes.
        // (3) It is even.

        // Return a sorted array of the unique integers.

        // Note:
        // Range of possible answers is all even numbers between [100; 999].

        // HashSet to store unique even numbers that can be formed.
        // => Avoids duplicate entries.
        HashSet<int> result = new HashSet<int>();

        // Count frequency of each digit in digits.
        int[] freq = new int[10]; // [0; 9]
        foreach (int digit in digits)
        {
            freq[digit]++;
        }

        // Iterate over all 3-digit numbers from 100 to 999,
        // incrementing by 2 to get only even numbers.
        for (int num = 100; num <= 999; num += 2)
        {
            // Extract individual digits of current number.
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int ones = num % 10;

            // Create temp frequency array for current number.
            int[] tempFreq = new int[10];
            tempFreq[hundreds]++;
            tempFreq[tens]++;
            tempFreq[ones]++;

            // Flag to check if current number can be formed with available digits.
            bool isValid = true;

            // Check if current number uses more instances
            // of each digit than are available in the input.
            for (int d = 0; d <= 9; d++)
            {
                if (tempFreq[d] > freq[d])
                {
                    // I digit is overused => INVALID.
                    isValid = false;
                    break;
                }
            }

            // If the current number is valid...
            if (isValid)
            {
                // Add it to result set.
                result.Add(num);
            }
        }

        // Convert HashSet to array.
        int[] output = new int[result.Count];
        result.CopyTo(output);

        // Sort final result in ascending order.
        Array.Sort(output);

        return output;
    }
}