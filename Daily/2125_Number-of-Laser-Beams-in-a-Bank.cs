public class Solution {
    public int NumberOfBeams(string[] bank) {

        // bank binary string array represents the bank's floor plan.
        int m = bank.Length;      //rows
        int n = bank[0].Length;   //cols

        // bank[i] = ith row.
        //         = '0', means the cell is empty.
        //         = '1', means the cell has a security device.

        // There is one laser beam between any 2 security devices,
        // if both of the following are met:
        // (1) The devices are located on different rows, where r1 < r2.
        // (2) For each row i where r1 < i < r2, there are no devices in the ith row.

        // Note: The laser beams are independent, i.e. don't interfere with each other.

        // Return the total number of laser beams in the bank.

        int laserBeams = 0;

        int prevDevCount = 0;
        for (int row = 0; row < m; row++)
        {
            int devCount = 0;
            foreach (var cell in bank[row])
            {
                if (cell == '1')
                {
                    devCount++;
                }
            }
            
            // Only add laser beams if current row has devices.
            if (devCount > 0)
            {
                laserBeams += devCount * prevDevCount;
                prevDevCount = devCount;
            }
        }

        return laserBeams;
    }
}