class Solution:
    def numRabbits(self, answers: List[int]) -> int:
        
        # Array (Hash Table, Math, Greedy)

        # We asked n rabbits:
        #   "How many _other_ rabbits have the same colour as you?"

        # answers = The list of answers from n rabbits.
        n = len(answers)

        # Return the min number of rabbit that could be in the forest.

        # There are x other rabbits who look like me => (x+1) possible gruopd.
        # i.e. each rabbit does not include themself in their answer.

        # Hashmap to store <colour, no. of rabbits who said this colour>
        map = Counter(answers)
        count = 0

        for x in map: # O(n)
            # For every rabbit who said there are x other rabbits present,
            # we divide the x rabbits into (x+1) different colour groups.
            # Then, ceil => can't be less than the no. of rabbits.
            count += ceil(float(map[x]) / (x+1)) * (x+1)
        
        return int(count)