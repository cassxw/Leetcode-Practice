class Solution {
    public int longestPalindrome(String[] words) {
        
        // Array (Hash Table, String, Greedy, Couting)

        // words = string array.
        // Each element in words is a string of two lowercase letters.

        // Create the longest possible palindrome by selecting some elements from words,
        // and concatenating them in any order.
        // Each element can be selected at most once.

        // Return the length of the longest possible palindrome.
        // If impossible, return 0.

        // Use a HashMap to store frequency of each word.
        Map<String, Integer> count = new HashMap<>();
        for (String word : words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }

        // Flag for if we can place a single palindrome in the centre.
        boolean hasCentre = false;

        int longestLength = 0;

        // Iterate through the unique word in words.
        for (String word : count.keySet()) {

            // If this word has already been processed (by its reverse)...
            if (count.get(word) == 0) {
                // Skip it.
                continue;
            }

            String reverseWord = new StringBuilder(word).reverse().toString();
            int currentCount = count.get(word);

            // If the word is a palindrome...
            if (word.equals(reverseWord)) {

                // Add length contributed by pairs of these palindrome words.
                // Each pair adds 4 to the length.
                longestLength += (currentCount / 2) * 4;

                // If there is an odd one out...
                if (currentCount % 2 == 1) {
                    // One can be used as the centre of the final palindrome.
                    hasCentre = true;
                }

                // Mark as processed.
                count.put(word, 0);
            }
            else {
                // The word is not a palindrom.
                
                // If its reverse exists in the map and has not been processed...
                if (count.containsKey(reverseWord) && count.get(reverseWord) > 0) {
                    int reverseCount = count.get(reverseWord);

                    // Determine how mnay pairs can be formed.
                    int pairs = Math.min(currentCount, reverseCount);

                    // Each pair adds 4 to the length.
                    longestLength += pairs * 4;

                    // Mark both as processed.
                    count.put(word, 0);
                    count.put(reverseWord, 0);
                }
            }
        }

        // If we found any palindrome words with an odd count...
        if (hasCentre) {
            // We can place it in the centre, adding 2.
            longestLength += 2;
        }

        return longestLength;
    }
}