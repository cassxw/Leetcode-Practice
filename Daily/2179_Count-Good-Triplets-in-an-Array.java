class Solution {
    public long goodTriplets(int[] nums1, int[] nums2) {

        // Array (Binary Search, Divide and Conquer, Merge Sort)

        // nums1, nums2 = integer arrays of length n
        // Both are permutations of [0, 1, ..., n-1].
        int n = nums1.length; // = nums2.length

        // A "good" triplet...
        // (1)  3 distinct values.
        // (2)  All present in increasing index order in both nums1 and nums2.
        //      e.g.    pos1_v = index of some value v in nums1.
        //              pos2_v = index of some value v in nums2.
        //              then, (x, y, z) is constrained by 0 <= x,y,z <= n-1
        //              such that
        //                  pos1_x < pos1_y < pos1_z &&
        //                  pos2_x < pos2_y < pos2_z

        // Return the total number of "good" triplets.

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // BRUTE FORCE: MLE (77/148)
        // First, find all triplets that satisfy pos1_x < pos1_y < pos1_z.
        // Then, check that they satisfy pos2_x < pos2_y < pos2_z.
        
        // List<List<Integer>> answer = new ArrayList<>();

        // // Generate all possible triplets from nums1 in increasing index order.
        // for (int left = 0; left < n-2; left++) {
        //     for (int mid = left+1; mid < n-1; mid++) {
        //         for (int right = mid+1; right < n; right++) {
        //             List<Integer> triplet = new ArrayList<>();
        //             triplet.add(nums1[left]);
        //             triplet.add(nums1[mid]);
        //             triplet.add(nums1[right]);
        //             answer.add(triplet);
        //         }
        //     }
        // }

        
        // int count = 0;

        // // Convert nums2 to ArrayList for indexOf operation.
        // List<Integer> nums2List = new ArrayList<>();
        // for (int num : nums2) {
        //     nums2List.add(num);
        // }

        // // Iterate through answer, checking which ones also satisfy the positions in nums2.
        // for (List<Integer> triplet : answer) {

        //     // Verify if elements appear in the same relative order in nums2.
        //     if  ( (nums2List.indexOf(triplet.get(0)) < nums2List.indexOf(triplet.get(1))) &&
        //         (nums2List.indexOf(triplet.get(1)) < nums2List.indexOf(triplet.get(2))) ) {
        //         count++;
        //     }
        // }

        // return count;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // OPTIMISED SOLUTION: Still MLE (107/148)
        // Create position arrays to store where each value appears in nums1 and nums2.
        // int[] pos1 = new int[n];
        // int[] pos2 = new int[n];
        
        // // Record positions of each value in both arrays.
        // for (int i = 0; i < n; i++) {
        //     pos1[nums1[i]] = i;
        //     pos2[nums2[i]] = i;
        // }

        // long count = 0;

        // // For each middle element, i, in our range of possible values.
        // for (int i = 0; i < n; i++) {

        //     // Current value in nums1.
        //     int val = nums1[i];

        //     // Position of current value in nums2.
        //     int pos_in_nums2 = pos2[val];

        //     // Determine left:
        //     // 1. Appear before current element in nums1.
        //     // 2. Appear before current element's position in nums2.
        //     long leftCount = 0;
        //     for (int j = 0; j < i; j++) {
        //         if (pos2[nums1[j]] < pos_in_nums2) {
        //             leftCount++;
        //         }
        //     }

        //     // Determine right:
        //     // 1. Appear after current element in nums1.
        //     // 2. Appear after current element's position in nums2.
        //     long rightCount = 0;
        //     for (int j = i+1; j < n; j++) {
        //         if (pos2[nums1[j]] > pos_in_nums2) {
        //             rightCount++;
        //         }
        //     }

        //     // For each valid left, we can pair it with each valid right.
        //     count += (leftCount * rightCount);
        // }

        // return count;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // So, apparently this LC needed to use a Fenwick Tree, AKA Binary Indexed Tree.
        // Well, now that's added to my knowledge base! :)

        // The position of each value in nums2.
        int[] pos2 = new int[n];

        // For each position in nums2, store the position of that value in nums1.
        int[] reversedIndexMapping = new int[n];

        // Record the position of each value in nums2.
        for (int i = 0; i < n; i++) {
            pos2[nums2[i]] = i;
        }
    
        // For each position i in nums1, store what position that value appears at in nums2.
        // Helps track relative ordering between arrays.
        for (int i = 0; i < n; i++) {
            reversedIndexMapping[pos2[nums1[i]]] = i;
        }

        // Initialise Fenwick tree to efficiently count elements to the left.
        FenwickTree tree = new FenwickTree(n);

        long res = 0;

        // Process each value in order of its appearance in nums2.
        // Ensures we maintain relative ordering in nums2.
        for (int value = 0; value < n; value++) {

            // Get position of current value in nums1.
            int pos = reversedIndexMapping[value];

            // Query tree to count valid elements to the left in nums1
            // that appear before current position in both arrays.
            int left = tree.query(pos);

            // Mark current position as processed.
            tree.update(pos, 1);
            
            // Calculate valid elements to the right:
            // Total possible right elements (n-1-pos) minus the
            // elements processed so far (value) that weren't counted in left.
            int right = (n - 1 - pos) - (value - left);

            // Each left element can pair with each right element.
            res += (long) left * right;
        }

        return res;
    }
}

/**
 * Fenwick Tree (Binary Indexed Tree)
 * Used for efficient prefix sum queries and point updates.
 * Operations:
 * - update: Add value to an index.
 * - query: Get sum of all values from index 0 to given index.
 * Both operations take O(log n) time.
 */
class FenwickTree {

    // Internal array to store tree values.
    private int[] tree;

    /**
     * Initialise Fenwick tree with given size.
     * Size + 1 is used due it being 1-indexed.
     */
    public FenwickTree(int size) {
        tree = new int[size + 1];
    }

    /**
     * Add delta to value at given index.
     * Uses least significant bit (LSB) to update all relevant nodes.
     * @param index The position to update (0-based).
     * @param delta The value to add.
     */
    public void update(int index, int delta) {
        // Convert to 1-based indexing.
        index++;

        // Move to next relevant index using LSB.
        while (index < tree.length) {
            tree[index] += delta;
            index += index & -index;
        }
    }

    /**
     * Get sum of all values from index 0 to given index.
     * Uses least significant bit (LSB) to traverse parent nodes.
     * @param index The position to query up to (0-based).
     * @return Sum of values from 0 to index.
     */
    public int query(int index) {
        // Convert to 1-based indexing.
        index++;

        int res = 0;
        while (index > 0) {
            res += tree[index];

            // Move to parent node using LSB.
            index -= index & -index;
        }
        
        return res;
    }
}