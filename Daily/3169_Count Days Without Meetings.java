import java.util.Arrays;

class Solution {
    public int countDays(int days, int[][] meetings) {
        
        // Arrays (and Sorting)

        // days = the total number of days an employee is available for work.
        // meetings = 2D array of size n, where meetings[i] = [start_i, end_i] (inclusive)
        //            represents the start & end of meeting i.

        // Return the count of days when the employee is available for work, but no meetings are scheduled.
        // i.e. the number of days that don't have any meetings.
        
        // Sort meetings, such that it is ordered by the start day of the meetings.
        Arrays.sort(meetings, (a,b) -> a[0] - b[0]); // O(N.log(N))
        
        int count = 0;
        int lastMeetingEnd = 0;
        
        // Iterate through each meeting.
        for (int[] meeting : meetings) { // O(N)
        
            // Add free days between lastMeeting and the start of the current meeting.
            // i.e. there is a gap between the last meeting and the current meeting.
            if (meeting[0] > lastMeetingEnd + 1) {
                count += meeting[0] - (lastMeetingEnd + 1);
            }

            // Update lastMeetingEnd, handling overlapping meetings.
            lastMeetingEnd = Math.max(lastMeetingEnd, meeting[1]);
        }

        // Add remaining free days after the last meeting.
        if (lastMeetingEnd < days) {
            count += days - lastMeetingEnd;
        }

        return count;

        // Time Complexity:
        // O(N.log(N))      <= sorting
        // + O(N)           <= iterating through meetings
        // = O(N.log(N))
    }
}