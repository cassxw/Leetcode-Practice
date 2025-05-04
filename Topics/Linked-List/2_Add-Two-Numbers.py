# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        
        # Linked List (Math, Recursion)

        # l1, l2 = non-empty linked lists,
        #          representing non-negative integers.
        #          (stored in reverse order)

        # Add the two numbers, and retun the sum as a linked list.

        def addHelper(n1, n2, carry):
            # BASE CASE:
            # All lists exhausted and no carry.
            if (not n1) and (not n2) and (not carry):
                return None

            # Safely get values from the current nodes; default to 0 if None.
            val1 = n1.val if n1 else 0
            val2 = n2.val if n2 else 0

            # Add the two digits and any carry from the previous step.
            total = val1 + val2 + carry

            # Calculate the digit to store in the current node (units place).
            new_val = total % 10

            # Calculate the carry to pass to the next recursion (tens place).
            new_carry = total // 10

            # Create the current node with the computed digit.
            node = ListNode(new_val)

            # Move to the next digits in the lists (if they exist).
            next1 = n1.next if n1 else None
            next2 = n2.next if n2 else None

            # Recursively build the rest of the list.
            node.next = addHelper(next1, next2, new_carry)

            return node
        
        # Initial call to the recursive helper with both lists and carry = 0.
        return addHelper(l1, l2, 0)