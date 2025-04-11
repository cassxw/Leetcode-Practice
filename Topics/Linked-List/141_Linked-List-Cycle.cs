/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     public int val;
 *     public ListNode next;
 *     public ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public bool HasCycle(ListNode head) {

        // Hash Table (Linked List, Two Pointers)
        
        // head = head of the Linked List

        /*
        A cycle in a linked list is identifiable if
        there is some node that can be reached by
        continuously following the "next" pointer.

        "pos" = index of the node that the tail's
                "next" pointer is connected to.
                It is not passed as a parameter.
                If it is -1, then nothing.
        */

        // Return if the linked list has a cycle or not.

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // 2 runners running around a track, represented by the Linked List.
        // They are at different speeds, i.e. the number of nexts.
        // If at some point they are at the same point = CIRCLE found.
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null) {
            
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }

        }

        return false;
        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    }
}