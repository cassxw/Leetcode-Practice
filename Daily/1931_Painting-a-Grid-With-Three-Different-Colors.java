class Solution {
    public int colorTheGrid(int m, int n) {

        // Dynamic Programming

        // m, n = integers.

        // mxn grid where each cell is initially white.
        // A cell can be painted Red/Green/Blue.
        // All cells must be painted.

        // Return the number of ways to colour the grid,
        // with no two adjacent cells having the same colour.
        // Since answer can be very large, return modulo 10^9 + 7.
        int MOD = 1_000_000_007;

        // Generate all valid vertical col colourings
        // that have no two vertically adj cells with the same colour.
        List<int[]> validCols = new ArrayList<>();
        generateValidColumns(m, new int[m], 0, validCols);

        // Build a transition map between valid cols.
        // Two cols are compatible if they have no horizontal match.
        Map<String, List<String>> transitions = buildTransitions(validCols);

        // Initialise DP; represents the number of ways to reach a col with a given coloruing.
        Map<String, Integer> dp = new HashMap<>();
        for (int[] col : validCols) {
            dp.put(arrayToString(col), 1); // First col for now.
        }

        // Iterate over each col from 1 to n-1,
        // updating DP by transitioning from previous valid states.
        for (int i = 1; i < n; i++) {

            Map<String, Integer> newDp = new HashMap<>();
            
            // For each previous col colouring,
            // add count to all valid next col colourings.
            for (String prev : dp.keySet()) {
                for (String next : transitions.get(prev)) {
                    newDp.put(next, (newDp.getOrDefault(next, 0) + dp.get(prev)) % MOD);
                }
            }

            // Move to next col state.
            dp = newDp;
        }

        // Sum all valid final col configurations to get the result.
        int total = 0;
        for (int val : dp.values()) {
            total = (total + val) % MOD;
        }

        return total;
    }

    // Helper method to generate all valid vertical col colourings.
    // Ensures no two adj cells in a col have the same colour.
    void generateValidColumns(int m, int[] curr, int idx, List<int[]> result) {

        if (idx == m) {
            // Store a valid col colouring.
            result.add(curr.clone());
            return;
        }

        for (int color = 0; color < 3; color++) {

            if (idx > 0 && curr[idx - 1] == color) {
                // Skip if same colour as the cell above.
                continue;
            }

            curr[idx] = color;
            generateValidColumns(m, curr, idx + 1, result);
        }
    }

    // Helper method to build a transition map between valid cols.
    // Col A can transition to Col B if they differ in all corresponding rows.
    Map<String, List<String>> buildTransitions(List<int[]> validCols) {

        Map<String, List<String>> map = new HashMap<>();

        for (int[] a : validCols) {
            String sa = arrayToString(a);

            for (int[] b : validCols) {
                boolean ok = true;

                // Check if Col A & B differ in every row, i.e. no horizontal conflict.
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == b[i]) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    map.computeIfAbsent(sa, k -> new ArrayList<>()).add(arrayToString(b));
                }
            }
        }

        return map;
    }

    // Utility method to convert col colouring to a string key for maps.
    String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i : arr) {
            sb.append(i);
        }

        return sb.toString();
    }
}