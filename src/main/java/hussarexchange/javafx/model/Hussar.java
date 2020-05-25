package hussarexchange.javafx.model;

public class Hussar  {

    /**
     * Enum for representing the Hussar's color of the {@code Hussar}.
     */
    public enum Color {
        RED,
        BLUE
    }

    private final Color color;

    private Hussar(Color color) {
        this.color = null;
    }


    /**
     * This method returns the {@code Color} of the {@code Hussar} object.
     *
     * @see hussarexchange.javafx.model.Hussar.Color
     * @return the {@code Color} of the {@code Hussar} object
     */
    public Color getColor() {
        return color;
    }

    /**
     * Compares this object to the specified object. The result is {@code true}
     * if the given object is a {@code Hussar} object that contains the same
     * {@code Color} value, {@Code false} otherwise.
     *
     * @see hussarexchange.javafx.model.Hussar.Color
     * @param obj the object to compare with
     * @return {@code true} if the given object is a {@code Hussar} object and
     * equivalent to this {@code Card}, {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Hussar) {
            return (this.color == ((Hussar) obj).color);
        }
        return false;
    }

    public String toString() {
        return "Hussar { color = " + String.format("%4s", getColor()) + " }";
    }

}
