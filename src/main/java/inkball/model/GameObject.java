package inkball.model;

import inkball.App;

/**
 * @author SanseYooyea
 */
public abstract class GameObject {
    protected final GameObjectType type;
    protected double x;
    protected double y;

    protected GameObject(GameObjectType type, double x, double y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public GameObjectType getType() {
        return type;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTop() {
        return y * App.CELL_SIZE + App.TOP_BAR_HEIGHT;
    }

    public double getBottom() {
        return y * App.CELL_SIZE + App.CELL_SIZE + App.TOP_BAR_HEIGHT;
    }

    public double getLeft() {
        return x * App.CELL_SIZE;
    }

    public double getRight() {
        return x * App.CELL_SIZE + App.CELL_SIZE;
    }
}
