class Solution {
    public long mostPoints(int[][] questions) {
        
        // Array (and Dynamic Programming)

        // questions = 2D Array, where questions[i] = [points_i, brainpower_i].
        int n = questions.length;

        // Questions must be processed in order, and each has points and brainpower.
        // Make a decision whether to solve or skip each one.
        // Solving question[i] will earn points_i, but you will be unable to solve each of the next brainpower_i questions.

        // For each question, decide whether to solve or skip it.

        // Return the maximum points that you can earn for the exam.

        // EDGE CASE:
        // If there is only one question, the maximum would be to solve it.
        if (n == 1) {
            return questions[0][0];
        }

        // Create an array to store the maximum points starting from each question.
        long[] F = new long[n];

        // BASE CASE:
        // For starting on only the last question, we can only take its points.
        F[n-1] = questions[n-1][0];

        /*
        Recurrence Relation, where F[i] is the maximum score when starting the exam with questions[i].
        
        F[i] = max(
                questions[i][0] + F[i + questions[i][1] + 1],  // if we solve question i
                F[i + 1]                                       // if we skip question i
            )
        */ 

        // Fill F array from right to left, i.e. end of exam to front.
        // Ensures that when we make a decision at i, 
        // we already know the optimal solutions for all starts after i.
        for (int i = n-2; i >= 0; i--) { // O(n-1) = O(n), for each question.

            // If we solve question i...
            // - Gain question[i][0] points.
            // - Skip the next question[i][1] questions due to brainpower.
            // - i.e. Continue from question (i + questions[i][1] + 1).
            long solvePoints = questions[i][0];
            if (i + questions[i][1] + 1 < n) {
                solvePoints += F[i + questions[i][1] + 1];
            }

            // If we skip quetion i...            
            // - Gain 0 points.
            // - Continue from question (i+1).
            long skipPoints = F[i+1];

            // Take the maximum of solving or skipping question i, to get the most points.
            F[i] = Math.max(solvePoints, skipPoints);
        }

        return F[0];  
    }
}