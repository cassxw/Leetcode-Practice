class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        
        // Array (Binary Search, Greedy, Queue, Sorting, Monotonic Queue)

        // tasks = integer array; the ith task requires tasks[i] strength to complete.        
        // workers = integer array; the jth worker has workers[j] strength.
        int n = tasks.length;
        int m = workers.length;

        // Each worker can only be assigned to a single task,
        // and must have a strength >= to the tasks's strengh req.
        // i.e. workers[j] >= tasks[i]

        // pills = integer.
        // strength = integer.

        // We have <pills> number of magical pills
        // that will increase a worker's strength by <strength>.
        // Decide which workers should take it, but each can only be given at most one.

        // Return the maximum number of tasks that can be completed.

        // Assign the first some k smallest tasks to workers, using a Heap.
        // Use Binary Search on this k value => necessity to sort.
        Arrays.sort(tasks); // by difficulty (ascending).
        Arrays.sort(workers); // by strength (ascending).

        int low = 0;
        int high = Math.min(n, m); // Cannot assign more tasks than available workers or tasks.
        int maxNum = 0;

        // Binary search to find the maximum number of tasks that can be assigned.
        while (low <= high) {
            // Midpoint: number of tasks to check.
            int k = low + (high - low) / 2;

           // Can we assign k tasks?
            if (canAssign(k, tasks, workers, pills, strength)) {
                // Valid assignment found.
                // Then, fewer number tasks is possible.
                // Shift to upper half to work with higher values of k.
                maxNum = k;
                low = k + 1;
            } else {
                // Then, k number of tasks is not possible.
                // Shift to lower half to work with lower values of k.
                high = k - 1;
            }
        }

        // Return the max number of tasks that can be completed.
        return maxNum;
    }

    // Helper method to check if k tasks can be assigned to workers with given pills.
    private boolean canAssign(int k, int[] tasks, int[] workers, int pills, int strength) {
        int m = workers.length;

        if (k == 0) return true; // No tasks = always possible.
        if (k > m) return false; // More tasks than workers = impossible.

        // TreeMap to simulate a multiset of workers' strengths.
        TreeMap<Integer, Integer> workerPool = new TreeMap<>();
        
        // Use k strongest workers from the sorted workers array.
        for (int i = m - k; i < m; i++) {
            workerPool.put(workers[i], workerPool.getOrDefault(workers[i], 0) + 1);
        }

        int pillsAvailable = pills;

        // Go from hardest to easiest task among the k hardest tasks.
        for (int i = k - 1; i >= 0; i--) {
            int task = tasks[i];

            // Case 1: Assign strongest worker who can do task without a pill.
            Integer strongEnough = workerPool.floorKey(Integer.MAX_VALUE);
            if (strongEnough != null && strongEnough >= task) {
                removeWorker(workerPool, strongEnough);

            // Case 2: Use pill on weakest possible worker who can reach task strength.
            } else {
                if (pillsAvailable == 0) return false;

                Integer withPill = workerPool.ceilingKey(task - strength);
                if (withPill == null) return false;

                removeWorker(workerPool, withPill);
                pillsAvailable--;
            }
        }
        
        // All k tasks assigned.
        return true;
    }

    // Helper to safely decrement worker count or remove from map.
    private void removeWorker(TreeMap<Integer, Integer> map, int strength) {
        if (map.get(strength) == 1) {
            map.remove(strength);
        } else {
            map.put(strength, map.get(strength) - 1);
        }
    }
}