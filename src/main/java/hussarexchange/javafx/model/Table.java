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

    public static int[][] currentState = {
            {1, 1, 1},
            {0, 0, 0},
            {2, 2, 2}
    };


    public static boolean isGoal() {
        boolean TheEnd = false;

        for (int i = 0; i < 3; i++) {
            if (currentState.equals(GoalState)) {
                TheEnd = true;
            } else {
                TheEnd = false;
                break;
            }
        }
        return TheEnd;
    }

    public static boolean canStepInto(int selectedRow, int selectedCol, int newRow, int newCol, int selectedColor) {
        if(currentState[newRow][newCol] == 0 && GoodStep(selectedRow, selectedCol, newRow, newCol)) {
            return true;
        }
        return false;
    }


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

    public static void main(String[] args) {
        Table state = new Table();
        System.out.println(state);
    }


}
