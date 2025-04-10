import java.util.*;

class Solution {
    public boolean isValid(String s) {
        
        // String (Stack)

        // s = string, that contains just 
        //     '(', ')', '{', '}', '[' and ']'.

        // Return if s is valid. It is valid if:
        // (1) Open brackets must be closed by the same type of brackets.
        // (2) Open brackets must be closed in the correct order.
        // (3) Each close bracket has a corresponding open bracket of the same time.

        // Edge Case: If odd length, then one is missing.
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // If ch is an opening parenthesis,
            // push it's pairing closing onto the stack.
            if (ch == '(') {
                stack.push(')');
            }
            else if (ch == '{') {
                stack.push('}');
            }
            else if (ch == '[') {
                stack.push(']');
            }
            
            // ch is not an opening parenthesis.
            // - If it doesn't match the closing at the top of the stack => INVALID.
            // - If the stack is currently empty => INVALID.
            else if (stack.isEmpty() || stack.pop() != ch) {
                return false;
            }
        }

        return stack.isEmpty();
    }
}