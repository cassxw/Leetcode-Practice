class Solution {
    public int countLargestGroup(int n) {
        
        // Hash Table (Math)

        // n = integer

        // Each number [1; n] is grouped according to the sum of its digits.

        // Return the number of groups that have the largest group size.

        // Create a Hash Table to track frequency of digit sums.
        // => <digit sum, count of numbers with that digit sum>.
        Map<Integer, Integer> map = new HashMap<>();

        // Iterate across every number in the range.
        for (int num = 1; num <= n; num++) {

            // Calculate the digit sum of current num.
            int sum = 0;

            for (char digit : (""+num).toCharArray()) {
                // Convert char to int value.
                sum += digit - '0';
            }

            // Add to hash table: <sum, +1>
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        // Find the largest frequency.
        int maxSize = Collections.max(map.values());

        // Count how many groups have this maximum size.
        int count = 0;
        for (int value : map.values()) {
            if (value == maxSize) {
                count++;
            }
        }

        // Return the number of groups with the largest size.
        return count;
    }
}