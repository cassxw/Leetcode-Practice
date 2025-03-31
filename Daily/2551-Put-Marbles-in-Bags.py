class Solution(object):
    def putMarbles(self, weights, k):
        """
        :type weights: List[int]
        :type k: int
        :rtype: int
        """

        # Array (Greedy, Sorting, Heap, Priority Queue)

        # weights = int array, where weights[i] is the weight of the ith marble.
        n = len(weights) # The number of marbles.
        
        # k = the number of bags of marbles to be divided into; k <= weights.length.

        # Divide n marbles into k bags, such that:
        #   (1) No bag is empty.
        #   (2) If the ith and jth marble are in a bag, then all marbles with an index [i:j] should also.
        #       i.e. consecutive.
        #   (3) A bag of marbles [i:j] has a cost of weights[i] + wieghts[j].

        # The score after marble distribution is the sum of the costs of all the k bags.

        # Return the difference between the maximum and minimum scores among marble distributions.
        # i.e. max possible score - min possible score.

        # For k bags of marbles, (k-1) split points are needed.

        # BASE CASE:
        # If k is 1, no splits are needed, i.e. only one bag.
        if k == 1:
            return 0
            
        # Calculate costs for all possible split points.
        # Each split point cost is the sum of the marbles on either side.
        split_costs = []
        for i in range(n-1):
            # weights[i] = end of one bag.
            # weights[i+1] = start of next bag.
            split_costs.append(weights[i] + weights[i+1])
            
        # Sort the split costs, to find min and max.
        split_costs.sort() # O(n.logn)
        
        # Minimum score: choose (k-1) smallest split costs.
        # Maximum score: choose (k-1) largest split costs.
        min_score = sum(split_costs[:k-1])
        max_score = sum(split_costs[-(k-1):])
        
        return max_score - min_score
