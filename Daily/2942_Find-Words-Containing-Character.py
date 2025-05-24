class Solution:
    def findWordsContaining(self, words: List[str], x: str) -> List[int]:
        
        # Array (String)

        # words = string array.
        # x = character.

        # Return an array of indices,
        # representing the words that contain x.

        indices = []

        for i in range(len(words)):
            if (x in words[i]):
                indices.append(i)

        return indices
        