class Solution {
    public List<Integer> lexicalOrder(int n) {

        // DFS (Trie)

        // n = integer.

        // Return all numbers in the range [1, n],
        // sorted in lexicographical order.

        // Must be O(n) time and O(1) space.
        // => Depth-First Search

        List<Integer> result = new ArrayList<>();
        
        // Start DFS from numbers 1 to 9, i.e. roots of our tree.
        for (int i = 1; i <= 9; i++)
        {
            dfs(i, n, result);
        }
        
        return result;
    }

    private void dfs(int current, int n, List<Integer> result)
    {
        // BASE CASE: If current number exceeds n...
        if (current > n)
        {
            // Stop.
            return;
        }

        // Add current number to result list.
        result.add(current);
        
        // Try to explore all children of current by appending digits 0 to 9.
        for (int i = 0; i <= 9; i++)
        {
            int next = current * 10 + i;

            // If the new number is greater than n...
            if (next > n)
            {
                // Stop, since all further children will be even larger.
                break;
            }

            // Recursively explore next number.
            dfs(next, n, result);
        }
    }
}
