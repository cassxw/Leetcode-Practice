public class Solution {
    public int FindKthLargest(int[] nums, int k) {
        
        // Array (Divide-and-Conquer, Sorting, Heap, Quickselect)

        // nums = integer array.
        // k = integer.
        int n = nums.Length;

        // Return the kth largest element in the array, in the sorted order.

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // BRUTE FORCE (42/42)
        // // Sort the array in ascending order.
        // Array.Sort(nums); // O(n.logn)

        // int count = 0;

        // // Loop from the end of the array (largest element) to beginning.
        // for (int i = n-1; i >= 0; i--) {
        //     count++;
            
        //     // Once we have counted k elements from the end, return that one..
        //     if (count == k) {
        //         return nums[i];
        //     }
        // }

        // // If not found (edge case safety, though never reached in valid input).
        // return -1;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        // Can you solve it without sorting?
        // => HEAP (PRIORITY QUEUE) APPROACH

        // Min-heap of size k: this will store the k largest elements seen so far.
        // The element at the top represents the kth largest.
        // Time Complexity: O(n log k), Space Complexity: O(k)
        PriorityQueue<int, int> heap = new PriorityQueue<int, int>();

        // (1) Fill the heap with the first k elements.
        //     These are our initial candidates for the top k largest.
        for (int i = 0; i < k; i++)
            heap.Enqueue(nums[i], nums[i]);

        // (2)  Process the rest of the array.
        for (int i = k; i < n; i++)

            // For each element beyond the first k, compare to top of heap.
            if (nums[i] > heap.Peek())
                // If larger, deserves to be in the top k.
                // Remove the smallest and add the new number.
                heap.DequeueEnqueue(nums[i], nums[i]);

        // The top of the heap is now the kth largest.
        return heap.Peek();
    }
}