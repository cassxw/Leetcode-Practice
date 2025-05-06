class Solution:
    def buildArray(self, nums: List[int]) -> List[int]:

        # Array (Simulation)

        # nums = permutation integer array.
        n = len(nums)

        # Build an array, ans, of the same length,
        # where ans[i] = nums[nums[i]] for each index.
        
        # Initialise ans with zeroes, same length as nums.
        ans = [0] * n

        # Iterate through each index in nums.
        for i in range(n):
            # For each i, set ans[i] to the value at the index nums[i] in nums.
            ans[i] = nums[nums[i]]

        # Return the constructed ans array.
        return ans