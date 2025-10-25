public class Solution {
    public int TotalMoney(int n) {
        
        // Hercy puts in money into his bank every day.
        // He starts on a Monday and put in $1 on that day.
        // Every day, he puts in $1 more than the previous day.

        // However, every next Monday, he will put in $1 more
        // than the previous Monday.

        //          What he puts in: - Total:
        // (0) Sun: $0               - $0
        // (1) Mon: $1               - $1
        // (2) Tue: $2               - $3
        // (3) Wed: $3               - $6
        // (4) Thu: $4               - $10
        // (5) Fri: $5               - $15
        // (6) Sat: $6               - $21
        // (7) Sun: $7               - $28
        // (8) Mon: (1)+$1 = $2      - $30
        // (9) Tue: $3               - $33
        // (10) Wed: $4              - $37

        // Given n, return the total amount of money
        // he will have in the bank after n days.

        if (n == 1)
        {
            return 1;
        }

        int[] putIn = new int[n];
        putIn[0] = 1;

        for (int i = 1; i < n; i++)
        {
            if (i % 7 == 0)
            {
                putIn[i] = putIn[i-7] + 1;
                continue;
            }

            putIn[i] = putIn[i-1] + 1;
        }

        int total = 0;
        for (int i = 0; i < n; i++)
        {
            total += putIn[i];
        }
        return total;
    }
}