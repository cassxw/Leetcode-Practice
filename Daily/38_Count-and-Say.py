class Solution:

    def RLE(self, num: str) -> str:
        """
        Run-Length Encoding (RLE) helper function that converts
        a string into its count-and-say sequence.
        e.g. "111221" -> "312211" (three 1s, two 2s, one 1)
        
        Args:
            num (str): Input string to be encoded.
            
        Returns:
            str: The count-and-say encoding of the input string.
        """
        ans = []
        count = 1

        for i in range(1, len(num)):

            # If the current char is running.
            if num[i] == num[i-1]:
                count += 1

            else:
                # Current char run has ended.
                # Add it to the ans array.
                ans.append(f"{count}{num[i-1]}")
                count = 1
        
        # Handle last run.
        ans.append(f"{count}{num[-1]}")

        # Return concat.
        return ''.join(ans)
        
    def countAndSay(self, n: int) -> str:
        
        # String

        # n = positive integer.

        # The count-and-say sequence =
        # ...a sequence of digit strings defined by the recursive formula:
        #       (1) countAndSay(1) = "1"
        #       (2) countAndSay(n) = run-length encoding of countAndSay(n-1)

        # RLE =
        # ...a string compression method that works by replacing consecutive
        #    identical chars (repeated 2+ times) with the concatenation
        #    of the char and the char count, i.e length of run.
        # e.g.  "3322251"
        #       - Replace "33" with "23".
        #       - Replace "222" with "32".
        #       - Replace "5" with "15".
        #       - Replace "1" with "11".
        #       = compresssed string is "23321511".

        # Return the nth element of the count-and-say sequence.

        # BASE CASE:
        if n == 1:
            return "1"

        # countAndSay(n) = RLE of countAndSay(n-1)
        # RLE of countAndSay(x) = RLE(countAndSay(x))
        return self.RLE(self.countAndSay(n-1))
        