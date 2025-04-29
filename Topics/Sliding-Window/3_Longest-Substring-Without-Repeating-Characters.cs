public class Solution {
    public int LengthOfLongestSubstring(string s) {
        
        // Hash Table (String, Sliding Window)

        // s = string.
        int sLen = s.Length;

        // Find length of the longest substring w/o duplicate characters.
        
        // HashSet to store unique characters within the current window.
        HashSet<char> set = new HashSet<char>();

        int left = 0;   // Left pointer of sliding window.
        int maxLen = 0; // Stores length of longest substring found.

        // Iterate through the string with right pointer.
        for (int right = 0; right < sLen; right++) {

            // If character at right already exists in the set,
            // shrink the window from the left until removed.
            while (set.Contains(s[right])) {
                set.Remove(s[left]);
                left++;
            }

            // Add current character to the set.
            set.Add(s[right]);

            // Update max length if current window is larger than previous.
            maxLen = Math.Max(maxLen, right - left + 1);
        }

        // Return length of longest substring w/o repeating characters.
        return maxLen;
    }
}