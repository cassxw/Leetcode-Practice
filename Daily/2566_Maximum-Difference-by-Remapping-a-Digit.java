class Solution {
    public int minMaxDifference(int num) {
        
        // Math (Greedy)

        // num = integer.
    
        // Bob will sneakily remap one of the 10 possible digits to another.
        // (1) All occurrences of the remapped digit will be replaced.
        // (2) A digit can be remapped to itself.
        // (3) Bob can remap different digits for obtaining minimum and maximum values respectively.
        // (4) The resulting number after remapping can contain leading zeroes.

        // Return the difference between the max and min values
        // Bob can make by remapping exactly one digit in num.

        // Convert number to string to easily work with digits.
        String numStr = String.valueOf(num);

        // To obtain the max, remap the first non-nine digit to 9.
        char maxReplace = ' ';
        for (char ch : numStr.toCharArray()) {
            if (ch != '9') {
                maxReplace = ch;
                break;
            }
        }

        String maxStr = "";
        if (maxReplace == ' ') {
            // All digits are already 9, so do nothing,
            maxStr = numStr;
        }
        else {
            maxStr = numStr.replace(maxReplace, '9');
        }
        int max = Integer.parseInt(maxStr);

        // To obtain the min, remap the first non-zero digit to 0.
        char minReplace = ' ';
        for (char ch : numStr.toCharArray()) {
            if (ch != '0') {
                minReplace = ch;
                break;
            }
        }

        String minStr = "";
        if (minReplace == ' ') {
            // All digits are already 0, so do nothing.
            minStr = numStr;
        }
        else {
            minStr = numStr.replace(minReplace, '0');
        }
        int min = Integer.parseInt(minStr);

        // Return difference between max and min values.
        return max - min;
    }
}