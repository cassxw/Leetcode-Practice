// Helper class for Gene Queue entries.
// Stores a gene string and how many mutations it took to reach it.
class Pair {
    String geneString;
    int mutationCount;

    Pair(String geneString, int mutationCount) {
        this.geneString = geneString;
        this.mutationCount = mutationCount;
    }
}

class Solution {
    public int minMutation(String startGene, String endGene, String[] bank) {
        
        // Hash Table (String, BFS)

        // startGene, endGene = Gene Strings, where each char in ['A', 'C', 'G', 'T'].
        // bank = Gene bank, recording all the valid gene mutations.

        // Investigate mutations from startGene to endGene,
        // where one mutation is defined as a single character changed in the gene string.
        // A gene must be in bank to make it a valid gene string.

        // Return the minimum number of mutations
        // needed to mutate from startGene to endGene.
        // If there is none, return -1.

        // Convert bank into a Hash Table => O(1).
        Set<String> bankSet = new HashSet<>(Arrays.asList(bank));

        // If endGene is not in the bank...
        if (!bankSet.contains(endGene)) {
            // Impossible to reach, i.e. mutate to.
            return -1;
        }

        // All possible characters a gene can mutate into.
        char[] genes = new char[]{'A', 'C', 'G', 'T'};

        // Initialise a Queue with startGene, for BFS: <geneString, mutationCount>
        Queue<Pair> queue = new LinkedList<>();

        // Set to keep track of visited genes, so we don’t revisit them.
        Set<String> visited = new HashSet<>();

        // Start BFS with the initial gene and 0 mutations.
        queue.offer(new Pair(startGene, 0));
        visited.add(startGene);

        while (!queue.isEmpty()) {
            // Remove the front of the queue to explore its neighbors.
            Pair currentGene = queue.poll();
            String geneString = currentGene.geneString;
            int mutationCount = currentGene.mutationCount;

            // If we reached the target gene...
            if (geneString.equals(endGene)) {
                // Return how many mutations it took to reach.
                return mutationCount;
            }

            // Convert current gene string into a char array for easy mutation.
            char[] geneChars = geneString.toCharArray();

            // Try mutating each character in the gene string.
            for (int i = 0; i < geneChars.length; i++) {

                // Save the original character.
                char original = geneChars[i];

                // Try replacing it with all other gene letters.
                for (char c : genes) {

                    // Skip if it is the same letter.
                    if (c == original) {
                        continue;
                    }

                    geneChars[i] = c;
                    String mutated = new String(geneChars);

                    // If this mutation is valid and has not been visited yet...
                    if (bankSet.contains(mutated) && !visited.contains(mutated)) {
                        // Mark as visited, and add to queue to be explored later.
                        visited.add(mutated);
                        queue.offer(new Pair(mutated, mutationCount + 1));
                    }
                }

                // Restore original character before moving to next one.
                geneChars[i] = original;
            }
        }

        // No valid path has been found.
        return -1;
    }
}