public class Solution {
    public string SimplifyPath(string path) {
        
        // String (Stack)

        // path = Absolute path for a Unix file system,
        //        always begins with a slash '/'.

        // Transform this absolute path into its simplified canonical path.
        // Canonical Rules:
        //   - "." means current directory => skip it.
        //   - ".." means move up to parent directory => pop from stack if possible.
        //   - Multiple slashes "//" are treated as a single "/".
        //   - The result must start with a single "/" and have no trailing slash.

        Stack<string> stack = new Stack<string>();
        
        // Split the path by "/", ignoring empty parts.
        string[] parts = path.Split('/', StringSplitOptions.RemoveEmptyEntries);

        foreach (string part in parts) {

            if (part == ".") {
                // "." means current directory - no change, skip it.
                continue;
            
            } else if (part == "..") {
                // ".." means go up one directory - pop from stack if not already at root.
                if (stack.Count > 0) {
                    stack.Pop();
                }
            
            } else {
                // Valid directory name - push onto stack.
                stack.Push(part);
            }
        }

        // The stack now contains the correct path components in reverse order.
        // Join them with "/" and prepend a "/" to indicate the root.
        return "/" + string.Join("/", stack.Reverse());
    }
}