package inkball.model;

/**
 * @author SanseYooyea
 */
public enum Color {
    GREY(0),
    ORANGE(1),
    BLUE(2),
    GREEN(3),
    YELLOW(4);

    private final int number;

    Color(int number) {
        this.number = number;
    }

    public static Color getColor(String color) {
        color = color.toUpperCase();
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

    public static Color getColorByNumber(int number) {
        switch (number) {
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

    public int getNumber() {
        return number;
    }
}
