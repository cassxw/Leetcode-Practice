// Node of the Trie structure.
class TrieNode {
    // Marks if this node represents the end of a valid word.
    public boolean isWord;

    // Size 26 represents lowercase English letters (a-z).
    // Each index maps to a letter: 0->a, 1->b, ..., 25->z.
    // A null value means that letter path doesn't exist.
    public TrieNode[] children = new TrieNode[26];
    
    // Constructor initialises a new node with default values.
    public TrieNode() {}
}

class WordDictionary {

    // Root of the Trie; serves as entry point for all operations.
    private TrieNode root;

    public WordDictionary() {
        // Instantiate the root node.
        root = new TrieNode();
    }
    
    // Adds a word into the Trie.
    public void addWord(String word) {

        // ws (working state) keeps track of current node as we build the trie.
        TrieNode ws = root;

        // Traverse the word character by character.
        for (int i = 0; i < word.length(); i++) {
            // Map character to array index.
            char ch = word.charAt(i);
            int index = ch - 'a';

            // If there is no node at this index...
            if (ws.children[index] == null) {
                // Create a new one.
                ws.children[index] = new TrieNode();
            }

            // Move to the next node.
            ws = ws.children[index];
        }

        // Mark the final node as a complete word.
        ws.isWord = true;
    }
    
    // Searches for a word in the Trie; '.' can match any character.
    public boolean search(String word) {
        // Start the recursive search from root and index 0.
        return searchInNode(word, 0, root);
    }

    // Helper method to recursively search the word with support for wildcards.
    private boolean searchInNode(String word, int index, TrieNode node) {

        // BASE CASE: Reached the end of the word.
        if (index == word.length()) {
            return node.isWord;
        }

        char ch = word.charAt(index);

        // If current character is not a wildcard...
        if (ch != '.') {
            // Proceed normally.
            int charIndex = ch - 'a';
            TrieNode child = node.children[charIndex];

            // If no child exists for the current character...
            if (child == null) {
                // Word doesn't exist.
                return false;
            }

            // Recurse to the next character.
            return searchInNode(word, index + 1, child);

        } else {
            // Current character is '.', so try all possible children.
            for (int i = 0; i < 26; i++) {
                TrieNode child = node.children[i];

                // If child exists, recursively search the rest of the word from there.
                if (child != null && searchInNode(word, index + 1, child)) {
                    // If any path returns true, the word exists.
                    return true;
                }
            }

            // No matching paths found.
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */