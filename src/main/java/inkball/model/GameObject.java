package inkball.model;

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
}
