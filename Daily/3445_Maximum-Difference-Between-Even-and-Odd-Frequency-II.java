class Solution {
    public int maxDifference(String s, int k) {
        
        // String (Sliding Window, Enumeration, Prefix Sum)

        // k = integer.
        // s = string, contains more than 2 distinct chars.
        int n = s.length();

        // Return the maximum difference between the frequency of two chars,
        // freq[a] - freq[b], in a substring subs of s, such that:
        // (1) subs has a size >= k.
        // (2) a has an odd frequency in subs.
        // (2) b has an even frequency in subs.

        // Collect distinct chars in s.
        Set<Character> distinctChars = new HashSet<>();
        for (char c : s.toCharArray()) {
            distinctChars.add(c);
        }

        Integer maxDiff = null;

        // Iterate over all pairs of distinct chars (a, b).
        for (char a : distinctChars) {
            for (char b : distinctChars) {

                if (a == b) {
                    continue;
                }

                // Prefix sums for freq of a and b.
                int[] prefixCountA = new int[n + 1];
                int[] prefixCountB = new int[n + 1];

                // Prefix parity states (2 bits: bit0 for a, bit1 for b)
                int[] prefixParity = new int[n + 1];

                // Compute prefix sums and parity for each position.
                for (int i = 0; i < n; i++) {

                    prefixCountA[i + 1] = prefixCountA[i] + (s.charAt(i) == a ? 1 : 0);
                    prefixCountB[i + 1] = prefixCountB[i] + (s.charAt(i) == b ? 1 : 0);

                    int parityA = prefixCountA[i + 1] % 2;
                    int parityB = prefixCountB[i + 1] % 2;

                    prefixParity[i + 1] = parityA | (parityB << 1);
                }

                // Prepare lists to track candidates by parity state.
                List<int[]>[] candidates = new List[4];
                List<Integer>[] prefixMins = new List[4];
                for (int i = 0; i < 4; i++) {
                    candidates[i] = new ArrayList<>();
                    prefixMins[i] = new ArrayList<>();
                }
                
                // Initialise candidates for empty prefix.
                candidates[0].add(new int[]{0, 0});
                prefixMins[0].add(0);
                
                int desiredSubstrParity = 1;

                // Slide window through string starting from size k.
                for (int i = k; i <= n; i++) {
                    int j = i - k;

                    // Values at start of current window.
                    int countB = prefixCountB[j];
                    int diff = prefixCountA[j] - prefixCountB[j];
                    int parity = prefixParity[j];
                    
                    // Get lists for current parity state.
                    List<int[]> list = candidates[parity];
                    List<Integer> mins = prefixMins[parity];
                    
                    // Binary search to find position of countB in list
                    int pos = Collections.binarySearch(list, new int[]{countB, 0},
                        Comparator.comparingInt(arr -> arr[0]));
                    
                    if (pos >= 0) {

                        // Update diff if smaller value found.
                        if (diff < list.get(pos)[1]) {
                            list.get(pos)[1] = diff;

                            // Rebuild prefix mins from this position.
                            for (int idx = pos; idx < mins.size(); idx++) {
                                int minSoFar = (idx == 0) ? list.get(0)[1] : Math.min(mins.get(idx - 1), list.get(idx)[1]);
                                mins.set(idx, minSoFar);
                            }
                        }
                    }
                    else {
                        // Insert new entry at correct position.
                        pos = -pos - 1;
                        list.add(pos, new int[]{countB, diff});
                        
                        // Update prefix mins for insertion.
                        int minSoFar = (pos == 0) ? diff : Math.min(mins.get(pos - 1), diff);
                        mins.add(pos, minSoFar);
                        
                        // Update subsequent prefix mins.
                        for (int idx = pos + 1; idx < mins.size(); idx++) {
                            mins.set(idx, Math.min(mins.get(idx - 1), list.get(idx)[1]));
                        }
                    }
                    
                    // Current parity at window end.
                    int currentParity = prefixParity[i];

                    // Calculate parity needed to satisfy conditions.
                    int targetStartParity = currentParity ^ desiredSubstrParity;
                    List<int[]> targetList = candidates[targetStartParity];
                    List<Integer> targetMins = prefixMins[targetStartParity];
                    
                    // Binary search to find upper bound index for prefixCountB[i].
                    int upperBound = Collections.binarySearch(targetList, new int[]{prefixCountB[i], 0}, 
                        Comparator.comparingInt(arr -> arr[0]));

                    if (upperBound < 0) {
                        upperBound = -upperBound - 1;
                    }
                    
                    if (upperBound > 0) {
                        // Calculate potential max difference using prefix min.
                        int minStartDiff = targetMins.get(upperBound - 1);
                        int currentVal = (prefixCountA[i] - prefixCountB[i]) - minStartDiff;

                        if (maxDiff == null || currentVal > maxDiff) {
                            maxDiff = currentVal;
                        }
                    }
                }
            }
        }

        if (maxDiff == null) {
            // INVALID.
            return -1;
        }
        
        return maxDiff;
    }
}