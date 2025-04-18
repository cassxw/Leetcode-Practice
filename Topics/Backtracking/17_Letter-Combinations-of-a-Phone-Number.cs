public class Solution {
    public IList<string> LetterCombinations(string digits) {
        // Hash Table (String, Backtracking)

        // digits = string, containing digits from [2;9].
      
        // Return all possible letter combinations that
        // the number could represent

        // Result list to store final combinations.
        List<string> results = new List<string>();

        // EDGE CASE: If input is empty, return empty list.
        if (string.IsNullOrEmpty(digits)) {
            return results;
        }

        // Create a Hash Table to store all the mappings,
        // just like on the telephone buttons.
        // e.g. "2" = "abc", "3" = "def", ..., "9" = "wxyz"
        Dictionary<char, string> phoneMap = new Dictionary<char, string> {
            {'2', "abc"},
            {'3', "def"},
            {'4', "ghi"},
            {'5', "jkl"},
            {'6', "mno"},
            {'7', "pqrs"},
            {'8', "tuv"},
            {'9', "wxyz"}
        };

        // Helper function for backtracking.
        void Backtrack(string combination, int index) {

            // BASE CASE:
            // If we have formed a combination as long as digits, return.
            if (index == digits.Length) {
                results.Add(combination);
                return;
            }

            // Get the current digit.
            char digit = digits[index];

            // Look up the letters that the digit maps to.
            string letters = phoneMap[digit];

            // Loop through each letter and recurse.
            foreach (char letter in letters) {
              
                // Add the letter to the current combination and recurse to next digit.
                Backtrack(combination + letter, index + 1);
            }
        }

        // Start the backtracking process from the first digit.
        Backtrack("", 0);

        return results;
    }
}
