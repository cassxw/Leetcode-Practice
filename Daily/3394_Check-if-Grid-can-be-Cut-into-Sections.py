class Solution(object):
    def checkValidCuts(self, n, rectangles):
        """
        :type n: int
        :type rectangles: List[List[int]]
        :rtype: bool
        """
        
        # Array (and Sorting)

        # n = the dimensions of an n x n grid, with origin at bottom-left corner.
        # rectangles = rectangles[x_start, y_start, x_end, y_end] is a rectangle on the grid,
        #              ...where start is bottom-left, end is top-right.

        # Note! No rectangles overlap.

        # Return true if you can make either (1) 2 horizontal cuts, or (2) 2 vertical cuts on the grid, such that:
        #   - Each of the resulting 3 sections contains at least one rectangle.
        #   - Each rectangle belongs to exactly one section.
        # Else, return false.

        # Observation:
        # All valid cuts of the grid can only occur at the edges of existing rectangles.
        # So, no need to test every possible cut, i.e. exhaustive.
        # Focus on unqiue coordinates where rectangles start/end, reducing search space.

        # But missed the gap observation:

        def checkCuts(rectangles, dim):

            # Sort rectangles in given dimension, either horizontal or vertical, i.e. dim = x or y.
            rectangles.sort(key=lambda rect: rect[dim])

            # Track number of gaps between rectangles in the given dimension.
            # A gap exists when there is space between rectangles where a cut can be made.
            num_gaps = 0

            # Set furthest_end to be the first rectangle's dim_end.
            furthest_end = rectangles[0][dim + 2]

            # Iterate through every rectangle.
            for rectangle in rectangles:

                # If furthest_end is less than or equal to the current rectangle's dim_start...
                if furthest_end <= rectangle[dim]:
                    # A gap has been found, i.e. a valid cut can be made here.
                    num_gaps += 1

                # Update furthest_end with dim_end, if larger.
                furthest_end = max(furthest_end, rectangle[dim + 2])

            # Once all rectangles iterated, check if gap >= 2 -> We need 2 gaps to create 3 sections.
            return num_gaps >= 2

        # Check for valid horizontal and vertical cuts.
        return checkCuts(rectangles, 0) or checkCuts(rectangles, 1)

        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        #                       FIRST ATTEMPT
        #~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        # # Collect the unique x and y coordinates for potential cuts.
        # x_cuts = set()
        # y_cuts = set()

        # for rectangle in rectangles:
        #     x_cuts.add(rectangle[0])
        #     x_cuts.add(rectangle[1])
        #     y_cuts.add(rectangle[2])
        #     y_cuts.add(rectangle[3])

        # x_cuts = sorted(list(x_cuts))
        # y_cuts = sorted(list(y_cuts))

        # # Check for valid horizontal cuts (cutting along x-axis).
        # for i in range(len(x_cuts) - 1):
        #     for j in range(i + 1, len(x_cuts)):
        #         # Grid has been cut by line y=x_cuts[i] and y=x_cuts[j].
        #         cut1 = x_cuts[i]
        #         cut2 = x_cuts[j]

        #         # Section 1: x < x_cuts[i]
        #         # Section 2: x_cuts[i] <= x < x_cuts[j]
        #         # Section 3: x >= x_cuts[j]
        #         count1 = count2 = count3 = 0

        #         # Count rectangles in each section created by cuts at x_cuts[i] and x_cuts[j].
        #         for rectangle in rectangles:
        #             x_start, y_start, x_end, y_end = rectangle
        #             if x_end <= cut1:
        #                 # Rectangle entirely in left section.
        #                 count1 += 1
        #             elif x_start >= cut2:
        #                 # Rectangle entirely in right section.
        #                 count3 += 1
        #             elif x_start >= cut1 and x_end <= cut2:
        #                 # Rectangle entirely in middle section.
        #                 count2 += 1

        #         # Check if all sections have at least one rectangle
        #         if count1 > 0 and count2 > 0 and count3 > 0:
        #             print("horizontal")
        #             print(i)
        #             print(j)
        #             print(count1)
        #             print(count2)
        #             print(count3)
        #             return True

        # # Check for valid vertical cuts (cutting along y-axis).
        # for i in range(len(y_cuts) - 1):
        #     for j in range(i + 1, len(y_cuts)):
        #         # Grid has been cut by line x=y_cuts[i] and x=y_cuts[j].
        #         cut1 = y_cuts[i]
        #         cut2 = y_cuts[j]

        #         # Section 1: y < y_cuts[i]
        #         # Section 2: y_cuts[i] <= y < y_cuts[j]
        #         # Section 3: y >= y_cuts[j]
        #         count1 = count2 = count3 = 0

        #         # Count rectangles in each section created by cuts at y_cuts[i] and y_cuts[j].
        #         for rectangle in rectangles:
        #             x_start, y_start, x_end, y_end = rectangle
        #             if y_end <= cut1:
        #                 # Rectangle entirely in bottom section.
        #                 count1 += 1
        #             elif y_start >= cut2:
        #                 # Rectangle entirely in top section.
        #                 count3 += 1
        #             elif y_start >= cut1 and y_end <= cut2:
        #                 # Rectangle entirely in middle section.
        #                 count2 += 1

        #         # Check if all sections have at least one rectangle.
        #         if count1 > 0 and count2 > 0 and count3 > 0:
        #             print("vertical")
        #             print(i)
        #             print(j)
        #             print(count1)
        #             print(count2)
        #             print(count3)
        #             return True

        # # Return false if no valid cuts found.
        # return False
