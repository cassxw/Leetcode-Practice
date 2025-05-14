public class Solution {
    private const int MOD = 1000000007;

    public int LengthAfterTransformations(string s, int t, IList<int> nums) {
        
        // Hash Table (Math, String, Dynamic Programming, Counting)

        // s = string.
        // t = int, the no. of transformations to perform.
        // nums = integer list of size 26.

        // In one transformation, every character in s is
        // replaced according to the following rules:
        // (1) s[i] = next nums[s[i] - 'a'] consecutive characters in alphabet.
        // (2) Wraps around the alphabet if it exceeds 'z'.

        // Return the length of the resulting string after exactly t transformations.
        // Since the answer may be very large, return modulo 10^9 + 7.

        //-----------------------------------------------------------------------------------------------
        // Linear Solution, adding on from #3335 => TLE (529/536)

        // // List to hold the initial letter frequency count in s.
        // int[] alphaCount = new int[26];
        // foreach (char c in s)
        // {
        //     alphaCount[c - 'a']++;
        // }

        // // Simulate the t transformations needed.
        // for (int tNum = 0; tNum < t; tNum++)
        // {
        //     // Holds updated letter frequence counts for this transformation.
        //     int[] newAlphaCount = new int[26];

        //     // Handling characters from 'a' to 'y' becomes next nums[s[i] - 'a'] chars in alphabet.
        //     // The letters wrap around the alphabet if it exceeds 'z'.
        //     for (int i = 0; i < 26; i++)
        //     {
        //         int count = alphaCount[i];
        //         if (count == 0)
        //         {
        //             continue;
        //         }

        //         // Represents how many consecutive letters this char should spread to.
        //         int spread = nums[i]; 

        //         for (int j = 1; j <= spread; j++)
        //         {
        //             // Wrap around using modulo 26.
        //             int nextChar = (i + j) % 26;
        //             newAlphaCount[nextChar] = (newAlphaCount[nextChar] + count) % MOD;
        //         }
        //     }
            
        //     // Prepare for next transformation by updating alphaCount.
        //     alphaCount = newAlphaCount;
        // }

        // // Sum all character counts to get final length of resulting string.
        // // i.e. sum of all character frequences.
        // long totalLength = 0;
        // foreach (int c in alphaCount)
        // {
        //     totalLength = (totalLength + c) % MOD;
        // }

        // return (int) totalLength;
        //-----------------------------------------------------------------------------------------------

        // Working solution needs Linear Algebra, with Matrix Exponentiation/Multiplication...
        // Model the transformation rules as a 26x26 Matrix, T:
        // T[i][j] represents how much letter j contributes to letter i in one step.
        const int L = 26;

        // Helper class for Matrix Operations:
        Mat BuildTransformMatrix(IList<int> nums)
        {
            Mat T = new Mat();
            for (int i = 0; i < L; i++)
            {
                for (int j = 1; j <= nums[i]; j++)
                {
                    // From char i, contribute to char (i + j) % 26.
                    T.a[(i + j) % 26, i] = 1;
                }
            }
            return T;
        }

        Mat res = QuickMul(BuildTransformMatrix(nums), t);

        // Build initial frequency vector.
        int[] f = new int[26];
        foreach (char ch in s)
        {
            f[ch - 'a']++;
        }

        // Multiply T with frequency vector.
        int answer = 0;
        for (int i = 0; i < 26; i++)
        {
            for (int j = 0; j < 26; j++)
            {
                answer = (int)((answer + (long)res.a[i, j] * f[j]) % MOD);
            }
        }

        return answer;
    }

    // Matrix Class for 26x26 alphabet.
    private class Mat {
        public int[,] a = new int[26, 26];

        public Mat() {}

        // Copy Sonstructor
        public Mat(Mat copyFrom)
        {
            for (int i = 0; i < 26; i++)
            {
                for (int j = 0; j < 26; j++)
                {
                    this.a[i, j] = copyFrom.a[i, j];
                }
            }
        }

        // Matrix Multiplication, with MOD use.
        public Mat Mul(Mat other)
        {
            Mat result = new Mat();
            
            for (int i = 0; i < 26; i++)
            {
                for (int j = 0; j < 26; j++)
                {
                    for (int k = 0; k < 26; k++)
                    {
                        result.a[i, j] = (int)((result.a[i, j] + (long)this.a[i, k] * other.a[k, j]) % MOD);
                    }
                }
            }

            return result;
        }
    }

    // Identity Matrix
    private Mat I()
    {
        Mat m = new Mat();

        for (int i = 0; i < 26; i++)
        {
            m.a[i, i] = 1;
        }

        return m;
    }

    // Fast Matrix Exponentiation
    private Mat QuickMul(Mat x, int y)
    {
        Mat ans = I();
        Mat cur = new Mat(x);

        while (y > 0)
        {
            if ((y & 1) == 1)
            {
                ans = ans.Mul(cur);
            }

            cur = cur.Mul(cur);
            y >>= 1;
        }

        return ans;
    }
}