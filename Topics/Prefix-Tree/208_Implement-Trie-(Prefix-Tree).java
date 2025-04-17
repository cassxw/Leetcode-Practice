// Design => Trie, or Prefix Tree
// Used to efficiently store and retrieve keys in a dataset of strings.
// Used in autocomplete and spellchecker.

// Initial solution using ArrayList to store all words resulted in TLE.
// Even using a HashSet/HashMap would be less efficient because:
// - For searching full words: HashSet O(L) where L is word length, similar to Trie.
// - For prefix search: HashSet would need O(N*L) to check all N words for prefix match.
// - Trie gives us O(L) for prefix search too!

class TrieNode {
    // Marks if this node represents the end of a valid word.
    public boolean isWord;

    // Size 26 represents lowercase English letters (a-z).
    // Each index maps to a letter: 0->a, 1->b, ..., 25->z.
    // A null value means that letter path doesn't exist.
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
}

class Trie {

    private TrieNode root;

    public Trie() {
        // Root node is empty, serves as starting point for all words.
        root = new TrieNode();
    }
    
    public void insert(String word) {
        // ws (working state) keeps track of current node as we build the trie.
        TrieNode ws = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            // Convert char to array index (e.g., 'a'->0, 'b'->1).
            // If path doesn't exist, create new node.
            if (ws.children[ch - 'a'] == null) {
                ws.children[ch - 'a'] = new TrieNode();
            }

            // Move to the next node.
            ws = ws.children[ch - 'a'];
        }

        // Mark the final node as a complete word.
        ws.isWord = true;
    }
    
    public boolean search(String word) {
        // ws (working state) keeps track of current node as we traverse the trie.
        TrieNode ws = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            // Convert char to array index (e.g., 'a'->0, 'b'->1).
            // If path doesn't exist, word cannot exist.
            if (ws.children[ch - 'a'] == null) {
                return false;
            }

            ws = ws.children[ch - 'a'];
        }

        // Must check isWord because the path existing isn't enough.
        // e.g. if "app" exists, searching for "ap" should return false.
        return ws.isWord;
    }
    
    public boolean startsWith(String prefix) {
        // Almost identical to search, but we don't need to check isWord.
        // If we can traverse the entire prefix, it exists in the trie.
        TrieNode ws = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);

            // Convert char to array index (e.g., 'a'->0, 'b'->1).
            // If path doesn't exist, word cannot exist.
            if (ws.children[c - 'a'] == null) {
                return false;
            }

            ws = ws.children[c - 'a'];
        }

        // Don't check isWord - we only care if the prefix path exists.
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */