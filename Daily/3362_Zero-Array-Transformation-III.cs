public class Solution {
    public int MaxRemoval(int[] nums, int[][] queries) {
        
        // Array (Greedy, Sorting, Heap, Prefix Sum)

        // nums = integer array.
        int n = nums.Length;

        // queries = 2D array, where
        //           queries[i] = [li, ri].
        int m = queries.Length;

        // For each queries[i]:
        // (1) Decrement the value at each index in range [li, ri] by at most 1.
        // (1) The amount by which the value is decremented can be chosen independently for each index.

        // "Zero Array" is an array where all elements are equal to 0.

        // Return the maximum number of elements that can be removed from queries,
        // such that nums can still be converted to a "Zero Array" using the reamining queries.
        // If impossible, return -1.

        // If nums is empty, it is already a "Zero Array".
        // That means we can safely remove all queries.
        if (n == 0)
        {
            return m;
        }

        // Calculate min number of queries needed to make nums zero.
        int minNeeded = CalculateMinSelectedQueries(nums, queries, n, m);

        if (minNeeded == -1) {
            // Impossible to make nums zero, even with best query selection.
            return -1;
        }

        // Binary Search to find max number of queries we can remove,
        // while still reducing nums to zero with the remaining queries.
        int left = 0; 
        int right = m; 
        int bestRemoval = 0; 

        while (left <= right)
        {
            int midRemoval = left + (right - left) / 2;

            if (m - midRemoval >= minNeeded)
            {
                // If keeping (m - midRemoval) queries is enough to zero nums,
                // try removing more queries.
                bestRemoval = midRemoval;
                left = midRemoval + 1;
            }
            else
            {
                // Otherwise, keep fewer removals (more queries).
                right = midRemoval - 1;
            }
        }
        
        return bestRemoval;
    }

    // Returns min number of queries needed to reduce nums a "Zero Array".
    // Returns -1 if impossible.
    private int CalculateMinSelectedQueries(int[] nums, int[][] originalQueries, int n, int m)
    {
        // Check if nums is already all zeros.
        bool allZero = true;
        for (int i = 0; i < n; i++)
        {
            if (nums[i] > 0)
            {
                allZero = false;
                break;
            }
        }

        if (allZero)
        {
            return 0;
        }

        // If nums is not all zero and there are no queries => IMPOSSIBLE.
        if (m == 0 && !allZero)
        {
            return -1;
        }
        
        // Copy and sort queries by their starting index to process sequentially.
        var sortedQueries = new int[m][];
        for (int k = 0; k < m; ++k)
        {
            sortedQueries[k] = (int[]) originalQueries[k].Clone(); 
        }
        Array.Sort(sortedQueries, (a, b) => a[0] - b[0]);

        // Max Heap storing to always pick queries that cover furthest right.
        var heap = new PriorityQueue<int, int>(Comparer<int>.Create((a, b) => b.CompareTo(a))); 
        
        int[] delta = new int[n + 1];   // Tracks changes to coverage at each index.
        int currentCoverage = 0;        // Effective coverage at current index i.
        int selectedCount = 0;          // Total queries selected from Heap so far.
        int queryIndex = 0;             // Index to add new queries starting at i.

        // Iterate through each index i in nums.
        for (int i = 0; i < n; i++)
        {
            // Update current coverage by applying previous query ends.
            currentCoverage += delta[i];

            // Add all queries that start at index i to the heap.
            while (queryIndex < m && sortedQueries[queryIndex][0] == i)
            {
                // Add end index of query to max heap.
                heap.Enqueue(sortedQueries[queryIndex][1], sortedQueries[queryIndex][1]);
                queryIndex++;
            }

            // While coverage not enough for nums[i], pick queries greedily.
            while (currentCoverage < nums[i])
            {
                // Remove any queries that no longer cover i.
                while (heap.Count > 0 && heap.TryPeek(out _, out int topEnd) && topEnd < i)
                {
                    heap.Dequeue();
                }

                // If no queries can cover this index...
                if (heap.Count == 0)
                {
                    return -1; // <= IMPOSSIBLE to satisfy nums[i].
                }
                
                // Pick query with farthest end to maximise coverage.
                int chosenEnd = heap.Dequeue();
                
                // Current query adds one decrement coverage, and for subsequent indices.
                currentCoverage++;
                selectedCount++;

                // Schedule query's coverage to stop after chosenEnd.
                if (chosenEnd + 1 <= n)
                { 
                    delta[chosenEnd + 1]--;
                }
            }
            
            // Not really necessary, but testing that coverage should always meet nums[i].
            if (currentCoverage < nums[i])
            {
                 return -1;
            }
        }

        // Return min number of queries selected.
        return selectedCount; 
    }
}
