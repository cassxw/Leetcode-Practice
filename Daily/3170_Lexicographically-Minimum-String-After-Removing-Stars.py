class Solution:
    def clearStars(self, s: str) -> str:
        
        # Hash Table (String, Stack, Greedy, Heap)

        # s = string.
        # May contain any number of * chars <= can be none.
        # Remove all *s.

        # While there is a *...
        # - Delete the leftmost * and the smallest non-* char to its left.
        #   If there are several smallest chars, you can delete any.

        # Return the lexicographically-smallest string
        # after removing all * chars.

        # Main stack to simulate chars in final string.
        stack = [] 

        # Tracks positions of each char in stack.
        char_positions = defaultdict(list)

        # Maintains stack chars in sorted order for quick min access.
        sorted_chars = []

        for ch in s:

            if (ch != '*'):
                # Add char to stack.
                stack.append(ch)

                # Maintain sorted list of chars for fast access to smallest.
                insort(sorted_chars, ch)

                # Record index in stack for removal tracking/
                char_positions[ch].append(len(stack) - 1)

            else:
                # If there are no chars to remove...
                if not sorted_chars:
                    # Skip.
                    continue

                # Get smallest char from sorted list.
                smallest = sorted_chars[0]

                # Pop most recent occurrence of that char.
                idx = char_positions[smallest].pop()

                # Mark as deleted.
                stack[idx] = None

                # Remove one instance of this char from sorted list.
                del sorted_chars[bisect_left(sorted_chars, smallest)]

                # If there are no more of this char...
                if not char_positions[smallest]:
                    # Remove from tracking map.
                    del char_positions[smallest]

        # Build final result by skipping chars marked as deleted.
        return ''.join(ch for ch in stack if ch is not None)
