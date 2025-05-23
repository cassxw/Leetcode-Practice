public class Solution {
    public long MaximumValueSum(int[] nums, int k, int[][] edges) {
        
        // Array (Dynamic Programming, Greedy, Bit Manipulation, Tree, Sorting)

        // An undirected tree with n nodes, numbered 0 to n-1.
        // Note: *undirected* => can perform operation on any edge.

        // nums = non-negative integer array.
        // nums[i] represents the value of node i.
        int n = nums.Length;

        // k = positive int.

        // edges = 2D integer array .
        // edges[i] = [ui, vi]
        // ...indicates an edge between nodes ui and vi in the tree.

        // Alice wants the sum of values of tree nodes to be max,
        // for which she can perform the following operation any no. of times:
        // - Choose any edge [u, v] connecting the nodes u and v, and update as follows:
        //      -- nums[u] = nums[i] XOR k
        //      -- nums[v] = nums[v] XOR k

        // Return the maximum possible sum of the values Alice
        // can achieve by performing the operation any number of times.

        // First, calculate base sum (if we don't flip any values at all).
        long totalSum = 0;

        // Each node can be flipped (XORed) or left as-is.
        // Compute the "gain" from flipping each node.
        List<int> gains = new List<int>();

        foreach (int num in nums)
        {
            int xored = num ^ k;

            // Start with sum of all original values.
            totalSum += num;

            // Gain is the change in value if this node is XORed.
            gains.Add(xored - num);
        }

        // Sort descending for positive gains,
        // so we pick the best ones first.
        gains.Sort((a, b) => b.CompareTo(a));

        // No. of positive gains we’re taking.
        int count = 0;

        // Total gain from flipping selected nodes.
        long gainSum = 0;

        // Count how many positive gains and sum.
        foreach (var gain in gains)
        {
            if (gain > 0)
            {
                gainSum += gain;
                count++;
            }
            else
            {
                // Stop once gains stop being positive.
                break;
            }
        }

        // If count is even, all good.
        // Allowed to do any number of operations, but each one flips TWO nodes,
        // so total number of flipped nodes must be EVEN.
        if (count % 2 == 0)
        {
            return totalSum + gainSum;
        }

        // Otherwise, we have an ODD number of flips; Must drop one node from our flip.
        // So, find the smallest gain (positive or negative).
        long minAdjustment = long.MaxValue;
        for (int i = 0; i < gains.Count; i++)
        {
            minAdjustment = Math.Min(minAdjustment, Math.Abs(gains[i]));
        }

        return totalSum + gainSum - minAdjustment;
    }
}