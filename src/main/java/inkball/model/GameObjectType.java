package inkball.model;

import java.util.Arrays;

/**
 * @author SanseYooyea
 */
public enum GameObjectType {
    BALL('B'),
    HOLE('H'),
    SPACE(' '),
    SPAWNER('S'),
    WALL('X');

    private final char symbol;

    GameObjectType(char symbol) {
        this.symbol = symbol;
    }

    public static GameObjectType fromSymbol(char symbol) {
        return Arrays.stream(GameObjectType.values())
                .filter(type -> type.symbol == symbol)
                .findFirst()
                .orElse(null);
    }

    public static GameObject createGameObject(GameObjectType type, double x, double y) {
        switch (type) {
            case SPAWNER:
                return new Spawner(x, y);
            case WALL:
                return new Wall(x, y);
            default:
                return null;
        }
    }

    public static GameObject createGameObject(GameObjectType type, double x, double y, Color color) {
        switch (type) {
            case BALL:
                return new Ball(x, y, color);
            case HOLE:
                return new Hole(x, y, color);
            case WALL:
                return new Wall(x, y, color);
            default:
                return null;
        }
    }

    public char getSymbol() {
        return symbol;
    }
}
