class Solution:
    def isPalindrome(self, s: str) -> bool:
        
        # Two Pointers (String)

        # "Palindrome" = reads the same forward and backward.

        # - Convert all uppercase to lowercase.
        s = s.lower()

        # - Remove all non-alphanumeric characters.
        new_s = ""
        for ch in s:
            if ch.isalnum():
                new_s += ch

        # Given s, return true if it is a palindrome; false, otherwise.
        n = len(new_s)

        left = 0
        right = n-1

        while (left < n-1) and (right >= 0):
            if (new_s[left] != new_s[right]):
                return False

            left += 1
            right -= 1

        return True
