public class Solution {
    public string RobotWithString(string s) {

        // Hash Table (String, Stack, Greedy)

        // s = string.
        int n = s.Length;
        
        // A robot currently holds an empty string t.
        
        // Apply one of the following operations until s and t are both empty:
        // (1) Remove first char of s and give to robot. Robot appends this char to t.
        // (2) Remove last char of t and give to robot. Robot writes this char on paper.

        // Return the lexicographically-smallest string that can be written on paper.
        
        // Precompute smallest char from current index to end.
        // minChar[i] will store smallest character from s[i] to s[n - 1].
        char[] minChar = new char[n];
        minChar[n-1] = s[n-1];

        // Fill minChar array with smallest char from i to end.
        for (int i = n - 2; i >= 0; i--)
        {
            // Take the smaller of s[i] and minChar[i+1].
            minChar[i] = (char)Math.Min(s[i], minChar[i + 1]);
        }

        // Stack to represent the temporary holding string t.
        Stack<char> stack = new Stack<char>();

        // StringBuilder to construct the final result (written characters).
        StringBuilder result = new StringBuilder();

        int index = 0;

        // Simulate the robot's operations.
        while (index < n)
        {
            // Take first char from s and push it to stack (robot's hand).
            stack.Push(s[index]);

            // While top of stack can be safely written to the result
            // (i.e. it's <= the smallest remaining char in the rest of the input),
            // pop it from the stack and append it to the result
            while (stack.Count > 0 && (index == n - 1 || stack.Peek() <= minChar[index + 1]))
            {
                result.Append(stack.Pop());
            }

            index++;
        }

        // If anything remains in the stack, write it out (in order)
        while (stack.Count > 0)
        {
            result.Append(stack.Pop());
        }

        return result.ToString();
    }
}
