class Solution {
    public List<List<Integer>> combine(int n, int k) {
        
        // Backtracking

        // n, k = integers.

        // Return all possible combintations of k numbers,
        // chosen from the range [1, n].

        // List to hold all valid combinations.
        List<List<Integer>> result = new ArrayList<>();

        // Start backtracking from 1.
        backtrack(1, n, k, new ArrayList<>(), result);

        return result;
    }

    private void backtrack(int start, int n, int k, List<Integer> current, List<List<Integer>> result) {

        // BASE CASE:
        // If current combination has k numbers, add to result.
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try each number starting from start up to n.
        for (int i = start; i <= n; i++) {
            // Choose the number i.
            current.add(i);

            // Explore further, with i+1 as new start.
            backtrack(i + 1, n, k, current, result);

            // Backtrack, i.e. remove last number and try next one.
            current.remove(current.size() - 1);
        }
    }
}