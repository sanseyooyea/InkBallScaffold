package inkball.model;

/**
 * @author SanseYooyea
 */
public enum Color {
    GREY(java.awt.Color.GRAY, 0),
    ORANGE(java.awt.Color.ORANGE, 1),
    BLUE(java.awt.Color.BLUE, 2),
    GREEN(java.awt.Color.GREEN, 3),
    YELLOW(java.awt.Color.YELLOW, 4);

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

    public static Color getColorByNumber(int color) {
        switch (color) {
            case 0:
                return GREY;
            case 1:
                return ORANGE;
            case 2:
                return BLUE;
            case 3:
                return GREEN;
            case 4:
                return YELLOW;
            default:
                return null;
        }
    }

    public java.awt.Color getAwtColor() {
        return color;
    }
}
