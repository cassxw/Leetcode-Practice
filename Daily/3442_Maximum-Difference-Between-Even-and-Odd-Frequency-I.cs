public class Solution {
    public int MaxDifference(string s) {
        
        // Hash Table (String, Counting)

        // s = string, of lowecase chars.

        // diff = freq(a1) - freq(a2)

        // Return the maximum differnce between the freq of
        // characters a1 and a2 in the string, such that:
        //  (1) a1 has an odd frequency.
        //  (1) a2 has an even frequency.

        // Store the frequency of each letter in s.
        int[] frequencies = new int[26];
        foreach (char c in s)
        {
            frequencies[c - 'a']++;
        }

        int maxOddFreq = -1;
        int minEvenFreq = int.MaxValue;
        
        bool foundOdd = false;
        bool foundEven = false;

        // Find Max Odd and Min Even Frequencies by iterating through frequencies.
        foreach (int freq in frequencies)
        {
            // Skip chars that are not in s.
            if (freq == 0)
            {
                continue;
            }

            // If the frequency is odd...
            if (freq % 2 != 0)
            {
                // Candidate for a1, which we want to maximise.
                maxOddFreq = Math.Max(maxOddFreq, freq);
                foundOdd = true;
            }
            else
            {
                // Frequency is even.
                // Candidate for a2, which we want to minimise.
                minEvenFreq = Math.Min(minEvenFreq, freq);
                foundEven = true;
            }
        }

        // If we did not find both an odd and an even frequency char...
        if (!foundOdd || !foundEven)
        {
            // No valid (a1, a2) pair exists.
            return -1; 
        }

        return maxOddFreq - minEvenFreq;
    }
}