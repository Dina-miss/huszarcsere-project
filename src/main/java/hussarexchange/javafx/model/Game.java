package hussarexchange.javafx.model;

public class Game {
    class Movements {
        private final int oldX;
        private final int oldY;
        private final int newX;
        private final int newY;

        Movements(int oldX, int oldY, int newX, int newY) {
            this.oldX = oldX;
            this.oldY = oldY;
            this.newX = newX;
            this.newY = newY;
        }

        int getOldX() {
            return oldX;
        }

        int getOldY() {

            return oldY;
        }

        int getNewX() {

            return newX;
        }

        int getNewY() {

            return newY;
        }
    }
}
