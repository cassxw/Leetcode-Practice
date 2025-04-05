import java.util.ArrayList;
import java.util.List;

class Solution {
    public int subsetXORSum(int[] nums) {
        
        // Array (Math, Backtracking, Bit Manipulation, Combinatorics, Enumeration)

        // "XOR Total" of an array = the bitwise XOR of all its elements, or 0 if empty.

        // Given an int array nums,
        // return the sum of all XOR totals for every subset of nums.
        // (Duplicate subsets should be counted multiple times)

        // Key Knowledge: XOR in Java uses '^'.

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Getting all subsets of an array - BRUTE FORCE
        List<List<Integer>> subsets = new ArrayList<>();

        // // Add the empty subset.
        // subsets.add(new ArrayList<>());

        // // Iterate through each number in nums.
        // for (int num : nums) {

        //     // Get the current number of subsets generated so far.
        //     int currentSize = subsets.size();

        //     for (int i = 0; i < currentSize; i++) {
        //         // (1) Get an existing subset.
        //         List<Integer> existingSubset = subsets.get(i);

        //         // (2) Create a new list by copying the existing subset.
        //         List<Integer> newSubset = new ArrayList<>(existingSubset);

        //         // (3) Add the current number to the new list.
        //         newSubset.add(num);

        //         // (4) Add this new subset to our main list.
        //         subsets.add(newSubset);
        //     } 
        // }

        // Recursive approach, with backtracking.
        generateSubsets(nums, 0, new ArrayList<>(), subsets);
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        int XOR_sum = 0;

        // Iterate through each subset, and compute its XOR total to be added to sum.
        for (List<Integer> subset : subsets) { // O(n)

            int XOR_total = 0;
            
            for (int num : subset) {
                XOR_total ^= num;
            }

            XOR_sum += XOR_total;
        }

        return XOR_sum; // O(n * 2^n)
    }

    // Recursive method to generate all subsets of an integer array = O(2^n)
    private void generateSubsets(int[] nums, int index, List<Integer> subset, List<List<Integer>> subsets) {
        // BASE CASE: index is reached the end of  nums, so just add current subset and return.
        if (index == nums.length) {
            subsets.add(new ArrayList<>(subset));
            return;
        }

        // Generate all subsets with nums[index].
        subset.add(nums[index]);
        generateSubsets(nums, index + 1, subset, subsets);
        subset.remove(subset.size() - 1);

        // Generate all subsets without nums[index].
        generateSubsets(nums, index + 1, subset, subsets);
    }
}