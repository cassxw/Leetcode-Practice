class Solution:
    def isValidSudoku(self, board: List[List[str]]) -> bool:
        
        # Array (Hash Table, Matrix)

        # 9x9

        # Helpful to define a function to check if a unit is valid,
        # to not repeat code for rows, columns, and subbboxes
        def is_valid(element: List[str]):
            # If the length of the original and copied element does not match,
            # then the current element contained repeated digits = INVALID.
            element = [x for x in element if x != "."]
            return len(set(element)) == len(element)

        # (1) Checking Rows
        for col in range(9):
            current_row = board[col]
            if not is_valid(current_row):
                return False

        # (2) Checking Columns        
        for row in range(9):

            current_col = []

            for col in range(9):
                current_col.append(board[col][row])
            
            if not is_valid(current_col):
                return False
        
        # (3) Checking 3x3 Sub-Boxes of the Grid
        for box_row in range(0, 9, 3):
            for box_col in range(0, 9, 3):
                current_box = []
                
                # Check each cell in the current 3x3 sub-box.
                for row in range(3):
                    for col in range(3):

                        # Calculate the actual position in the board.
                        actual_row = box_row + row
                        actual_col = box_col + col
                        current_box.append(board[actual_row][actual_col])

                if not is_valid(current_box):
                    return False

        # If we reach here, then passed all invalid tests = VALID.
        return True