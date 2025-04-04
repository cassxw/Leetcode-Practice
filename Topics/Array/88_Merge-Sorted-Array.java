class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        // Array (Two Pointers, Sorting)

        // 2 Int, Sorted Arrays
        // m = number of elements in num1
        // n = number of elements in num2

        // Merge nums1 and nums2 into a single, sorted array.
        // In-place, not returning the new array.
        // nums1 has a length of m+n to accommodate this.

        // MERGE SORT:
        // The two lists are already sorted.
        // So, it is a matter of how both lists will intertwine.

        // Edge Cases:
        // If nums1 is empty, simply copy nums2 into nums1.
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        // If nums2 is empty, no action is needed as nums1 is already sorted.
        // If either array has only one element, the merge logic will handle it correctly.

        // Key Trick:
        // Start comparing each list from its end.
        // Avoids risk of overwriting any elements.

        int nums1Pointer = m-1;
        int nums2Pointer = n-1;
        int mergePointer = m+n-1;

        // While there are elements to compare...
        while (nums2Pointer >= 0) { // O(m+n)
            
            // If nums1Pointer is still valid,
            // and current element in nums1 is greater than current element in nums2
            if ( (nums1Pointer >= 0) && (nums1[nums1Pointer] > nums2[nums2Pointer]) ) {
                nums1[mergePointer] = nums1[nums1Pointer];
                nums1Pointer--;
            }
            // If current element in nums1 is less than or equal to current element in nums2
            else {
                nums1[mergePointer] = nums2[nums2Pointer];
                nums2Pointer--;
            }

            mergePointer--;
        }
    }
}