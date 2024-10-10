package inkball.model;

import inkball.App;
import org.jetbrains.annotations.NotNull;

/**
 * @author SanseYooyea
 */
public class Hole extends GameObject implements Colorable {
    @NotNull
    private final Color color;

    public Hole(double x, double y, @NotNull Color color) {
        super(GameObjectType.HOLE, x, y);
        this.color = color;
    }

    public boolean captureBall(Ball ball) {
        return ball.getLeft() >= getLeft() && ball.getRight() <= getRight() && ball.getTop() >= getTop() && ball.getBottom() <= getBottom();
    }

    @Override
    public @NotNull Color getColor() {
        return color;
    }

    @Override
    public double getBottom() {
        return getTop() + 2 * App.CELL_SIZE;
    }

    @Override
    public double getRight() {
        return getLeft() + 2 * App.CELL_SIZE;
    }
}
