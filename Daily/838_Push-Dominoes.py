class Solution:
    def pushDominoes(self, dominoes: str) -> str:

        # Two Pointers (String, Dynamic Programming)

        # dominoes = string.
        # dominoes[i] is either 'L', 'R', or '.'
        n = len(dominoes)

        # There are n dominoes in a line, each placed vertically upright.
        # We simultaneously push some either to the left or right.

        # After each second...
        #   - Each domino falling to the L pushes the adj to L.
        #   - Each domino falling to the R pushes the adj to R.
        #   - A domino falling on from both sides stays still due to balance.

        # Note: Consider that a falling domino expends no additional force
        #       on a falling or already-fallen domino.

        # Create a forces array to represent the net forces being applied to the dominoes.
        forces = [0] * n

        # First pass: left to right.
        # Apply force from dominoes pushed to the right ('R').
        force = 0
        for i in range(n):

            if (dominoes[i] == 'R'):
                # Max force.
                force = n
            
            elif (dominoes[i] == 'L'):
                # L cancels R force.
                force = 0

            else:
                # Decrease force over distance.
                force = max(force - 1, 0)
            
            # Add it as R is taken as additive.
            forces[i] += force

        # Second pass: right to left.
        # Tracking the left forces, i.e. R => L.
        force = 0
        for i in range(n-1, -1, -1):

            if (dominoes[i] == 'L'):
                # Max force.
                force = n
            
            elif (dominoes[i] == 'R'):
                # R cancels L force.
                force = 0

            else:
                # Decrease force over distance.
                force = max(force - 1, 0)
            
            # Subtract it as L is taken as negative.
            forces[i] -= force

        # Using our built forces array, build the new domino state string.
        new_dominoes = ""
        for force in forces:

            if (force == 0):
                # Resultant force on current domino is idle.
                new_dominoes += '.'

            elif (force > 0):
                # Resultant force on current domino is R.
                new_dominoes += 'R'

            elif (force < 0):
                # Resultant force on current domino is L.
                new_dominoes += 'L'

        return new_dominoes
        