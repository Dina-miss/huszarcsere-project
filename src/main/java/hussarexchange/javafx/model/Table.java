package hussarexchange.javafx.model;

public class Table {

    /**
     * The array representing the start configuration of the tray.
     */
    public static final int[][] startState = {
            {1, 1, 1},
            {0, 0, 0},
            {2, 2, 2}
    };


    /**
     * The array representing the goal configuration of the tray.
     */
    public static final int[][] GoalState = {
            {2, 2, 2},
            {0, 0, 0},
            {1, 1, 1}
    };

    /**
     * The array representing the current configuration of the tray.
     */
    public static int[][] currentState = {
            {1, 1, 1},
            {0, 0, 0},
            {2, 2, 2}
    };


    /**
     * Checks whether the game is solved.
     *
     * @return {@code true} if the game is solved, {@code false} otherwise
     */
    public static boolean isGoal() {
        for (int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if (currentState[i][j] == GoalState[i][j]) {}
                else {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Return true if the hussar step an empty cell and it step in L shape.
     *
     * @return {@code true} if the hussar step an empty cell and it step in
     * L shape, {@code false} otherwise
     */
    public static boolean canStepInto(int selectedRow, int selectedCol, int newRow, int newCol) {
        if(currentState[newRow][newCol] == 0 && GoodStep(selectedRow, selectedCol, newRow, newCol)) {
            return true;
        }
        return false;
    }

    /**
     * Checks the good steps. Return true if the hussar wants to take a good
     * step.
     *
     * @return {@code true} if the step is good, {@code false} otherwise
     */
    public static boolean GoodStep(int row, int col, int newRow, int newCol) {
        if(newRow - row == 2 && newCol - col == 1) {
            return true;
        }
        else if(newRow - row == 1 && newCol - col == 2) {
            return true;
        }
        else if(newRow - row == -2 && newCol - col == -1) {
            return true;
        }
        else if(newRow - row == -1 && newCol - col == -2) {
            return true;
        }
        else if(newRow - row == -2 && newCol - col == 1) {
            return true;
        }
        else if(newRow - row == 2 && newCol - col == -1) {
            return true;
        }
        else if(newRow - row == -1 && newCol - col == 2) {
            return true;
        }
        else if(newRow - row == 1 && newCol - col == -2) {
            return true;
        }
        else {
            return false;
        }
    }


}
