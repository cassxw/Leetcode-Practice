class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {

        // Array (Hash Table, Prefix Sum)

        // nums = integer ArrayList.
        // modulo = integer.
        // k = integer.
        int n = nums.size();

        // An "interesting subarray" is one where:
        // (1)  Let cnt be the number of indices, i,
        //      in the range [l, r] such that:
        //      nums[i] % modulo == k
        //      Then, cnt % modulo == k

        // Return an integer (long) denoting the count of "interesting subarrays" in nums.

        //---------------------------------------------------------------------------------------------------
        // Brute Force, Sliding Window Approach <= a lot of repeated calculations => TLE (609/617)
        // long interestCount = 0; 

        // // Iterate through all possible start indices (left pointer).
        // for (int left = 0; left < n; left++) {

        //     // Count for the current subarray nums[left...right].
        //     int currentCount = 0; 

        //     // Iterate through all possible end indices (right pointer) starting from left.
        //     for (int right = left; right < n; right++) {

        //         // Check the element at the 'right' index
        //         if (nums.get(right) % modulo == k) {
        //             currentCount++; // Increment count if the condition nums[i] % modulo == k is met
        //         }
                
        //         // Now, check if the count for the subarray nums[left...right] satisfies the second condition
        //         if (currentCount % modulo == k) {
        //             interestCount++; // If yes, this is an interesting subarray
        //         }
        //     }
        // }
        
        // return interestCount;
        //---------------------------------------------------------------------------------------------------

        // PREFIX SUM APPROACH
        // -    Find subarrays nums[l..r] where within it, the count of elements, x,
        //      such that x % modulo == k, satisfies count(l, r) % modulo == k.
        
        // prefixCount[i] is the count of elements nums[j] such that nums[j] % modulo == k
        // for 0 <= j < i, i.e. the count *up to* index i-1.
        
        // Then, count for the subarray nums[l..r] is count(l, r) = prefixCount[r+1] - prefixCount[l].
        // => (prefixCount[r+1] - prefixCount[l]) % modulo == k.

        int[] prefixCounts = new int[n + 1]; // Size n+1 to handle counts up to index n
        prefixCounts[0] = 0; // Count before the first element is 0.

        // Calculate the prefix counts.
        // prefixCounts[i] will store the number of elements nums[0...i-1] that satisfy nums[x] % modulo == k.
        for (int i = 1; i <= n; i++) {
            // Inherit the count from the previous prefix.
            prefixCounts[i] = prefixCounts[i - 1]; 

            // If the current element nums[i-1] satisfies the condition, increment.
            if (nums.get(i - 1) % modulo == k) {
                prefixCounts[i]++;
            }
        }

        // Now, find pairs of indices (l, r) such that 0 <= l <= r < n, where
        // (count of relevant elements in nums[l..r]) % modulo == k.
        // i.e. prefixCounts[r+1] - prefixCounts[l].
        // => (prefixCounts[r+1] - prefixCounts[l]) % modulo == k.

        // Rearranging:
        // prefixCounts[r+1] % modulo - prefixCounts[l] % modulo == k % modulo
        // prefixCounts[l] % modulo == (prefixCounts[r+1] % modulo - k % modulo + modulo) % modulo`
        
        // Iterate through the prefixCounts array, representing the end points r+1.
        // For each prefixCounts[j] (where j = r+1), look for how many previous satisfy the required modulo condition.

        long interestingCount = 0;

        // HashMap to store the frequency of each prefix count remainder.
        // <prefixCount[i] % modulo, How many times this remainder has occurred so far>
        HashMap<Integer, Integer> prefixRemFreqs = new HashMap<>();
        
        // Before processing any element, the prefix count is 0, and modulo is 0.
        prefixRemFreqs.put(0, 1); 

        // Iterate through prefixFreq.
        // j represents the index r+1 in our formula: prefixCounts[r+1] - prefixCounts[l].
        for (int j = 1; j <= n; j++) {

            // Get the current prefix count up to index j-1.
            int currentPrefixCount = prefixCounts[j];

            // Calculate the remainder of current prefix count.
            int currentRemainder = currentPrefixCount % modulo; 
            
            // Calculate target remainder needed a valid starting prefix count prefixCounts[l].
            // i.e. (P[j] - k + modulo) % modulo = (P[r+1] - k + modulo) % modulo
            int targetRemainder = (currentRemainder - k + modulo) % modulo; 
            
            // How many times have we previously seen a prefix count prefixCounts[l],
            // such that prefixCounts[l] % modulo == targetRemainder?
            int countOfMatchingStarts = prefixRemFreqs.getOrDefault(targetRemainder, 0);
            
            // Add this count to our total result.
            interestingCount += countOfMatchingStarts;
            
            // Update frequency map: We have now encountered the currentRemainder one more time.
            prefixRemFreqs.put(currentRemainder, prefixRemFreqs.getOrDefault(currentRemainder, 0) + 1);
        }

        // Return the final calculated count of "interesting subarrays".
        return interestingCount; 
    }
}