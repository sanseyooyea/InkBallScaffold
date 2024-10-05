package inkball.model;

/**
 * @author SanseYooyea
 */
public enum Color {
    ORANGE(java.awt.Color.ORANGE, 1),
    BLUE(java.awt.Color.BLUE, 2),
    GREEN(java.awt.Color.GREEN, 3),
    YELLOW(java.awt.Color.YELLOW, 4),
    GREY(java.awt.Color.GRAY, 5);

    private final java.awt.Color color;
    private final int number;

    Color(java.awt.Color color, int number) {
        this.color = color;
        this.number = number;
    }

    public static Color getColor(String color) {
        switch (color) {
            case "GREY":
                return GREY;
            case "ORANGE":
                return ORANGE;
            case "BLUE":
                return BLUE;
            case "GREEN":
                return GREEN;
            case "YELLOW":
                return YELLOW;
            default:
                return null;
        }
    }

    public java.awt.Color getColor() {
        return color;
    }
}
