import java.util.HashMap;

class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        
        // Hash Table (String, Counting)

        // ransomNote = String
        // magazine = String

        // Return true if ransomNote can be constructed using the letters from magazine.
        // Otherwise, return false.

        // Note! Each letter in magazine can only be used once in ransomNote.

        // magazine can have other letters.
        // Because of the requirement that each magazine letter can only be used once,
        // each element in ransomNote needs to be found in magazine.

        // Create a HashMap to store the letters available in the magazine.
        HashMap<Character, Integer> magDict = new HashMap<>();

        // Iterate through all characters found in the magazine.
        for (char letter : magazine.toCharArray()) {

            // If the letter is not yet present in the HashMap, add it.
            if (!magDict.containsKey(letter)) {
                magDict.put(letter, 1);

            } else {
                // The letter is already present in the HashMap, so increment.
                magDict.put(letter, magDict.get(letter) + 1);
            }
        }

        // Iterate through all letters needed for the ransomNote.
        for (char letter : ransomNote.toCharArray()) {

            // Check if magazine contains at least one of the current letter.
            if (magDict.containsKey(letter) && magDict.get(letter) > 0) {
                // Consume it, i.e. cut it out from the magazine to be used in the ransomNote.
                magDict.put(letter, magDict.get(letter) - 1);

            } else {
                // The magazine does not contain at least one of the current letter => IMPOSSIBLE.
                return false;
            }
        }

        // Now, if we reach here, all of the letters in ransomNote has been processed,
        // and for each one, we have consumed one from the magazine.
        // i.e. It is possible to make the ransomNote using magazine.
        return true;
    }
}